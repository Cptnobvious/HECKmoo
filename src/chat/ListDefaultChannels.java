package chat;

import chat.channels.ChatNet;
import chat.channels.ErrorNet;
import verb.VerbList;

public class ListDefaultChannels extends VerbList{

	public ListDefaultChannels(){
		init();
	}
	
	public boolean init(){
		addVerb(new ChatNet());
		addVerb(new ErrorNet());
		return true;
	}
	
}
