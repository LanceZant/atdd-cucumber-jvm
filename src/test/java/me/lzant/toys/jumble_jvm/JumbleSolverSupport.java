package me.lzant.toys.jumble_jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JumbleSolverSupport {
	
	public static List<String> stringToList(String wordList) {
		List<String> result = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(wordList, ", ");
		while (tokenizer.hasMoreTokens()) {
			result.add(tokenizer.nextToken().toUpperCase());
		}
		
		return result;
	}

}
