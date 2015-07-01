package ServerMessage;

import java.util.ArrayList;
import java.util.Collection;

public class ConnectMSG extends Message{
	String status="connect";
	String user;
	public ConnectMSG(String name){
		Identifer="Connect";
		user=name;
		this.status="connect";
	}
	public ConnectMSG(ArrayList<String> tokens){
		Identifer="Connect";
		user=tokens.get(1);
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


	
}