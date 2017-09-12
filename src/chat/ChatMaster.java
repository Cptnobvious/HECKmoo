package chat;

import java.util.ArrayList;

import playermanager.Player;
import playermanager.PlayerController;
import verb.Verb;

//Decides where to send things and when

public class ChatMaster {

	private static ArrayList<ChatChannel> channels = new ArrayList<ChatChannel>();
	
	public static boolean addChannel(ChatChannel chan){
		for (int i = 0; i < channels.size(); i++){
			if (channels.get(i).getName().equals(chan.getName())){
				return false;
			}
		}
		channels.add(chan);
		return true;
	}
	
	public static boolean sendChat(ChatMessage chat){
		//TODO: this sends to all
		ArrayList<Player> players = PlayerController.getPlayersListCopy();
		ArrayList<Player> subscribed = new ArrayList<Player>();
		
		subscribed = players;
		
		for (int i = 0; i < subscribed.size(); i++){
			subscribed.get(i).sendMessageToClient(chat.getMessage());
		}
		return true;
	}
	
	
	public static boolean sendErrorChat(String str){
		ChatMessage chat = new ChatMessage("ERROR", "SYSTEM", str);
		return sendChat(chat);
	}
	
	public static boolean doesChannelExist(String str){
		for (int i = 0; i < channels.size(); i++){
			if (channels.get(i).getName().equalsIgnoreCase(str)){
				return true;
			}
		}
		
		return false;
	}
}
