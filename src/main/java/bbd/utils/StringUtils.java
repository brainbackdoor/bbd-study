package bbd.utils;

import java.util.ArrayList;

public class StringUtils {
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static String positionPrint(int position) {
		String result = "";
		for (int i = 0; i <= position; i++) {
			result += "&nbsp;";

		}
		return result;
	}

	public static ArrayList<String> splitInput(String names) {
		ArrayList<String> returnlist = new ArrayList<>();

		String[] splittedString = names.split(" ");
		for (String s : splittedString) {
			returnlist.add(s);
		}

		return returnlist;

	}
	
}
