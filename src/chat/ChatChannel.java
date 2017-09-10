package chat;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;

public abstract class ChatChannel extends Verb{

	protected String channelName = null;
	protected String channelNameColor = "$WHITE";
	
	abstract public boolean setChannelName();
	abstract public boolean setChannelNameColor();
	
	public ChatChannel(){
		super();
		setChannelName();
		setChannelNameColor();
		setAlias();
		ChatMaster.addChannel(this);
	}
	
	public boolean setFlags(){
		return true;
	}
	
	public boolean run(Player ply, String str){
		if (channelName == null){
			return false;
		}
		
		String chan = ColorStrings.getColoredText(ColorStrings.getColorIntByString(channelNameColor), ColorStrings.getBoldText("[" + channelName + "]") );
		
		ChatMaster.sendChat(new ChatMessage(chan, ply.getActor().getName(), StringUtility.getStringAfterFirst(str)));
		return true;
	}
	
	public String getName(){
		return this.channelName;
	}
	
}
