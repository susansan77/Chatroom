package ServerMessage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.StringTokenizer;

abstract public class Message {
	String Identifer;
	String text;
	public abstract String flatten();
	
	static public String extract(String str){
		StringTokenizer st = new StringTokenizer(str, ":");
		String identifer=st.nextToken();
		return identifer;
	}
	static public ArrayList<String> getTokens(String str){
		StringTokenizer st = new StringTokenizer(str, ":");
		ArrayList<String> tokens=new ArrayList<String>();
		while(st.hasMoreTokens()){
			tokens.add(st.nextToken());
		}
		return tokens;
	}
	
}




	