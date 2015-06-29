package controller;

import model.Client;

public class ChooseNickNameController {
	Client client;
	public ChooseNickNameController(String str){
		client=new Client(str);
	}
	public Client getClient(){
		return client;
	}
}
