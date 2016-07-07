Feature: Learn a new word

	 Scenario: Learn a new word when prefixed with a plus sign and colon 
	
		Given the known words BASIC, C#, Clojure, Groovy, Java, LISP, Ruby, Scala		
		When  I enter "+:FORTRAN"
		Then  the output should include "Ok, got it."

		And   when I enter "tranfor"
		Then  the output should include "FORTRAN"

	 Scenario: Learn a new word that contains a plus sign 
	
		Given the known words BASIC, C#, Clojure, Groovy, Java, LISP, Ruby, Scala		
		When  I enter "+:C++"
		Then  the output should include "Ok, got it."

		And   when I enter "+c+"
		Then  the output should include "C++"

	 Scenario: Don't Learn a duplicate word
	
		Given the known words BASIC, C#, Clojure, Groovy, Java, LISP, Ruby, Scala		
		When  I enter "+:Groovy"
		Then  the output should include "I already know that word."

# Removed in favor of unit test
#	 Scenario: Ignore Case when identifying duplicate words
#	
#		Given the known words BASIC, C#, Clojure, Groovy, Java, LISP, Ruby, Scala		
#		When  I enter "+:GROOVY"
#		Then  the output should include "I already know that word."
