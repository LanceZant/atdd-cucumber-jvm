Feature: Learn a new word

	 Scenario: Learn a new word when prefixed with a plus sign and colon 
	
		Given the known words BASIC, C#, Clojure, Groovy, Java, LISP, Ruby, Scala		
		When  I enter new word "FORTRAN"
		Then  the output should include "Ok, got it."

		And   when I enter "tranfor"
		Then  the output should include "FORTRAN"


	 Scenario: Don't Learn a duplicate word
	
		Given the known words BASIC, C#, Clojure, Groovy, Java, LISP, Ruby, Scala		
		When  I enter the new word "Groovy"
		Then  the output should include "I already know that word."

