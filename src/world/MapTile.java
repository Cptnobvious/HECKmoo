package world;

import utility.ColorStrings;

public class MapTile {
	
	public static int PARTS = 4;
	String symbol = "::";
	int fColor = ColorStrings.WHITE;
	int bColor = ColorStrings.BLACK;
	boolean fBold = false;
	
	public MapTile(String symb){
		this.symbol = symb;
	}
	
	public MapTile(int fcolor, int bcolor, boolean bold, String str){
		this.fColor = fcolor;
		this.bColor = bcolor;
		this.fBold = bold;
		if (str.length() > 1){
			this.symbol = str.substring(0,2);
		}
	}
	
	public MapTile(int fcolor, int bcolor, int bold, String str){
		this.fColor = fcolor;
		this.bColor = bcolor;
		if (bold == 1){
			this.fBold = true;
		} else {
			this.fBold = false;
		}
		if (str.length() > 1){
			this.symbol = str.substring(0,2);
		}
	}
	
	public String getFormatedSymbol(){
		return ColorStrings.getColoredText(fBold, fColor, bColor, symbol);
	}
	
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public int getfColor() {
		return fColor;
	}
	
	public void setfColor(int fColor) {
		this.fColor = fColor;
	}
	
	public int getbColor() {
		return bColor;
	}
	
	public void setbColor(int bColor) {
		this.bColor = bColor;
	}
	
	public boolean isfBold() {
		return fBold;
	}
	
	public int getBoldForSave(){
		if (this.fBold){
			return 1;
		}
		
		return 0;
	}
	
	public void setfBold(boolean fBold) {
		this.fBold = fBold;
	}
	

}
