package playermanager;

import java.util.ArrayList;

import items.Inventory;
import items.Item;
import world.World;

//This is the thing that the player controls

public class Actor implements Inventory{
	//Owner's uID
	private int parentID = -1;
	//Name of the player
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

	//---------------------------------------------------------------------------------------------
	//This section is for item stuff
	//---------------------------------------------------------------------------------------------
	
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private double carryingCapacity = 100;

	public boolean addItem(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeItem(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeItem(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeItem(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Item> getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	public Item getItem() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getInventoryWeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
