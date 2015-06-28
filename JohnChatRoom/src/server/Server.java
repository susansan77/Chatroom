import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
	private Hashtable<Socket, OutputStream> clients=new Hashtable<Socket, OutputStream>();
	/**this is a mapping between sockets and clients' nicknames*/
	private Hashtable<Socket,String> clientsNameList;
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
				
				//save output stream
				DataOutputStream out=new DataOutputStream(s.getOutputStream());
				this.clients.put(s, out);
				
				//start to serve this client
				ServerThread thread=new ServerThread();
				thread.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){
		Server server=new Server();
	}
}
