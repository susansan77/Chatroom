package ServerCore;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Handler.ProcessMsgController;
import Message.DisconnectMSG;
import ServerMessage.UnExpectedDisconnectMSG;

/**
 * This is the server thread for one client,
 * once a connection builds up, the server thread will
 * be constructed
 * @author susanqin
 *
 */
public class ServerThread extends Thread{
	/**this is the server this thread belongs to*/
	private Server server;
	private Socket socket;
	boolean connectionValid=true;
	
	private PrintWriter outS;
	private BufferedReader inS;
	
	public ServerThread(Server s, Socket so){
		this.setServer(s);
		this.setSocket(so);
		 try {
			setOutS(new PrintWriter (getSocket().getOutputStream(), true));
			setInS(new BufferedReader(new InputStreamReader(getSocket().getInputStream())));
			//verifyDuplicate();
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		start();
		
	}
	
	public PrintWriter getOutput(){
		return this.getOutS();
	}
	public BufferedReader getInput(){
		return this.getInS();
	}
	public void run(){
		try {
			while(connectionValid){
				String s=getInS().readLine();
				//server.broadCast(s);
				ProcessMsgController controller=new ProcessMsgController(this,s);
				controller.process();
			}
		} catch (Exception ioe) {
			String user=this.server.findUserByClient(this.socket);
			connectionValid=false;
			server.removeConnection(socket,this);
			UnExpectedDisconnectMSG disconnectMsg=new UnExpectedDisconnectMSG(user);
			
			ProcessMsgController controller=new ProcessMsgController(this,disconnectMsg.flatten());
			controller.process();
			
		}
		
	}

	public void clientLeave() {
		connectionValid=false;
		server.removeConnection(socket,this);
	}


public void setConnectionValid(boolean connectionValid) {
		this.connectionValid = connectionValid;
	}

public Server getServer() {
	return server;
}

public void setServer(Server server) {
	this.server = server;
}

public BufferedReader getInS() {
	return inS;
}

public void setInS(BufferedReader inS) {
	this.inS = inS;
}

public PrintWriter getOutS() {
	return outS;
}

public void setOutS(PrintWriter outS) {
	this.outS = outS;
}

public Socket getSocket() {
	return socket;
}

public void setSocket(Socket socket) {
	this.socket = socket;
}
}
