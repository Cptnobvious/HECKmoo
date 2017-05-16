package items;

import java.util.ArrayList;

import verb.Verb;

public class Inventory {

	ArrayList<Item> inventory = new ArrayList<Item>();
	
	public boolean addItem(Item item){
		inventory.add(item);
		return true;
	}
	
	//returns item removed or null
	public Item removeItem(String name){
		for (int i = 0; i < inventory.size(); i++){
			if (inventory.get(i).getName().equals(name)){
				return inventory.remove(i);
			}
		}
		return null;
	}
	
	public Item removeItem(Item item){
		for (int i = 0; i < inventory.size(); i++){
			if (inventory.get(i) == item){
				return inventory.remove(i);
			}
		}
		return null;
	}
	
	public Item getItem(String name){
		for (int i = 0; i < inventory.size(); i++){
			if (inventory.get(i).getName().equalsIgnoreCase(name)){
				return inventory.get(i);
			}
		}
		for (int i = 0; i < inventory.size(); i++){
			if (inventory.get(i).getName().toLowerCase().contains(name.toLowerCase())){
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
	
	public int getInvetorySize(){
		return inventory.size();
	}
	
}
