package looks;

import items.Item;

import java.util.ArrayList;

import playermanager.Player;

public class LookInventory {
	
	public static String lookAtInventory(Player ply){
		String inventory = "Here's your inventory:\n";
		ArrayList<Item> items = new ArrayList<Item>();
		items = ply.getActor().getInventoryList();
		
		for (int i = 0; i < items.size(); i++){
			inventory = inventory + items.get(i).getName() + " (" + items.get(i).getWeight() + "kg)\n";
		}
		
		return inventory;
	}

	public static String lookThroughInventory(ArrayList<Item> items, String target){
		String description = "I couldn't find that";
		for (int i = 0; i < items.size(); i++){
			if (items.get(i).getName().equalsIgnoreCase(target)){
				description = items.get(i).getDescription();
			}
		}
		for (int i = 0; i < items.size(); i++){
			if (items.get(i).getName().toLowerCase().contains(target.toLowerCase())){
				description = items.get(i).getDescription();
			}
		}
		
		return description;
	}
}
