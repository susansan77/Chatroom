package Message;

import java.util.ArrayList;

public class EndToEndMSG extends Message{
	String sender;
	public String getSender() {
		return sender;
	}
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
	public void setIfIamSender(boolean isSender){
		if(isSender)
		sender="you";
		else
		receiver="you";
	}
	public String getFriendlyMsg(){
		return sender+" whisper to "+receiver+": "+text+"\n";
	}
}