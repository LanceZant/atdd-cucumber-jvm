package me.lzant.toys.jumble_jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JumbleSolver {

	private Map<String, List<String>> knownWords = new HashMap<String, List<String>>();
	
	protected static final String ACK_NEW_WORD = "Ok, got it.";
	protected static final String SURRENDER = "I got nuthin'.  You win.";
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
	protected String learnWord(String word) {
		String key = makeKey(word);
		if (! knownWords.containsKey(key)) {
			knownWords.put(key, new ArrayList<String>());
		}
		knownWords.get(key).add(word);
		return ACK_NEW_WORD;
	}
	
	protected String makeKey(String word) {
		char [] chars = word.toUpperCase().toCharArray();
	    Arrays.sort(chars);
	    return new String(chars);
	}
	
	public String solve(String jumble) {
		List<String> solutions = knownWords.get(makeKey(jumble));
		StringBuilder result = new StringBuilder();
		if (solutions != null) {
			for (String word : solutions) {
				result.append(word).append(' ');
			}
		}
		else {
			result.append(SURRENDER);
		}
		return result.toString().trim();
	}
	
	protected String handleInput(String jumble) {
		if (jumble == null || jumble.length() == 0) {
			return GOOD_BYE;
		} 
		else if (jumble.startsWith("+") && jumble.length() >1) {
				String newWord = jumble.substring(1);
				return learnWord(newWord);
		}
		else {
			return solve(jumble);
		}

	}
	
	public static BufferedReader getReaderForFileName(String fileName) {
		return new BufferedReader( new InputStreamReader(
						JumbleSolver.class.getClassLoader().getResourceAsStream(fileName)));
	}
	public static void main(String[] args ) throws IOException {
		// Default to big word list, just in case args[0] doesn't work out
		String wordFileName = "corncob_caps.txt";
		BufferedReader wordReader = null;
		
		if (args != null && args.length > 0 && args[0] != null) {
			try {
			BufferedReader classPathWordReader = getReaderForFileName(args[0]);
					
			if (classPathWordReader != null) {
				wordReader = classPathWordReader;
				wordFileName = args[0];
			}
			}
			catch (NullPointerException e) {
				System.out.println("No luck finding word file " + args[0]);
			}
		}

		if (wordReader == null) {
			wordReader = getReaderForFileName(wordFileName);
		}
		
		System.out.println("Getting word list from " + wordFileName);
		JumbleSolver theSolver = new JumbleSolver(wordReader);
		
		System.out.println("Ok, ready.\n  Enter JUMBLEs one at a time.\n  Enter an empty value to quit.\n");
		BufferedReader jumbleReader = new BufferedReader(new InputStreamReader(System.in));
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
