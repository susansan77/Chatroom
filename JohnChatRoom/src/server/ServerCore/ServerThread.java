package ServerCore;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Handler.ProcessMsgController;

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
		
		
	}
	
	public PrintWriter getOutput(){
		return this.getOutS();
	}
	public BufferedReader getInput(){
		return this.getInS();
	}
	public void run(){
		try {
			while(true){
				String s=getInS().readLine();
				//server.broadCast(s);
				ProcessMsgController controller=new ProcessMsgController(this,s);
				controller.process();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
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
