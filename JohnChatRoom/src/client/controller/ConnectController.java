package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import Message.ConnectMSG;
import view.ClientWindow;
import view.LoginWindow;

public class ConnectController {
	LoginWindow window;
	Socket socket;
	
	public ConnectController(String nickname, int p, LoginWindow w) throws IOException {
		// TODO Auto-generated constructor stub
		try {
			
			 socket=new Socket("localhost",p);
			 //socket.setSoTimeout(15000);
			 window=w;
			 PrintWriter outS = null; 
			 BufferedReader inS = null ;
			 try {
				  outS = new PrintWriter(socket.getOutputStream(), true);
				  inS = new BufferedReader(new InputStreamReader(socket
							.getInputStream()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 window.setClient(nickname);
			 window.setSocket(socket);
			 window.setOutS(outS);
			 window.setInS(inS);
			 ConnectMSG connectMsg=new ConnectMSG(nickname);
			 outS.println(connectMsg.flatten());
			 
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (SocketTimeoutException timee){
			 JOptionPane.showMessageDialog(window.getFrame(),
						"connection timeout due to no response from server, have you use the write port?");
			
		}
	}
	public Socket getSocket(){
		return this.socket;
	}

}
