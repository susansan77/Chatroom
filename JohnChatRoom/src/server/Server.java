import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This is the class for Server model
 * @author susanqin
 *
 */
public class Server {
	
	/**this indicates which port the server is using
	 * We assign this as 3000*/
	private int port=3000;
	/**this is a mapping between socket to OutPutStrems for each client*/
	private Hashtable<Socket, PrintWriter> clients=new Hashtable<Socket, PrintWriter>();
	/**this is a mapping between sockets and clients' nicknames*/
	private Hashtable<Socket,String> clientsNameList=new Hashtable<Socket,String>();
	/**Server socket for accepting connections request of clients*/
	ServerSocket serverSocket;
	
	
	public Server(){
		//initialize server's socket
		try {
			serverSocket= new ServerSocket (port);
			System.out.println("Start to listen on: "+serverSocket);
			serve();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void serve() {
		//serve forever
		while(true){
			try {
				Socket s=serverSocket.accept();
				System.out.println("Connect from: "+s);
				ServerThread thread=new ServerThread(this,s);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){
		Server server=new Server();
	}
	public void broadCast(String s) {
		// TODO Auto-generated method stub
		synchronized(this.clients){
			Enumeration outs=clients.elements();
			for(;outs.hasMoreElements();){
				PrintWriter o=(PrintWriter) outs.nextElement();
				o.println(s);
			}
		}
	}
	public Hashtable<Socket,String> getclientsNameList(){
		return this.clientsNameList;
	}
	public Hashtable<Socket, PrintWriter> getclientsOutputList(){
		return this.clients;
	}
}
