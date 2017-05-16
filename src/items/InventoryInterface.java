package items;

import java.util.ArrayList;

import verb.Verb;

public interface InventoryInterface {
	
	public boolean addItem(Item item);
	public Item removeItem(Item item);
	public Item removeItem(String name);
	public ArrayList<Item> getInventory();
	public Item getItem(String name);
	public double getInventoryWeight();
	
	//Can you add this item to your inventory
	public boolean canTake(Item item);
	//Can you remove this item from your inventory
	public boolean canDrop(Item item);
	
}
