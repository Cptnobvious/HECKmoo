package items;

import java.util.ArrayList;

public interface InventoryInterface {
	
	public boolean addItem(Item item);
	public boolean removeItem(Item item);
	public boolean removeItem(String name);
	public ArrayList<Item> getInventory();
	public Item getItem(String name);
	public double getInventoryWeight();
	
}
