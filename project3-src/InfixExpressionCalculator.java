/**
   A class to evaluate simple math expressions when passed the expression and variables
   Jamel Hendricks
   02/23/2019
   CMSC 256 Spring '19
*/
public class InfixExpressionCalculator {
	
	private static String varA;
	private static String varB;
	private static String varC;
	private static String varD;
	private static String varE;
	private static String varF;

	private static MyStack<Character> operatorStack = new MyStack<Character>();
	private static MyStack<Integer> valueStack = new MyStack<Integer>();
	
	// method to assign variables to String representation variables
	public static void assignVariables(String vars){
		
		// remove all equals signs from the argument
		String variables = "";
		variables = vars.replaceAll("=", "");
		
		// iterate through variables 
		for (int i = 0; i < (variables.length()-1 / 2); i++){
			
			// set the value of each passed variable to its corresponding String var
			switch (variables.charAt(i)){
				case 'a': case 'A' :
					
					// include negative sign if present
					if (variables.charAt(i+1) == '-') {
						varA = "-" + String.valueOf(variables.charAt(i+2)); 
					} else {
						varA = String.valueOf(variables.charAt(i+1));
					}
					
					break;
				case 'b': case 'B' :
					
					// include negative sign if present
					if (variables.charAt(i+1) == '-') {
						varB = "-" + String.valueOf(variables.charAt(i+2)); 
					} else {
						varB = String.valueOf(variables.charAt(i+1));
					}
					
					break;
				case 'c': case 'C' :
					
					// include negative sign if present
					if (variables.charAt(i+1) == '-') {
						varC = "-" + String.valueOf(variables.charAt(i+2)); 
					} else {
						varC = String.valueOf(variables.charAt(i+1));
					}
					break;
				case 'd': case 'D' :
					
					// include negative sign if present
					if (variables.charAt(i+1) == '-') {
						varD = "-" + String.valueOf(variables.charAt(i+2)); 
					} else {
						varD = String.valueOf(variables.charAt(i+1));
					}
					break;
				case 'e': case 'E' :
					
					// include negative sign if present
					if (variables.charAt(i+1) == '-') {
						varE = "-" + String.valueOf(variables.charAt(i+2)); 
					} else {
						varE = String.valueOf(variables.charAt(i+1));
					}
					break;
				case 'f': case 'F' :
					
					// include negative sign if present
					if (variables.charAt(i+1) == '-') {
						varF = "-" + String.valueOf(variables.charAt(i+2)); 
					} else {
						varF = String.valueOf(variables.charAt(i+1));
					}
					break;

			}
		}
	}

	// method to return the numerical value of a string, including negative
	public static int getValue(String var){
		
		if (var.length() > 1) {
			// if var contains a negative sign, multiply value by negative 1 to change to negative
			return -1 * Character.getNumericValue(var.charAt(1));
		}else {
			return Character.getNumericValue(var.charAt(0));
		}
	}
	
	// method to check validity of an expression
	public static void validExpression(String infix) {
		
		for (int i = 0; i < infix.length(); i++) {
			
			// check for balanced parenthesis
			if (!BalanceChecker.checkBalance(infix)){
				throw new IllegalArgumentException("Expression is not balanced!");
			}
			
			// check for implicit multiplication
			if (i+1 < infix.length()) {
				if (infix.charAt(i) == ')' && infix.charAt(i+1) == '(') {
					throw new IllegalArgumentException("No operator between parenthesis!");
				}
			}
			
			// check for empty parenthesis
			if (infix.charAt(i) == '(' && infix.charAt(i+1) == ')') {
				throw new IllegalArgumentException("No values between parenthesis!");
			}
			

			
		}
	}
	
