package playermanager;

import world.World;

//This is the thing that the player controls

public class Actor {
	//Owner's uID
	private int parentID = -1;
	
	private String name = "WHERESMYNAME";
	
	//Which zone is this actor standing in
	private String currentZone = "ORGNA";
	//Which room in that zone is this actor standing in
	private int currentRoom = 0;

	Actor(int id, String name){
		this.parentID = id;
		this.name = name;
	}
	
	public boolean setCurrentZone(String str){
		if (World.getZoneByID(str) != null){
			this.currentZone = str;
			return true;
		}
		return false;
	}
	
	public String getCurrentZone(){
		return this.currentZone;
	}
	
	public boolean setCurrentRoom(int index){
		if (World.getRoom(currentZone, index) != null){
			this.currentRoom = index;
			return true;
		}
		return false;
	}
	
	public boolean setCurrentRoom(String str, int index){
		if (World.getRoom(str, index) != null){
			this.currentZone = str;
			this.currentRoom = index;
			return true;
		}
		return false;
	}
	
	public int getCurrentRoom(){
		return this.currentRoom;
	}
	
	public String getLocationCode(){
		return this.getCurrentZone() + ":" + this.getCurrentRoom();
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean setName(String name){
		this.name = name;
		return true;
	}
}
