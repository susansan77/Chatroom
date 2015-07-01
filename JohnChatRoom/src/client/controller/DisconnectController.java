package controller;

import java.io.PrintWriter;
import java.net.Socket;

import Message.DisconnectMSG;
import view.LoginWindow;

public class DisconnectController {
	
		String name;
		PrintWriter outS;
		
		public DisconnectController(String nickname,  PrintWriter o ) {
				 this.name=nickname;			
				 outS=o;
				 DisconnectMSG disconnectMsg=new DisconnectMSG(name);
				 outS.println(disconnectMsg.flatten()); 
			
		}

}


