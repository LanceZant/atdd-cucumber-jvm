Feature: Solve

	Scenario: Solve for a single word
	
		Given the known words BASIC, C#, CLOJURE, GROOVY, JAVA, LISP, RUBY, SCALA
		
		When the JumbleSolver is run
		And  I enter the jumble "SLIP"
		
		Then the output should include "LISP"
		

	Scenario: Stump the solver
	
		Given the known words BASIC, C#, CLOJURE, GROOVY, JAVA, LISP, RUBY, SCALA
		
		When the JumbleSolver is run
		And  I enter the jumble "COBOL"
		
		Then the output should include "You win"
		And  it should include "To teach me a new word, prefix it with '+:'"
		

	Scenario: Solve for multiple words
	
		Given the known words  MEAN, MATE, MEAT, META, TAME, TEAM
		And   I enter the jumble "EAMT"
		
		Then the output should not include "MEAN"
		But  it should include "MEAT"
		And  it should include "MATE"
		And  it should include "TAME"
		And  it should include "TEAM"
		And  it should include "META"
		

	Scenario: Exit
	
		Given the JumbleSolver is run
		When  I enter an empty string
		
		Then the program should thank the user
		