package ServerMessage;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

public class NameListMSG extends Message{
	
	ArrayList<String> nameList=new ArrayList<String>();
	
	public NameListMSG(Hashtable<String, Socket> hashtable){
		Identifer="List";
		Enumeration<String> nlist=hashtable.keys();
		for(;nlist.hasMoreElements();){
			nameList.add(nlist.nextElement());
		}
	}

	public NameListMSG(String str) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String flatten() {
		String str=Identifer+":";
		for(String s:nameList){
			str+=s+":";
		}
		return str;
	}

	
}

