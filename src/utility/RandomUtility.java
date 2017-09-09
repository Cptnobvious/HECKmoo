package utility;

import java.util.Random;

public class RandomUtility {
	
	private static final Random rand = new Random();
	
	public static boolean init(){
		rand.setSeed(System.currentTimeMillis());
		return true;
	}
	
	public static int getRandomNumber(int min, int max){
		return getRandomNumberExclusive(min, max) + 1;
	}
	
	public static int getRandomNumberExclusive(int min, int max){
		return min + rand.nextInt((max - min));
	}
	
}
