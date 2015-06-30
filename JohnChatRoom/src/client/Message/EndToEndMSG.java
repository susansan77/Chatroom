package Message;

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
		return sender+"whisper to "+receiver+": "+text+"\n";
	}
}