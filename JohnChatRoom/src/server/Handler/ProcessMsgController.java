package Handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Enumeration;
import java.util.StringTokenizer;

import ServerCore.Server;
import ServerCore.ServerThread;
import ServerMessage.BroadCastMSG;
import ServerMessage.ConnectMSG;
import ServerMessage.EndToEndMSG;
import ServerMessage.Message;
import ServerMessage.NameListMSG;
import model.*;
import view.ClientWindow;


public class ProcessMsgController {
	Message msg;
	ServerThread serverThread;
	public ProcessMsgController(ServerThread serverT, String str) {
		// TODO Auto-generated constructor stub
		this.serverThread=serverT;
		msg=generateMsg(str);
		
	}

	

	private Message generateMsg(String str) {
		// TODO Auto-generated method stub
		String identifer=Message.extract(str);
		if(identifer.equals("BroadCast")){
			BroadCastMSG msg=new BroadCastMSG(str);
			return msg;
		}
		else if(identifer.equals("Whisper")){
			EndToEndMSG msg=new EndToEndMSG(str);
			return msg;
		}
		else if(identifer.equals("Connect")){
			ConnectMSG msg=new ConnectMSG(str);
			return msg;
		}
		else if(identifer.equals("List")){
			NameListMSG msg=new NameListMSG(str);
			return msg;
				}
		else return null;
	}

	public void process() {
		// TODO Auto-generated method stub
		if(msg instanceof BroadCastMSG){
			broadCast(msg.flatten());
			
		}else if(msg instanceof EndToEndMSG){
			whisper((EndToEndMSG) msg);
		}else if(msg instanceof ConnectMSG){
			if(verifyDuplicate(((ConnectMSG) msg).getUser())==false){
				serverThread.getOutS().println("Server Message: Duplicate User");	
			}
			else{
				serverThread.getServer().getclientsNameList().put( ((ConnectMSG) msg).getUser(),serverThread.getSocket());
				this.serverThread.getServer().getclientsOutputList().put(serverThread.getSocket(), serverThread.getOutS());
				serverThread.getOutS().println("Login OK!");
				this.serverThread.start();		
			broadCast(msg.flatten());
			}
		}else if(msg instanceof NameListMSG){
			broadCast(msg.flatten());
		}
		
	}
	public void broadCast(String s) {
		// TODO Auto-generated method stub
		synchronized(serverThread.getServer().getClients()){
			Enumeration outs=this.serverThread.getServer().getClients().elements();
			for(;outs.hasMoreElements();){
				PrintWriter o=(PrintWriter) outs.nextElement();
				o.println(s);
			}
		}
	}
	public void whisper(EndToEndMSG msg){
		//find the channel for both sender and receiver
		Socket senderSoc=this.serverThread.getServer().getclientsNameList().get(msg.getSender());
		Socket receiverSoc=this.serverThread.getServer().getclientsNameList().get(msg.getReceiver());
		PrintWriter sendW=this.serverThread.getServer().getClients().get(senderSoc);
		PrintWriter receiveW=this.serverThread.getServer().getClients().get(receiverSoc);
		synchronized(this.serverThread.getServer().getClients()){
			sendW.println(msg.flatten());
			receiveW.println(msg.flatten());
		}
	}
public boolean verifyDuplicate(String name){
		
		if(serverThread.getServer().getclientsNameList().contains(name)){
				
			return false;
		}
		else{
			
			return true;
		}	
	}
}
