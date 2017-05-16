package looks;

import items.Item;

import java.util.ArrayList;

import playermanager.Player;

public class LookInventory {
	
	public static String lookAtInventory(Player ply){
		String inventory = "Here's your inventory:\n";
		ArrayList<Item> items = new ArrayList<Item>();
		items = ply.getActor().getInventory();
		
		for (int i = 0; i < items.size(); i++){
			inventory = inventory + items.get(i).getName() + " (" + items.get(i).getWeight() + ")\n";
		}
		
		return inventory;
	}

}
