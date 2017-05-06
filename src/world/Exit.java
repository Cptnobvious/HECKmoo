package world;

import utility.StringUtility;

public class Exit {

	private String name = "Bad Exit";
	private String[] alias = {};
	private String zone = null;
	private int room = -1;
	
	public Exit(String name, String zone, int room){
		this.name = name;
		String[] names = {name};
		this.alias = names;
		this.zone = zone;
		this.room = room;
	}
	
	public boolean matches(String str){
		return StringUtility.compareStringInArray(str, this.alias);
	}
	
	public boolean addAlias(String str){
		alias[alias.length + 1] = str;
		return true;
	}
	
	public boolean removeAlias(String str){
		int i = 0;
		int k = 0;
		String[] temp = {};
		while (i < alias.length){
			if (!alias[i].equals(str)){
				temp[k] = alias[i];
				k++;
				i++;
			} else {
				i++;
			}
		}
		alias = temp;
		return true;
	}
	
	public boolean setZone(String str){
		if (World.getZoneByID(str) != null){
			this.zone = str;
			return true;
		}
		return false;
	}
	
	public String getZone(){
		return this.zone;
	}
	
	public boolean setRoom(int index){
		if (World.getRoom(this.zone, index) != null){
			this.room = index;
			return true;
		}
		return false;
	}
	
	public int getRoom(){
		return this.room;
	}
	
	public boolean isGoodExit(){
		if (World.getRoom(this.zone, this.room) != null){
			return true;
		}
		return false;
	}
	
	public String getName(){
		return this.name;
	}
}
