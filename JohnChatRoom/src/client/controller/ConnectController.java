package controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectController {
	Socket socket;
	
	public ConnectController(String nickname, int p) {
		// TODO Auto-generated constructor stub
		try {
			
			 socket=new Socket("localhost",p);
			 
			 System.out.println("connected to : "+socket);
			 
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Socket getSocket(){
		return this.socket;
	}

}
