package model;
/**
 * This is the class for client model, seperated with the view window
 * @author susanqin
 *
 */
public class Client {
	/**The nickname assigned by the client user
	 * Which is also the ID used by the system*/
	String nickName;
	
	public Client(String str){
		nickName=str;
	}
	public String getName(){
		return this.nickName;
	}
}

