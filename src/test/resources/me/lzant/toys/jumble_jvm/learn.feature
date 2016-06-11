Feature: Learn a new words

	 Scenario: Learn a new word when prefixed with a plus sign 
	
		Given the known words BASIC, C#, Clojure, Groovy, Java, LISP, Ruby, Scala		
		When  I enter "+FORTRAN"
		Then  the output should include "Ok, got it."

		And   when I enter "tranfor"
		Then  the output should include "FORTRAN"
