package controller;

import java.io.PrintWriter;

import view.ClientWindow;
import Message.EndToEndMSG;

public class WhisperController {
	
	ClientWindow window;
	PrintWriter writer;
	EndToEndMSG msg;
	public WhisperController(ClientWindow clientWindow, PrintWriter outS,
			String str, String talkTo) {
		// TODO Auto-generated constructor stub
		window=clientWindow;
		writer=outS;
		msg=new EndToEndMSG(window.getClient(),talkTo,str);
	}
	public void process() {
		// TODO Auto-generated method stub
		writer.println(msg.flatten());
	}
}
