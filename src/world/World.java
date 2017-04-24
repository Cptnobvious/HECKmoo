package world;

import java.util.ArrayList;

import debug.MakeFakeZone;

public class World {
	
	private static ArrayList<Zone> zones = new ArrayList<Zone>();
	
	
	public boolean init(){
		addZone(MakeFakeZone.getFakeZone());
		return false;
	}
	
	public boolean addZone(Zone zone){
		zones.add(zone);
		return true;
	}

}
