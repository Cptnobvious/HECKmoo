package utility;

import java.util.Random;

public class RandomUtility {
	
	public static int getRandomNumber(int min, int max){
		return getRandomNumberExclusive(min, max) + 1;
	}
	
	public static int getRandomNumberExclusive(int min, int max){
		Random rand = new Random();
		rand.setSeed(fastSeed(System.currentTimeMillis()));
		return min + rand.nextInt((max - min));
	}

	public static long fastSeed(long last){
		if (System.currentTimeMillis() == last){
			return System.currentTimeMillis() + last;
		}
		
		return System.currentTimeMillis();
	}
	
}
