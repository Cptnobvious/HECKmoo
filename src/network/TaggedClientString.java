package network;

class TaggedClientString {

	//the uID of the client
	private int uID;
	//The string
	private String str;
	
	public TaggedClientString(int uID, String str){
		this.uID = uID;
		this.str = str;
	}
	
	public int getUID(){
		return this.uID;
	}
	
	public String getString(){
		return this.str;
	}
	
}
