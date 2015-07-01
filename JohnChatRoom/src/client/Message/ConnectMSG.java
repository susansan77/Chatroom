package Message;

import java.util.ArrayList;
import java.util.Collection;

public class ConnectMSG extends Message{
	String status;
	String user;
	public ConnectMSG(String name){
		Identifer="Connect";
		user=name;
		this.status="connect";
	}
	
	
	@Override
	public String flatten() {
		// TODO Auto-generated method stub
		return Identifer+":"+user+":"+status+":"+"to server"+":";
	}
	public String getUser(){
		return user;
	}
	public String getFriendlyMsg(){
		return user+" join in the chatroom"+"\n";
	}
}