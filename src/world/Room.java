package world;

import items.InventoryInterface;
import items.Item;

import java.util.ArrayList;

public class Room implements InventoryInterface{

	private String roomName			= "No name yet";
	private String roomDescription 	= "Tell an admin this needs to be described";
	private int mapx				= 20;
	private int mapy				= 20;
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

	public boolean addExit(Exit exit){
		exits.add(exit);
		return true;
	}
	
	public boolean removeExit(String str){
		for (int i = 0; i < exits.size(); i++){
			if (exits.get(i).matches(str)){
				exits.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Exit> getExits() {
		return exits;
	}

	public Exit getExitByName(String str){
		for (int i = 0; i < exits.size(); i++){
			if (exits.get(i).matches(str)){
				return exits.get(i);
			}
			if (exits.get(i).matchespartial(str)){
				return exits.get(i);
			}
		}
		return null;
	}
	
	public void setExits(ArrayList<Exit> exits) {
		//TODO: remake this it's auto generated
	}
	
	public int getMapX(){
		return this.mapx;
	}
	
	public int getMapY(){
		return this.mapy;
	}
	
	public boolean setMapPos(int x, int y){
		this.mapx = x;
		this.mapy = y;
		return true;
	}
	
	public String getExitNames(){
		String ex = "[Exits: ";
		for (int i = 0; i < exits.size(); i++){
			ex = ex + exits.get(i).getName() + " ";
		}
		ex = ex + "]";
		return ex;
	}

	
	//------------------------------------------------------------------------------------------------
	//-------------------------------Inventory Stuff Down Here ---------------------------------------
	//------------------------------------------------------------------------------------------------
	
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

	public ArrayList<Item> getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	public Item getItem(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getInventoryWeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
