package world;

import java.util.ArrayList;

import playermanager.Player;
import utility.ColorStrings;
import world.prefabs.Origin;
import debug.MakeFakeZone;

public class World {
	
	private static ArrayList<Zone> zones = new ArrayList<Zone>();
	
	public static boolean init(){
		addZone(Origin.getOriginZone());
		return false;
	}
	
	public static Zone getZoneByID(String id){
		for (int i = 0; i < zones.size(); i++){
			if (zones.get(i).getZoneID().equals(id)){
				return zones.get(i);
			}
		}
		return null;
	}
	
	public static Zone getZoneByIndex(int i){
		return zones.get(i);
	}

	public static int getZoneIndexByID(String id){
		for (int i = 0; i < zones.size(); i++){
			if (zones.get(i).getZoneID().equals(id)){
				return i;
			}
		}
		
		return -1;
	}
	
	public static boolean addZone(Zone zone){
		for (int i = 0; i < zones.size(); i++){
			if (zones.get(i).getZoneID().equals(zone.getZoneID())){
				return false;
			}
		}
		zones.add(zone);
		return true;
	}

	public static Room getRoom(String zone, int index){
		if (getZoneByID(zone) != null){
			return getZoneByID(zone).getRoomByIndex(index);
		}
		return null;
	}
	
	public static Zone getZoneByPlayer(Player ply){
		return getZoneByID(ply.getActor().getCurrentZone());
	}
	
	public static Room getRoomByPlayer(Player ply){
		return getRoom(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom());
	}
	
	
	//TODO: shuffle some of this into classes. Pretty sure it doesn't all belong here
	public static String getRoomLook(String zone, int index){
		Zone zn = getZoneByID(zone);
		Room rm = getRoom(zone, index);
		ArrayList<String> map = zn.getMap().getFiveByFive(rm.getMapX(), rm.getMapy());
		String spacer = "     ";
		String look = "";
		
		//Break the description into 60 character lines
		ArrayList<String> descLines = new ArrayList<String>();
		String[] description =  rm.getRoomDescription().split(" ");
		String temp = "";
		for (int i = 0; i < description.length; i++){
			temp = temp + description[i] + " ";
			if (temp.length() > 60 && i < 5){
				descLines.add(temp);
				temp = "";
			} else if (temp.length() > 75 && i >= 5){
				descLines.add(temp);
				temp = "";
			}
		} 
		if (!temp.equals("")) {
			descLines.add(temp);
		}
		
		//Put the map into an easy to use array
		ArrayList<String> maparr = new ArrayList<String>();
		for (int i = 0; i < 5; i++){
			temp = "";
			for (int k = 0; k < 5; k++){
				temp = temp + map.get(k + i*5);
			}
			maparr.add(temp);
		}
		
		//Put the name of the area into a nice format
		String areaName = spacer + ColorStrings.getColoredText(true, ColorStrings.YELLOW, ColorStrings.BLACK, rm.getRoomName()) +
				ColorStrings.getColoredText(false, ColorStrings.YELLOW, ColorStrings.BLACK, " (" + zn.getZoneName() + ")");
		
		//Throw it together
		look = maparr.get(0) +  areaName + "\n";
		int dline = 0;
		for (int i = 1; i < 5; i++){
			look = look + maparr.get(i);
			if (dline < descLines.size()){
				look = look + spacer + descLines.get(dline);
				dline++;
			}
			look = look + "\n";
		}
		
		if (dline < descLines.size()){
			for (int i = dline; i < descLines.size(); i++){
				look = look + descLines.get(i) + "\n";
			}
		}
		
		//Throw in the exits
		String exits = rm.getExitNames();
		look = look + "\n\n" + ColorStrings.getColoredText(false, ColorStrings.CYAN, ColorStrings.BLACK, exits);
		
		return look;
	}
}
