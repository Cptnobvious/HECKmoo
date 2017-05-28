package chat.channels;

import chat.ChatChannel;

public class ErrorNet extends ChatChannel{

	@Override
	public boolean setChannelName() {
		channelName = "ERROR";
		return true;
	}

	@Override
	public boolean setChannelNameColor() {
		channelNameColor = "$RED";
		return true;
	}

	@Override
	public boolean setAlias() {
		String[] temp = {"errornet", "enet"};
		alias = temp;
		return true;
	}

	@Override
	public String getHelpText() {
		return null;
	}

}
