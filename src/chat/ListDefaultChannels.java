package chat;

import chat.channels.ChatNet;
import verb.VerbList;

public class ListDefaultChannels extends VerbList{

	public ListDefaultChannels(){
		init();
	}
	
	public boolean init(){
		addVerb(new ChatNet());
		return true;
	}
	
}
