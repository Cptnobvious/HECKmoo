package items;

import java.util.ArrayList;

public class Inventory {

	ArrayList<Item> inventory = new ArrayList<Item>();
	
	public boolean addItem(Item item){
		inventory.add(item);
		return true;
	}
	
	public boolean removeItem(String name){
		for (int i = 0; i < inventory.size(); i++){
			if (inventory.get(i).getName().equals(name)){
				inventory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeItem(Item item){
		for (int i = 0; i < inventory.size(); i++){
			if (inventory.get(i) == item){
				inventory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public Item getItem(String name){
		for (int i = 0; i < inventory.size(); i++){
			if (inventory.get(i).getName().equals(name)){
				return inventory.get(i);
			}
		}
		return null;
	}
	
	public double getWeight(){
		double weight = 0;
		for (int i = 0; i < inventory.size(); i++){
			weight = weight + inventory.get(i).getWeight();
		}
		return weight;
	}
	
	public ArrayList<Item> getInventoryCopy(){
		ArrayList<Item> copy = this.inventory;
		return copy;
	}
	
}
