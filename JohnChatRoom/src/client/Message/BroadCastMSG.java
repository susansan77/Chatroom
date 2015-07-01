package Message;

import java.util.ArrayList;

public class BroadCastMSG extends Message{
	String sender;

	public BroadCastMSG(String name, String text){
		Identifer="BroadCast";
		this.text=text;
		sender=name;
	}

	public BroadCastMSG(ArrayList<String> tokens) {
		// TODO Auto-generated constructor stub
		Identifer="BroadCast";
		sender=tokens.get(1);
		text=tokens.get(2);
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
	public String getFriendlyMsg(){
		return sender+":"+text+"\n";
	}
}