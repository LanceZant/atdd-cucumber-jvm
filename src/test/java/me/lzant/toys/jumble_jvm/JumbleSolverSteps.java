package me.lzant.toys.jumble_jvm;

import java.util.List;

import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import static org.junit.Assert.*;

public class JumbleSolverSteps {
	private JumbleSolver theSolver = new JumbleSolver();
	private String solution = null;
	
@Given("^the known words? (,?[^\b]+)+$")
public void theKnownWords(String knownWords) throws Throwable {
//    System.out.println("==> StepDef: theKnownWords(" + knownWordList.getClass().getSimpleName() + ": [" + knownWordList.toString() + "])");
	List<String> knownWordList = JumbleSolverSupport.stringToList(knownWords);
    for (String word : knownWordList) {
    	theSolver.learnWord(word);
    }
}

@When("^the JumbleSolver is run$")
public void theJumbleSolverIsRun() throws Throwable {
    // nop until we get to the command line;
}

@Then("^(?:the output|it) should include \"([^\"]*)\"$")
public void theOutputShouldInclude(String wordOrPhrase) throws Throwable {
    assertTrue("Output: [" + solution + "] should contain [" + wordOrPhrase + "]", solution.contains(wordOrPhrase));
}

@Then("^the program should thank the user$")
public void theProgramtShouldThankTheUser() throws Throwable {
	assertEquals(JumbleSolver.GOOD_BYE, solution);
}

@Then("^(?:the output|it) should not include \"([^\"]*)\"$")
public void theOutputShouldNotInclude(String wordOrPhrase) throws Throwable {
    assertFalse(solution.contains(wordOrPhrase));
}


@When("^I enter an empty string$")
public void iEnterAnEmptyString() throws Throwable {
    solution = theSolver.handleInput("");
}

@Then("^the JumbleSolver should display \"([^\"]*)\" and exit normally$")
public void theJumbleSolverShouldDisplayAndExitNormally(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

@When("^(?:when )?I enter ?(?:the jumble)? \"([^\"]*)\"$")
public void iEnterTheJumble(String jumble) throws Throwable {
    solution = theSolver.handleInput(jumble);
}

@When("^the JumbleSolver is run with the word file \"([^\"]*)\"$")
public void theJumbleSolverIsRunWithTheWordFile(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

}
