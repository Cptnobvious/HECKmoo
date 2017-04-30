package world;

import java.util.ArrayList;

public class Room {

	private String roomName			= "No name yet";
	private String roomDescription 	= "Tell an admin this needs to be described";
	private int index 				= -1;
	private ArrayList<Exit> exits 	= new ArrayList<Exit>(); 
	
	public Room(){
		//Initialize this shit
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ArrayList<Exit> getExits() {
		return exits;
	}

	public void setExits(ArrayList<Exit> exits) {
		//TODO: remake this it's auto generated
	}
	
}
