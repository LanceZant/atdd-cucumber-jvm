package me.lzant.toys.jumble_jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JumbleSolver {

	private Map<String, List<String>> knownWords = new HashMap<String, List<String>>();
	
	protected static final String DEFAULT_WORD_FILE = "corncob_caps.txt";
	protected static final String SURRENDER = "I got nuthin'.  You win.\n";
	protected static final String GOOD_BYE = "Thanks for playing!  Goodbye.";
	
	protected JumbleSolver() {
		// Reinstate no-args c'tor for convenience in testing other methods
	}
	
	protected JumbleSolver(Map<String, List<String>> knownWords) {
		this.knownWords = knownWords;
	}
	
	public JumbleSolver(BufferedReader wordReader) throws IOException{
		while (wordReader.ready()){
			learnWord( wordReader.readLine() );
		}
	}
	
	public JumbleSolver(List<String> words){
		for (String word : words) {
			learnWord(word);
		}
		
	}
	
	public String getInstructions() {
		return "Ok, ready.\n  Enter JUMBLEs one at a time.\n  Enter an empty value to quit.\n";
	}
	
	protected void learnWord(String word) {
		String key = makeKey(word);
		if (! knownWords.containsKey(key)) {
			knownWords.put(key, new ArrayList<String>());
		}
		knownWords.get(key).add(word);
	}

	private boolean alreadyKnow(String word, String key) {
		String knownAnagrams = String.join(", ", knownWords.get(key));
		return knownAnagrams.toUpperCase().contains(word.toUpperCase());
	}
	
	protected String makeKey(String word) {
		char [] chars = word.toUpperCase().toCharArray();
	    Arrays.sort(chars);
	    return new String(chars);
	}
	
	public String solve(String jumble) {
		List<String> solutions = knownWords.get(makeKey(jumble));
		if (solutions == null || solutions.isEmpty()) {
			return(SURRENDER);
		}
		return String.join(" ", solutions);
	}
	
	protected String handleInput(String jumble) {
		if (jumble == null || jumble.length() == 0) {
			return GOOD_BYE;
		} 
		else {
			return solve(jumble);
		}
	}
	
	private static BufferedReader getReaderForFileName(String fileName) {
		return new BufferedReader( new InputStreamReader(
						JumbleSolver.class.getClassLoader().getResourceAsStream(fileName)));
	}
	
	public static void main(String[] args ) throws IOException {
		
		JumbleSolver theSolver = new JumbleSolver(getReaderForFileName(DEFAULT_WORD_FILE));
		BufferedReader jumbleReader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println(theSolver.getInstructions());
		
		while (true) {
			String jumble = jumbleReader.readLine();
			String result = theSolver.handleInput(jumble);
			System.out.println(result + "\n");
			if (result.equals(GOOD_BYE)) {
				break;
			}
		}
		
	}
}
