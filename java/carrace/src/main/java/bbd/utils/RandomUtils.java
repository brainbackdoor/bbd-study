package bbd.utils;

import java.util.Random;

public class RandomUtils {
	
	
	public static int makeRandom() {
		Random rnd = new Random();
		return rnd.nextInt(11) + 4;
	}

}
