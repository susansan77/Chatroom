package ServerMessage;

import java.util.ArrayList;

public class BroadCastMSG extends Message{
	String sender;
	public BroadCastMSG(String str){
		
	}
	public BroadCastMSG(String name, String text){
		Identifer="BroadCast";
		text=text;
		sender=name;
	}

	@Override
	public String flatten() {
		// TODO Auto-generated method stub
		return Identifer+":"+sender+":"+text+":";
	}
	public String getSender(){
		return sender;
	}
	public String getText(){
		return text;
	}
	
}