package ServerMessage;

import java.util.ArrayList;

public class DisconnectMSG extends Message{
	String status;
	String user;
	public DisconnectMSG(String name){
		Identifer="Disconnect";
		user=name;
		this.status="disconnect";
	}
	
	public DisconnectMSG(ArrayList<String> tokens) {
		// TODO Auto-generated constructor stub
		Identifer="Disconnect";
		user=tokens.get(1);
		this.status="disconnect";
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
