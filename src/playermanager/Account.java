package playermanager;

import java.util.ArrayList;

public class Account {
	
	private String accountName = null;
	private String accountPassword = null;
	private ArrayList<String> accountFlags = new ArrayList<String>();
	private ArrayList<String> subscribedChannels = new ArrayList<String>();
	
	Account (String name, String password){
		this.accountName = name;
		this.accountPassword = password;
		subscribedChannels.add("CHAT");
	}
	
	public String getAccountName(){
		return this.accountName;
	}
	
	public String getAccountPassword(){
		return this.accountPassword;
	}

	public boolean setAccountName(String name){
		this.accountName = name;
		return true;
	}
	
	public boolean setAccountPassword(String pass){
		this.accountPassword = pass;
		return true;
	}
	
	public boolean giveFlag(String flag){
		accountFlags.add(flag);
		return true;
	}
	
	public boolean hasFlag(String flag){
		for (int i = 0; i < accountFlags.size(); i++){
			if (accountFlags.get(i).equals(flag)){
				return true;
			}
		}
		return false;
	}
	
	public String getFlags(){
		String list = "";
		for (int i = 0; i < accountFlags.size(); i++){
			list = list + accountFlags.get(i) + " ";
		}
		return list;
	}
	
	public boolean turnChannelOn(String chan){
		boolean exists = false;
		for (int i = 0; i < subscribedChannels.size(); i++){
			if (subscribedChannels.get(i).equals(chan)){
				exists = true;
			}
		}
		
		if (!exists){
			subscribedChannels.add(chan);
		}
		
		return true;
	}
	
	public boolean turnChannelOff(String chan){
		for (int i = 0; i < subscribedChannels.size(); i++){
			if (subscribedChannels.get(i).equals(chan)){
				subscribedChannels.remove(i);
				break;
			}
		}

		return true;
	}
}
