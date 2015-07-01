package controller;

import java.awt.event.MouseAdapter;
import java.io.PrintWriter;
import java.net.Socket;

import Message.BroadCastMSG;
import view.ClientWindow;



public class BroadcastMsgController {
	ClientWindow window;
	PrintWriter writer;
	BroadCastMSG msg;
	public BroadcastMsgController(ClientWindow window, PrintWriter w,String text) {
		// TODO Auto-generated constructor stub
		this.window=window;
		this.writer=w;
		msg=new BroadCastMSG(window.getClient(),text);
	}


	public void process(){
		writer.println(msg.flatten());
	}
	
}
