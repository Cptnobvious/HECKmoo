package items.prototype;

import items.Item;

import java.util.ArrayList;

public class PrototypeList{

	private static ArrayList<Prototype> prototypes = new ArrayList<Prototype>();
	
	public static boolean addPrototype(Prototype prototype){
		if (getPrototypeByName(prototype.getName()) == null){
			prototypes.add(prototype);
			return true;
		}
		
		
		return false;
	}
	
	public static Prototype getPrototypeByName(String name){
		for (int i = 0; i < prototypes.size(); i++){
			if (prototypes.get(i).getAttribute(Item.ITEMNAME).sGetValue().equals(name)){
				//Get a copy not the real thing
				Prototype copy = new Prototype(prototypes.get(i));
				return copy;
			}
		}
		
		return null;
	}
	
}
