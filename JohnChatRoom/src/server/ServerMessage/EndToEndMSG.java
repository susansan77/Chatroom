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