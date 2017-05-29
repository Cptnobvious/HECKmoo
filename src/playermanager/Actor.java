package playermanager;

import java.util.ArrayList;

import script.ScriptInterface;
import script.attributes.Attribute;
import script.attributes.AttributeList;
import script.script.HeckScript;
import script.script.ScriptList;
import items.Inventory;
import items.InventoryInterface;
import items.Item;
import world.World;

//This is the thing that the player controls

public class Actor implements InventoryInterface, ScriptInterface{
	//Owner's uID
	private int parentID = -1;
	
	//The zone the player is in
	private static final String ZONE = "_ZONE";
	//The room in the zone the player is in
	private static final String ROOM = "_ROOM";
	//The player's name
	private static final String NAME = "_NAME";

	Actor(int id, String name){
		this.parentID = id;
		setAttribute(ZONE, "ORGNA");
		setAttribute(ROOM, 0);
		setAttribute(NAME, name);
	}
	
	public boolean setCurrentZone(String str){
		if (World.getZoneByID(str) != null){
			setAttribute(ZONE, str);
			return true;
		}
		return false;
	}
	
	public String getCurrentZone(){
		return getAttribute(ZONE).sGetValue();
	}
	
	public boolean setCurrentRoom(int index){
		if (World.getRoom(getCurrentZone(), index) != null){
			setAttribute(ROOM, index);
			return true;
		}
		return false;
	}
	
	public boolean setCurrentRoom(String str, int index){
		if (World.getRoom(str, index) != null){
			setAttribute(ZONE, str);
			setAttribute(ROOM, index);
			return true;
		}
		return false;
	}
	
	public int getCurrentRoom(){
		return getAttribute(ROOM).iGetValue();
	}
	
	public String getLocationCode(){
		return this.getCurrentZone() + ":" + this.getCurrentRoom();
	}
	
	public String getName(){
		return getAttribute(NAME).sGetValue();
	}
	
	public boolean setName(String name){
		setAttribute(NAME, name);
		return true;
	}
	
	public boolean sendMessageToPlayer(String str){
		return PlayerController.getPlayerByUID(this.parentID).sendMessageToClient(str);
	}

	//---------------------------------------------------------------------------------------------------------------------
	//-------------------------------------This section is for item stuff--------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------
	
	Inventory inventory = new Inventory();
	private double carryingCapacity = 100;

	public boolean addItem(Item item) {
		if (inventory.getWeight() + item.getWeight() < carryingCapacity){
			inventory.addItem(item);
			return true;
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
	
	public boolean canTake(Item item){
		return false;
	}
	
	public boolean canDrop(Item item){
		return false;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	//-------------------------------------This section is for script stuff------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------
	
	private AttributeList 	attributes 	= new AttributeList();
	private ScriptList 		scripts 	= new ScriptList();
	
	public boolean setAttribute(String name, int i) {
		return attributes.setAttribute(name, i);
	}

	public boolean setAttribute(String name, double d) {
		return attributes.setAttribute(name, d);
	}

	public boolean setAttribute(String name, String str) {
		return attributes.setAttribute(name, str);
	}

	public boolean setAttribute(String name, boolean b) {
		return attributes.setAttribute(name, b);
	}

	public Attribute getAttribute(String name) {
		return attributes.getByName(name);
	}

	public ArrayList<Attribute> getAttributeList() {
		return attributes.getAttributeList();
	}

	public boolean addScript(HeckScript script) {
		return scripts.addScript(script);
	}

	public boolean removeScript(String name) {
		return scripts.removeScript(name);
	}

	public HeckScript getScript(String name) {
		return scripts.getScript(name);
	}

	public boolean scriptExists(String name) {
		return scripts.scriptExists(name);
	}

	public boolean isScriptCompiled(String name) {
		return scripts.isScriptCompiled(name);
	}

	public ArrayList<String> getScriptNames() {
		return scripts.getScriptNames();
	}
	
}
