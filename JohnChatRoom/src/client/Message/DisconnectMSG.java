package Message;

public class DisconnectMSG extends Message{
	String status;
	String user;
	public DisconnectMSG(String name){
		Identifer="Disconnect";
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
	public String getFriendlyMsg(){
		return user+" leaves  the chatroom"+"\n";
	}
}
