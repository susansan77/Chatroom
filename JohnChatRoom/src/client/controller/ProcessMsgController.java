package controller;

import java.util.ArrayList;
import java.util.StringTokenizer;

import Message.*;
import view.ClientWindow;

public class ProcessMsgController {
	ClientWindow window;
	Message msg;
	public ProcessMsgController(ClientWindow clientWindow, String str) {
		// TODO Auto-generated constructor stub
		this.window=clientWindow;
		msg=generateMsg(str);
		
	}

	

	private Message generateMsg(String str) {
		// TODO Auto-generated method stub
		String identifer=Message.extractIdentifer(str);
		if(identifer.equals("BroadCast")){
			BroadCastMSG msg=new BroadCastMSG(Message.getTokens(str));
			return msg;
		}
		else if(identifer.equals("Whisper")){
			EndToEndMSG msg=new EndToEndMSG(Message.getTokens(str));
			
			return msg;
		}
		else if(identifer.equals("Connect")){
			String username=Message.getTokens(str).get(1);
			//System.out.println(username);
			ConnectMSG msg=new ConnectMSG(username);
			return msg;
		}
		else if(identifer.equals("List")){
			NameListMSG msg=new NameListMSG(str);
			return msg;
				}
		else if(identifer.equals("Disconnect")){
			DisconnectMSG msg=new DisconnectMSG(Message.getTokens(str).get(1));
			return msg;
				}
		else return null;
	}

	public void process() {
		// TODO Auto-generated method stub
		if(msg instanceof BroadCastMSG){
			String broadMsg=((BroadCastMSG) msg).getFriendlyMsg();
			window.getDisplayTextArea().append(broadMsg);
			
		}
		
		else if(msg instanceof EndToEndMSG){
			boolean IamSender=((EndToEndMSG) msg).getSender().equals(window.getClient());
			((EndToEndMSG) msg).setIfIamSender(IamSender);
			String whisperMsg=((EndToEndMSG) msg).getFriendlyMsg();
			window.getDisplayTextArea().append(whisperMsg);
		}
		
		else if(msg instanceof ConnectMSG){
			String connectMsg=((ConnectMSG) msg).getFriendlyMsg();
			window.getDisplayTextArea().append(connectMsg);
			
		}
		
		else if(msg instanceof NameListMSG){
			
			window.setTable(((NameListMSG) msg).getList());
		}
		else if(msg instanceof DisconnectMSG){
			String disconnectMsg=((DisconnectMSG) msg).getFriendlyMsg();
			window.getDisplayTextArea().append(disconnectMsg);
			if(((DisconnectMSG) msg).getUser().equals(window.talkTo)){
				window.setTalkTo("everybody");
				window.getTalkToLabel().setText("Talk to " + window.talkTo);
			}
		}
	}

}
