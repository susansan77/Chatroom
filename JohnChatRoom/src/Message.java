
public class Message {
	String text;
	public Message(){
		
	}
}

class BroadCastMSG extends Message{
	String sender;
}
class EndToEndMSG extends Message{
	String sender;
	String receiver;
}
class StatusMSG extends Message{
	
}
