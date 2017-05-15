package chat.channels;

import chat.ChatChannel;

public class ChatNet extends ChatChannel{

	public boolean setChannelName() {
		channelName = "CHAT";
		return true;
	}

	public boolean setChannelNameColor() {
		channelNameColor = "$GREEN";
		return true;
	}

	public String getHelpText() {
		return null;
	}

	@Override
	public boolean setAlias() {
		String[] temp = {"chatnet", "cnet"};
		alias = temp;
		return true;
	}

}
