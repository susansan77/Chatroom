package controller;

import java.util.ArrayList;
import java.util.StringTokenizer;

import Message.*;
import model.*;
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
			String broadMsg=((BroadCastMSG) msg).getFriendlyMsg();
			window.getDisplayTextArea().append(broadMsg);
			
		}else if(msg instanceof EndToEndMSG){
			boolean IamSender=((EndToEndMSG) msg).getSender().equals(window.getClient().getName());
			((EndToEndMSG) msg).setIfIamSender(IamSender);
			String whisperMsg=((EndToEndMSG) msg).getFriendlyMsg();
			window.getDisplayTextArea().append(whisperMsg);
		}else if(msg instanceof ConnectMSG){
			String connectMsg=((ConnectMSG) msg).getFriendlyMsg();
			window.getDisplayTextArea().append(connectMsg);
			
		}else if(msg instanceof NameListMSG){
			ArrayList<String> list=((NameListMSG)msg).getList();
			window.setTable(list);
			}
		
	}

}
