package items;

import java.util.ArrayList;

public interface Inventory {
	
	public boolean addItem(Item item);
	public boolean removeItem(Item item);
	public boolean removeItem(String name);
	public boolean removeItem(int index);
	public ArrayList<Item> getInventory();
	public Item getItem();
	public double getInventoryWeight();
	
}
