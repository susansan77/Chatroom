import java.io.IOException;
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
	private Hashtable clients=new Hashtable();
	/**this is a mapping between sockets and clients' nicknames*/
	private Hashtable<Socket,String> clientsNameList;
	/**Server socket for accepting connections request of clients*/
	ServerSocket serverSocket;
	
	
	public Server(){
		//initialize server's socket
		try {
			serverSocket= new ServerSocket ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//serve forever
		while(true){
			try {
				Socket s=serverSocket.accept();
				
				
				
				
				ServerThread thread=new ServerThread();
				thread.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void main(String[] args){
		Server server=new Server();
	}
}
