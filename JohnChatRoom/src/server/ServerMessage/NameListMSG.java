package ServerMessage;

import java.util.ArrayList;
import java.util.Collection;

public class NameListMSG extends Message{
	
	ArrayList<String> nameList;
	
	public NameListMSG(Collection<String> list){
		Identifer="List";
		nameList=(ArrayList<String>)list;
	}

	public NameListMSG(String str) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String flatten() {
		String str=Identifer;
		for(String s:nameList){
			str+=s+":";
		}
		return str;
	}

	
}

