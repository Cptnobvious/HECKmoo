package chat;

public class ChatMessage {

	String channel = null;
	String text = null;
	String speaker = null;
	
	public ChatMessage(String chan, String speak, String text){
		this.channel = chan;
		this.speaker = speak;
		this.text = text;
	}

	public String getChannel() {
		return channel;
	}

	public String getText() {
		return text;
	}

	public String getSpeaker() {
		return speaker;
	}
	
	public String getMessage(){
		return channel + " " + speaker + ": " + text;
	}
	
}
