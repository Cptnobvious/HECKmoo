package playermanager;

public class Account {
	
	private String accountName = null;
	private String accountPassword = null;
	
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
}
