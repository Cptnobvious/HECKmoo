package world;

import items.Inventory;
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
		for (int o = 0; o < exits.size(); o++){
			if (exits.get(o).matchespartial(str)){
				return exits.get(o);
			}
		}
		}
		return null;
	}
	
	public Exit getExitByExactName(String str){
		for (int i = 0; i < exits.size(); i++){
			if (exits.get(i).matches(str)){
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
	
	Inventory inventory = new Inventory();
	public static final int maxItemsOnGround = 50;
	
	public boolean addItem(Item item) {
		if (inventory.getInvetorySize() + 1 <= 50){
			inventory.addItem(item);
		}
		return false;
	}

	public Item removeItem(Item item) {
		return inventory.removeItem(item);
	}

	public Item removeItem(String name) {
		return inventory.removeItem(name);
	}

	public ArrayList<Item> getInventoryList() {
		return inventory.getInventoryCopy();
	}
	
	public Inventory getInventory(){
		return inventory;
	}

	public Item getItem(String name) {
		return inventory.getItem(name);
	}

	public double getInventoryWeight() {
		return inventory.getWeight();
	}

	public boolean canTake(Item item) {
		return (inventory.getInvetorySize() < 50);
	}

	public boolean canDrop(Item item) {
		return true;
	}
	
}
