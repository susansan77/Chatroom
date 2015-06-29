import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

	
	PrintWriter outS;
	BufferedReader inS;
	
	public ServerThread(Server s, Socket so){
		this.server=s;
		this.socket=so;
		 try {
			outS=new PrintWriter (socket.getOutputStream(), true);
			inS=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			verifyDuplicate();
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public PrintWriter getOutput(){
		return this.outS;
	}
	public BufferedReader getInput(){
		return this.inS;
	}
	public void run(){
		try {
			while(true){
				String s=inS.readLine();
				server.broadCast(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
public void verifyDuplicate(){
		
		try {
			String s;
			s = inS.readLine();
			if(server.getclientsNameList().values().contains(s)){
				outS.println("Server Message: Duplicate User");			
			}
			else{
				server.getclientsNameList().put(socket, s);
				server.getclientsOutputList().put(socket, outS);
				outS.println("Login OK!");
				start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
