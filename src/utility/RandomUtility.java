package utility;

import java.util.Random;

public class RandomUtility {
	
	public static int getRandomNumber(int min, int max){
		return getRandomNumber(min, max) +1;
	}
	
	public static int getRandomNumberExclusive(int min, int max){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		return min + rand.nextInt((max - min));
	}

}
