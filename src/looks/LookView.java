package looks;

import items.Item;

import java.util.ArrayList;

import playermanager.Player;
import playermanager.PlayerController;
import utility.ColorStrings;
import utility.StringUtility;
import world.Room;
import world.World;
import world.Zone;

public class LookView {
	
	private Player ply = null;
	private Zone zn = null;
	private Room rm = null;
	private static final String SPACER = "     ";
	
	public LookView(Player ply){
		this.ply = ply;
		zn = World.getZoneByID(ply.getActor().getCurrentZone());
		rm = World.getRoom(zn.getZoneID(), ply.getActor().getCurrentRoom());
	}
	
	public String getWhatYouSee(){
		String sees = "";
		ArrayList<String> map = zn.getMap().getFiveByFive(rm.getMapX(), rm.getMapY());
		
		//Build the description
		String[] desc = getRoomDescriptionWithAdds().split(" ");
		
		//Break the description into 60 character lines
		ArrayList<String> descLines = new ArrayList<String>();
		String descWithAdds = "";
		for (int i = 0; i < desc.length; i++){
			descWithAdds = descWithAdds + desc[i] + " ";
			if (descWithAdds.length() > 60 && i < 5){
				descLines.add(descWithAdds);
				descWithAdds = "";
			} else if (descWithAdds.length() > 75 && i >= 5){
				descLines.add(descWithAdds);
				descWithAdds = "";
			}
		}
		if (!descWithAdds.equals("")){
			descLines.add(descWithAdds);
		}
		
		//Stick the map into an array
		ArrayList<String> maparr = new ArrayList<String>();
		String mapline = "";
		for (int i = 0; i < 5; i++){
			mapline = "";
			for (int k = 0; k < 5; k++){
				mapline = mapline + map.get(k + i*5);
			}
			maparr.add(mapline);
		}
		
		//Put the name of the area into a nice format
		String areaName = SPACER + ColorStrings.getColoredText(true, ColorStrings.YELLOW, ColorStrings.BLACK, rm.getRoomName()) +
				ColorStrings.getColoredText(false, ColorStrings.YELLOW, ColorStrings.BLACK, " (" + zn.getZoneName() + ")");
		
		//Get a list of everything standing there
		String standing = "You see ";
		ArrayList<String> standinghere = new ArrayList<String>();
		//Add the players first
		ArrayList<Player> players = PlayerController.getPlayersListCopy();
		for (int i = 0; i < players.size(); i++){
			Player tPlayer = players.get(i);
			if (tPlayer.getActor() != null){
				if (ply.getActor().getCurrentRoom() == tPlayer.getActor().getCurrentRoom() && ply.getActor().getCurrentZone().equals(tPlayer.getActor().getCurrentZone())){
					if (ply.getuID() != tPlayer.getuID()){
						String tName = ColorStrings.getBoldText(tPlayer.getActor().getName());
						standinghere.add(tName);
					}
				}
			}
		}
		
		//Get a list of everything laying on the floor
		String onFloor = "";
		ArrayList<String> onFloorList = new ArrayList<String>();
		ArrayList<Item> inv = World.getRoomByPlayer(ply).getInventoryList();
		for (int i = 0; i < inv.size(); i++){
			onFloorList.add(inv.get(i).getName());
		}
		if (!onFloorList.isEmpty()) {
			onFloor = "Floor: ";
			for (int i = 0; i < onFloorList.size(); i++){
				onFloor = onFloor + onFloorList.get(i) + " ";
			}
		}
				
		//Add those things together
		if (standinghere.size() == 0){
			standing = "";
		} else if (standinghere.size() == 1){
			standing = standing + standinghere.get(0) + " standing here.";
		} else if (standinghere.size() == 2){
			standing = standing + standinghere.get(0) + " and " + standinghere.get(1) + " standing here.";
		} else if (standinghere.size() > 2){
			for (int i = 0; i < standinghere.size(); i++){
				if (i < standinghere.size() - 1){
					standing = standing + standinghere.get(i) + ", ";
				} else {
					standing = standing + "and " + standinghere.get(i) + " are standing here.";
				}
			}
		}
		
		//Put it all together
		sees = maparr.get(0) + areaName + "\n";
		int dline = 0;
		for (int i = 1; i < 5; i++){
			sees = sees + maparr.get(i);
			if (dline < descLines.size()){
				sees = sees + SPACER + descLines.get(dline);
				dline++;
			}
			sees = sees + "\n";
		}
		
		if (dline < descLines.size()){
			for (int i = dline; i < descLines.size(); i++){
				sees = sees + descLines.get(i) + "\n";
			}
		}
		
		//Add in the standing here
		sees = sees + "\n" + onFloor;
		sees = sees + "\n" + standing;
		//Exits now
		String exits = rm.getExitNames();
		exits = orderExits(exits);
		
		sees = sees + "\n" + ColorStrings.getColoredText(false, ColorStrings.CYAN, ColorStrings.BLACK, exits);
		
		return sees;
	}
	
	private String orderExits(String exits){
		String ordered = "";
		String[] arr = StringUtility.getWordList(exits);
		String temp = "";
		
		//First find what's in this array
		temp = temp + arr[0];
		
		if (StringUtility.arrayContains(arr, "north")){
			temp = temp + " north";
		}
		if (StringUtility.arrayContains(arr, "south")){
			temp = temp + " south";
		}
		if (StringUtility.arrayContains(arr, "east")){
			temp = temp + " east";
		}
		if (StringUtility.arrayContains(arr, "west")){
			temp = temp + " west";
		}
		if (StringUtility.arrayContains(arr, "northwest")){
			temp = temp + " northwest";
		}
		if (StringUtility.arrayContains(arr, "northeast")){
			temp = temp + " northeast";
		}
		if (StringUtility.arrayContains(arr, "southwest")){
			temp = temp + " southwest";
		}
		if (StringUtility.arrayContains(arr, "southeast")){
			temp = temp + " southeast";
		}
		if (StringUtility.arrayContains(arr, "up")){
			temp = temp + " up";
		}
		if (StringUtility.arrayContains(arr, "down")){
			temp = temp + " down";
		}
		if (StringUtility.arrayContains(arr, "in")){
			temp = temp + " in";
		}
		if (StringUtility.arrayContains(arr, "out")){
			temp = temp + " out";
		}
		
		String[] special = {"north", "south", "east", "west", "northwest", "northeast", "southwest", "southeast", "up", "down", "in", "out"};
		
		//strip those exits already used to null
		arr[0] = null;
		for (int i = 1; i < arr.length; i++){
			for (int j = 0; j < special.length; j++){
				if (arr[i].equals(special[j])){
					arr[i] = null;
					break;
				}
			}
		}
		
		//add all the non-null exits
		for (int i = 0; i < arr.length; i++){
			if (arr[i] != null){
				temp = temp + " " + arr[i];
			}
		}
		
		ordered = temp;
		
		return ordered;
	}
	
	private String getRoomDescriptionWithAdds(){
		String description = rm.getRoomDescription();
		return description;
	}
}
