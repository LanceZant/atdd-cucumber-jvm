package me.lzant.toys.jumble_jvm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class JumbleSolverTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMakeKey() {
		// Arrange
		JumbleSolver solver = new JumbleSolver();
		String jumble = "jumble";
		String expected = "BEJLMU";
		
		// Act
		String actual = solver.makeKey(jumble);
		
		// Assert
		assertEquals("Wrong key from 'jumble'", expected, actual);
	}

	@Test
	public void testLearnWord() {
		// Arrange
		JumbleSolver solver = new JumbleSolver();
		String newWord = "jumble";
		String key = solver.makeKey(newWord);
		
		// Act
		solver.learnWord(newWord);
		
		// Assert
		assertEquals("knownWords should include '" + newWord + "'", 
				newWord, solver.solve(newWord));
	}

	@Test
	public void testSolve() {
		// Arrange
		String newWord = "jumble";
		String key = "BEJLMU";
		String jumble = "beljum";
		Map<String, List<String>> knownWords = new HashMap<>();
		knownWords.put(key,  Arrays.asList(newWord));
		JumbleSolver solver = new JumbleSolver(knownWords);
		
		// Act
		String actual = solver.solve(jumble);
		
		// Assert
		assertEquals(newWord, actual);
	}
	
	@Test
	public void testSolveStumped() {
		// Arrange
		JumbleSolver solver = new JumbleSolver();
		
		// Act
		String actual = solver.solve("unknown");
		
		// Assert
		assertContains("You win", actual);
	}
	
	@Test
	public void testHandleInputExit() {
		// Arrange
		JumbleSolver solver = new JumbleSolver();
		String expected = JumbleSolver.GOOD_BYE;
		
		// Act
		String actual = solver.handleInput("");
		
		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleInputJumbleToSolve() {
		// Arrange
		JumbleSolver spySolver = spy(JumbleSolver.class);
		String jumble = "theJumble";
		String expected = "theSolution";
		when(spySolver.solve(jumble)).thenReturn(expected);
		
		// Act
		String actual = spySolver.handleInput(jumble);
		
		// Assert
		assertEquals(expected, actual);		// ...and faithfully passed along the result
	}
	
	public void assertContains(String expected, String actual) {
		assertTrue(String.format("Output[%s] should contain [%s]", actual, expected), 
				actual.contains(expected));
	}
}
