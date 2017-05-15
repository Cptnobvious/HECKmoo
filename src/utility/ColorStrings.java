package utility;

public class ColorStrings {
	
	public static final char ESCAPECHAR = 27;
	
	public static final int BLACK 	= 0;
	public static final int RED 	= 1;
	public static final int GREEN	= 2;
	public static final int YELLOW	= 3;
	public static final int BLUE	= 4;
	public static final int MAGENTA = 5;
	public static final int CYAN	= 6;
	public static final int WHITE	= 7;
	
	public static final int ATTRIBUTESOFF = 0;
	public static final int BOLD = 1;
	public static final int UNDERSCORE = 4;
	
	public static String getColorResetCode(){
		return ESCAPECHAR + "[" + ATTRIBUTESOFF + "m";
	}
	
	public static String getBoldCode(){
		return ESCAPECHAR + "[" + BOLD + "m";
	}
	
	public static String getColorFCode(int color){
		//30 isn't magic it's the ANSI code
		int code = 30 + color;
		
		return ESCAPECHAR + "[" + code + "m";
	}
	
	public static String getColorBCode(int color){
		//40 isn't magic it's the ANSI code
		int code = 40 + color;
		
		return ESCAPECHAR + "[" + code + "m";
	}
	
	public static String getColorCodes(int fColor, int bColor){
	
		return ESCAPECHAR + "[" + (fColor + 30) + ";" + (bColor + 40) + "m";
	
	}
	
	public static String getColoredText(boolean bold, int fColor, int bColor, String str){
		if (bold){
			return getBoldCode() + getColorCodes(fColor, bColor) + str + getColorResetCode();
		} else {
			return getColorCodes(fColor, bColor) + str + getColorResetCode();
		}
	}
	
	public static String getColoredText(int fColor, int bColor, String str){
		return getColoredText(false, fColor, bColor, str);
	}
	
	public static String getColoredText(int fColor, String str){
		return getColoredText(false, fColor, BLACK, str);
	}
	
	public static String getBoldText(String str){
		return getBoldCode() + str + getColorResetCode();
	}
	
	public static String replaceTags(String str){
		
		//Check to make sure there's a tag
		if (str.contains("[") || str.contains("]")){
			//Do foreground tags
			str = str.replace("$[fBLACK]", 		ColorStrings.getColorFCode(ColorStrings.BLACK));
			str = str.replace("$[fRED]", 		ColorStrings.getColorFCode(ColorStrings.RED));
			str = str.replace("$[fGREEN]", 		ColorStrings.getColorFCode(ColorStrings.GREEN));
			str = str.replace("$[fYELLOW]", 	ColorStrings.getColorFCode(ColorStrings.YELLOW));
			str = str.replace("$[fBLUE]", 		ColorStrings.getColorFCode(ColorStrings.BLUE));
			str = str.replace("$[fMAGENTA]",	ColorStrings.getColorFCode(ColorStrings.MAGENTA));
			str = str.replace("$[fCYAN]", 		ColorStrings.getColorFCode(ColorStrings.CYAN));
			str = str.replace("$[fWHITE]", 		ColorStrings.getColorFCode(ColorStrings.WHITE));
			
			//Now for the background tags
			str = str.replace("$[bBLACK]", 		ColorStrings.getColorBCode(ColorStrings.BLACK));
			str = str.replace("$[bRED]", 		ColorStrings.getColorBCode(ColorStrings.RED));
			str = str.replace("$[bGREEN]", 		ColorStrings.getColorBCode(ColorStrings.GREEN));
			str = str.replace("$[bYELLOW]", 	ColorStrings.getColorBCode(ColorStrings.YELLOW));
			str = str.replace("$[bBLUE]", 		ColorStrings.getColorBCode(ColorStrings.BLUE));
			str = str.replace("$[bMAGENTA]", 	ColorStrings.getColorBCode(ColorStrings.MAGENTA));
			str = str.replace("$[bCYAN]", 		ColorStrings.getColorBCode(ColorStrings.CYAN));
			str = str.replace("$[bWHITE]", 		ColorStrings.getColorBCode(ColorStrings.WHITE));
			
			//other tags
			str = str.replace("$[DEF]", ColorStrings.getColorResetCode());
			
			//Add a clear character
			str = str + ColorStrings.getColorResetCode();
		}
		
		return str;
	}
	
	public static String getPallet(){
		
		String line = "Standard:\n";
		
		for (int i = 0; i < 8; i++){
        	for (int j = 0; j < 8; j++){
        		line = line + ColorStrings.getColorCodes(i, j) + i + j;
        	}
        	
        	line = line + "\n";
        }
		
		line = line + getColorResetCode() + getBoldCode() + "\nBold Font\n";
		
		for (int i = 0; i < 8; i++){
        	for (int j = 0; j < 8; j++){
        		line = line + ColorStrings.getColorCodes(i, j) + i + j;
        	}
        	
        	line = line + "\n";
        }
		
		line = line + getColorResetCode() + getBoldCode() + "\nBold Font\n";
		
		line = line + getColorResetCode();
		
		return line;
	}
	
	public static int getColorIntByString(String color){
		if (color.equals("$BLACK")){
			return BLACK;
		}
		if (color.equals("$RED")){
			return RED;
		}
		if (color.equals("$GREEN")){
			return GREEN;
		}
		if (color.equals("$YELLOW")){
			return YELLOW;
		}
		if (color.equals("$BLUE")){
			return BLUE;
		}
		if (color.equals("$MAGENTA")){
			return MAGENTA;
		}
		if (color.equals("$CYAN")){
			return CYAN;
		}
		if (color.equals("$WHITE")){
			return WHITE;
		}
		return WHITE;
	}
	
}
