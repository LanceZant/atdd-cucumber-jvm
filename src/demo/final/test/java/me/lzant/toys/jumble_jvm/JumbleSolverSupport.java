package me.lzant.toys.jumble_jvm;

import java.util.Arrays;
import java.util.List;

public class JumbleSolverSupport {
	
	public static List<String> stringToList(String wordList) {
		return Arrays.asList(wordList.split(", "));
	}

}
