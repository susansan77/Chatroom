package ServerMessage;

import java.util.ArrayList;

public class EndToEndMSG extends Message{
	String sender;
	String receiver;
	public EndToEndMSG(String sender, String receiver, String txt){
		Identifer="Whisper";
		this.sender=sender;
		this.receiver=receiver;
		this.text=txt;
	}
	public EndToEndMSG(String str) {
		// TODO Auto-generated constructor stub
	}
	public EndToEndMSG(ArrayList<String> tokens) {
		// TODO Auto-generated constructor stub
		Identifer=tokens.get(0);
		sender=tokens.get(1);
		receiver=tokens.get(2);
		text=tokens.get(3);
	}
	@Override
	public String flatten() {
		// TODO Auto-generated method stub
		return Identifer+":"+sender+":"+receiver+":"+text+":";
	}
	public String getSender() {
		// TODO Auto-generated method stub
		return sender;
	}
	public Object getReceiver() {
		// TODO Auto-generated method stub
		return receiver;
	}
	
}