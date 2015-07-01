package ServerMessage;

import java.util.ArrayList;

public class UnExpectedDisconnectMSG extends Message{
	String status;
	String user;
	public UnExpectedDisconnectMSG(String name){
		Identifer="UnExpectedDisconnect";
		user=name;
		this.status="disconnect";
	}
	
	public UnExpectedDisconnectMSG(ArrayList<String> tokens) {
		// TODO Auto-generated constructor stub
		Identifer="UnExpectedDisconnect";
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
