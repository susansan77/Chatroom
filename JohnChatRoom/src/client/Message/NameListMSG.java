package Message;

import java.util.ArrayList;
import java.util.Collection;

public class NameListMSG extends Message{
	
	ArrayList<String> nameList=new ArrayList<String>();
	
	public NameListMSG(Collection<String> list){
		Identifer="List";
		nameList=(ArrayList<String>)list;
	}
	public ArrayList<String> getList(){
		return this.nameList;
	}
	public NameListMSG(String str) {
		// TODO Auto-generated constructor stub
		Identifer="List";
		ArrayList<String> list=Message.getTokens(str);
		for(int i=1;i<list.size();i++){
			nameList.add(list.get(i));
		}
		
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

