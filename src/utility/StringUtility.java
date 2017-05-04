package utility;

import java.util.ArrayList;

public class StringUtility {

	public static String getFirstWord(String str){
		return getWordInString(str, 1);
	}
	
	public static String getWordInString(String str, int i){
		String[] arr = str.split(" ");
		if (arr.length >= i){
			return arr[i-1];
		} else {
			return null;
		}
	}
	
	public static boolean compareStringInArray(String str, String[] arr){
		for (int i = 0; i < arr.length; i++){
			if (arr[i].equals(str)){
				return true;
			}
		}
		return false;
	}
	
	public static String getStringAfterFirst(String str){
		String[] arr = str.split(" ");
		String result = null;
		for (int i = 1; i < arr.length; i ++){
			if (i == 1){result = arr[1];} else {result = result + arr[i];}
		}
		return result;
	}
	
	public static String[] getWordList(String str){
		String[] arr = str.split(" ");
		
		//return the spaces i just removed
		for (int i = 0; i < arr.length; i++){
			arr[i] = arr[i] + " ";
		}
		
		ArrayList<String> strings = new ArrayList<String>();
		boolean finished = false;
		int i = 0;
		while (!finished){
			if (arr[i].contains("\"")){
				//special notice, that weird space i have to add to keep things working
				if (arr[i].startsWith("\"") && arr[i].endsWith("\" ")){
					strings.add(arr[i]);
				} else {
					boolean subfinished = false;
					String substring = arr[i];
					i++;
					while (!subfinished){
						if (arr[i].contains("\"")){
							substring = substring + arr[i];
							subfinished = true;
						} else {
							substring = substring + arr[i];
							i++;
						}
					}
					strings.add(substring);
				}
			} else {
				strings.add(arr[i]);
			}
			
			i++;
			if (i == arr.length){
				finished = true;
			}
		}
		
		String[] complete = strings.toArray(new String[strings.size()]);
		
		//strip extra spaces
		for (int k = 0; k < complete.length; k++){
			complete[k] = complete[k].substring(0, complete[k].length() - 1);
		}
		
		return complete;
	}
	
	public static String[] getWordListWithoutQuotes(String str){
		String[] temp = getWordList(str);
		for (int i = 0; i < temp.length; i++){
			if (temp[i].equals("\"\"")){
				temp[i] = null;
			} else if (temp[i].contains("\"")){
				temp[i] = temp[i].substring(1, temp[i].length() - 1);
			}
		}
		return temp;
	}
}