	// evaluate a passed expression with passed variables
	public static int evaluateInfix(String[] args){	
		
		// capture experssion and assign vars
		String infix = args[0];
		assignVariables(args[1]);
		
		// verify expression is valid
		validExpression(infix);

		char topOperator;
		int operandTwo;
		int operandOne;
		
		// add values to corresponding stacks
		int i = 0;
		while ( i < infix.length() ){

			char nextCharacter = infix.charAt(i);
			
			// if current character is not a white space, add to stacks
			if (!Character.isWhitespace(nextCharacter)){

				switch (nextCharacter){

					case 'a' : case 'A' :
						valueStack.push(getValue(varA));
						break;
					case 'b' : case 'B' :
						valueStack.push(getValue(varB));
						break;
					case 'c' : case 'C' :
						valueStack.push(getValue(varC));
						break; 
					case 'd' : case 'D' :
						valueStack.push(getValue(varD));
						break; 
					case 'e' : case 'E' :
						valueStack.push(getValue(varE));
						break; 
					case 'f': case 'F' :
						valueStack.push(getValue(varF));
						break;
					case '^':
						operatorStack.push(nextCharacter);
						break;
					case '+' :
						
						// while operator stack has more element, following the order of operations
						while ( (!operatorStack.isEmpty()) && ( (operatorStack.peek().equals('+')) || (operatorStack.peek().equals('-')) ) ){
							
							// execute operation and add expression's result to valueStack
							topOperator = operatorStack.pop();
							operandTwo = valueStack.pop();
							operandOne = valueStack.pop();

							int result = operandOne + operandTwo;
							valueStack.push(result);
						} 
						operatorStack.push(nextCharacter);
						break;
					case '-' : 
						
						// while operator stack has more elements, following the order of operations
						while ( (!operatorStack.isEmpty()) && ( (operatorStack.peek().equals('+')) || (operatorStack.peek().equals('-')) ) ){
							
							// execute operation and add expression's result to valueStack
							topOperator = operatorStack.pop();
							operandTwo = valueStack.pop();
							operandOne = valueStack.pop();

							int result = operandOne - operandTwo;
							valueStack.push(result);
						} 
						operatorStack.push(nextCharacter);
						break;
					case '*' : 
						
						// while operator stack has more elements, following the order of operations
						while ( (!operatorStack.isEmpty()) && ( (operatorStack.peek().equals('+')) || (operatorStack.peek().equals('-')) || (operatorStack.peek().equals('*')) || (operatorStack.peek().equals('/')) ) ){
							
							// execute operation and add expression's result to valueStack
							topOperator = operatorStack.pop();
							operandTwo = valueStack.pop();
							operandOne = valueStack.pop();

							int result = operandOne * operandTwo;
							valueStack.push(result);
						} 
						operatorStack.push(nextCharacter);
						break;
					case '/' :
						
						// while operator stack has more elements, following the order of operations
						while ( (!operatorStack.isEmpty()) && ( (operatorStack.peek().equals('+')) || (operatorStack.peek().equals('-')) || (operatorStack.peek().equals('*')) || (operatorStack.peek().equals('/')) ) ){

							// execute operation and add expression's result to valueStack
							topOperator = operatorStack.pop();
							operandTwo = valueStack.pop();
							operandOne = valueStack.pop();
							
							// check for divide by zero error
							if (operandTwo == 0){
								throw new IllegalArgumentException("Divide by zero error: " + operandOne + " / " + operandTwo);
							}

							int result = operandOne / operandTwo;
							valueStack.push(result);
						} 
						operatorStack.push(nextCharacter);
						break;
					case '(' :
						operatorStack.push(nextCharacter);
						break;
					case ')' :
						topOperator = operatorStack.pop();
						
						// execute expressions within parenthesis
						while (topOperator != '('){
							operandTwo = valueStack.pop();
							operandOne = valueStack.pop();
							
							int result = 0;
							if (topOperator == ('+')){
								result = operandOne + operandTwo;
							} else if ( topOperator == ('-')){
								result = operandOne - operandTwo;
							} else if ( topOperator == ('*')){
								result = operandOne * operandTwo;
							} else if ( topOperator == ('/')){
								if (operandTwo == 0){
									throw new IllegalArgumentException("Divide by zero error => " + operandOne + " / " + operandTwo);
								} else {
									result = operandOne / operandTwo;
								}
							} else if ( topOperator == ('^')){
								result = (int) Math.pow(operandOne, operandTwo);
							}

							valueStack.push(result);
							topOperator = operatorStack.pop();
						}
						break;
					
					// add literal numbers to value stack
					case '0' : case '1' : case '2' : case '3' : case '4' : case '5' : case '6' : case '7' : case '8' : case '9' :
						valueStack.push(getValue(String.valueOf(nextCharacter)));
						break;
					// throw error for invalid characters in expression
					default :
						throw new IllegalArgumentException("Invalid character in expression. Please use 'a-f', '0-9', or '+,-,*,/,^'");
				}

				i++;
			} else {
				i++;
				continue;
			}
		}
		
		// execute remaining operations and push to valueStack
		while (!operatorStack.isEmpty()){

			topOperator = operatorStack.pop();
			operandTwo = valueStack.pop();
			operandOne = valueStack.pop();


			int result = 0;
			
			if (topOperator == ('+')){
				result = operandOne + operandTwo;
			} else if ( topOperator == ('-')){
				result = operandOne - operandTwo;
			} else if ( topOperator == ('*')){
				result = operandOne * operandTwo;
			} else if ( topOperator == ('/')){

				if (operandTwo == 0){
					throw new IllegalArgumentException("Divide by zero error => " + operandOne + " / " + operandTwo);
				}

				result = operandOne / operandTwo;
			} else if ( topOperator == ('^')){
				result = (int) Math.pow(operandOne, operandTwo);
			}

			valueStack.push(result);
		}
		
		// return result of all operations
		System.out.println("Result: " + valueStack.peek());
		return valueStack.peek();
	}

	public static void main(String[] args) {
		
		// evaluate passed expression
		evaluateInfix(args);

	}
}