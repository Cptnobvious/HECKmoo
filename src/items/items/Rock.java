package items.items;

import items.Item;

public class Rock extends Item{
	
	public Rock(){
		super();
		name = "Rock";
		plural = "Rocks";
		description = "It is a rock.";
		addVerb(new Drop());
	}
	
}
