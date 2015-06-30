package ServerMessage;

import java.util.ArrayList;
import java.util.Collection;

public class ConnectMSG extends Message{
	String status;
	String user;
	public ConnectMSG(String name, int status){
		Identifer="Connect";
		user=name;
		this.status="connect";
	}
	public ConnectMSG(String str) {
		// TODO Auto-generated constructor stub
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