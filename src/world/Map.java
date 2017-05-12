package world;

import java.util.ArrayList;

import utility.ColorStrings;

public class Map {
	
	public static int MAPW = 50;
	public static int MAPH = 50;
	MapTile[][] map = new MapTile[MAPW][MAPH];
	String defaultBack = "::";
	
	
	public boolean initMap() {
		for (int x = 0; x < MAPW; x++){
			for (int y = 0; y < MAPH; y++){
				map[x][y] = new MapTile(defaultBack);
			}
		}
		return true;
	}
	
	public boolean setMapPoint(int x, int y, int fcolor, int bcolor, boolean bold, String str){
		if (str.length() != 2){
			return false;
		}

		map[x][y] = new MapTile(fcolor, bcolor, bold, str);
		return true;
	}
	
	public boolean shiftMap(int x, int y){
		//TODO: set up the shift map
		return false;
	}
	
	public ArrayList<String> getFiveByFive(int x, int y){
		ArrayList<String> result = new ArrayList<String>();
		for (int i = (y-2); i < (y+3); i++){
			for (int k = (x-2); k < (x+3); k++){
				
				if (k < 0 || k > MAPW || i < 0 || i > MAPH){
					result.add(defaultBack);
				} else {
					result.add(map[k][i].getFormatedSymbol());
				}
			}
		}
		
		result.set(12, ColorStrings.getColoredText(true, ColorStrings.CYAN, ColorStrings.BLUE, "()"));
		return result;
	}

	public String getMapPoint(int x, int y){
		return map[x][y].getFormatedSymbol();
	}
	
	public boolean setMapPoint(int x, int y, String str) {
		map[x][y].setSymbol(str);
		return true;
	}
	
	public MapTile getMapTilePoint(int x, int y){
		return map[x][y];
	}

	public boolean setMapPoint(int x, int y, MapTile tile) {
		this.map[x][y] = tile;
		return true;
	}

}
