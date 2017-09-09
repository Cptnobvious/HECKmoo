package playermanager;

import java.util.ArrayList;

public class Account {
	
	private String accountName = null;
	private String accountPassword = null;
	private ArrayList<String> accountFlags = new ArrayList<String>();
	
	Account (String name, String password){
		this.accountName = name;
		this.accountPassword = password;
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
}
