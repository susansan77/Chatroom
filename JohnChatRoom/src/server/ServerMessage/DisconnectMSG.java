package ServerMessage;

public class DisconnectMSG extends Message{
	String status;
	String user;
	public DisconnectMSG(String name){
		Identifer="Disonnect";
		user=name;
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
