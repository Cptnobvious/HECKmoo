package world;

import java.awt.Color;
import java.util.ArrayList;

import utility.ColorStrings;

public class Map {
	
	public static int MAPW = 50;
	public static int MAPH = 50;
	MapTile[][] map = new MapTile[MAPW][MAPH];
	String defaultBack = "::";
	
	
	public boolean initMap() {
		for (int x = 0; x < 51; x++){
			for (int y = 0; y < 51; y++){
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
		for (int i = (x-2); i < (x+3); i++){
			for (int k = (y-2); k < (y+3); k++){
				
				if (k < 0 || k > 51 || i < 0 || i > 51){
					result.add(defaultBack);
				} else {
					result.add(map[i][k].getFormatedSymbol());
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

}
