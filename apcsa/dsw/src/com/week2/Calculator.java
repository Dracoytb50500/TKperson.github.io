package com.week2;

import java.util.*;

public class Calculator {
        // Key instance variables
    private String expression;
    private final String originalExpression;
    private ArrayList<String> tokens;
    private ArrayList<String> reverse_polish;
    private Double result;

    // Helper definition for supported operators
    private final Map<String, Integer> SEPARATORS = new HashMap<>();
    {
        // Map<"separator", not_used>
        SEPARATORS.put(" ", 0);
        SEPARATORS.put("(", 0);
        SEPARATORS.put(")", 0);
    }

        // Helper definition for supported operators
    private final Map<String, Integer> OPERATORS = new HashMap<>();
    {
        // Map<"token", precedence>
        OPERATORS.put("^", 2);
        OPERATORS.put("*", 3);
        OPERATORS.put("/", 3);
        OPERATORS.put("%", 3);
        OPERATORS.put("+", 4);
        OPERATORS.put("-", 4);
    }

    private final ArrayList<String> FUNCTIONS = new ArrayList<>();
    {
	FUNCTIONS.add("sqrt");
    }

    // Print the expression, terms, and result
    public String toString() {
        return ("Original expression: " + this.originalExpression + "\n" +
                "Tokenized expression: " + this.tokens.toString() + "\n" +
                "Reverse Polish Notation: " +this.reverse_polish.toString() + "\n" +
                "Final result: " + String.format("%.2f", this.result));
    }

    // Create a 1 argument constructor expecting a mathematical expression
    public Calculator(String expression) {
        // original input
	this.originalExpression = expression;
        this.expression = expression.replaceAll(" ", "");

	// parse vars
	this.parseVars();

        // parse expression into terms
        this.termTokenizer();

        // place terms into reverse polish notation
        this.tokensToReversePolishNotation();
	System.out.println(this);

        // calculate reverse polish notation
        this.rpnToResult();
    }

    public void parseVars() {
	// find var
	int varStart = this.expression.indexOf("=");
	int varEnd = this.expression.indexOf(";");
	// debug
	// System.out.println(varStart);

	// proceed if found var
	while(varStart != -1) {
		String varExpression = this.expression.substring(0, varEnd);
		// remove expression from original string
		this.expression = this.expression.replace(varExpression + ";", "");

		String varName = varExpression.substring(0, varStart);
		// System.out.println(varName);
		// System.out.println(varExpression.substring(varStart+1, varEnd));
		// System.out.println(this.expression);

		Double varResult = new Calculator(varExpression.substring(varStart+1, varEnd)).getResult();

		this.expression = this.expression.replaceAll(varName, varResult.toString());

		// find next var
		varStart = this.expression.indexOf("=");
		varEnd = this.expression.indexOf(";");
	}
    }

        // Takes RPN and produces a final result
    private void rpnToResult() {
        // Stack used to hold calculation while process RPN
        Stack<Double> calculation = new Stack<Double>();

        // for loop to process RPN
        for(int i = 0; i < this.reverse_polish.size(); i++) {
	    String currentToken = this.reverse_polish.get(i);
            // if token is an operator
            if(isOperator(currentToken)) {
                // get operator
		Double result;

		// get operands
		Double operand1 = calculation.pop();
		Double operand2 = calculation.pop();
		// calculate result
		result = this.calculate(operand1, operand2, currentToken);

                // push result to stack
                calculation.push(result);
            } else if (isFunction(currentToken)) {

		result = this.eval(calculation.pop(), currentToken);
                // push result to stack
                calculation.push(result);

	    } else { // if a number
                // push number to stack
                calculation.push(Double.parseDouble(currentToken));
            }
	    // debug
	    // System.out.println("reverse plish: " + this.reverse_polish);
	    System.out.println("calc: " + calculation);
        }

        this.result = calculation.pop();
    }

    public Double calculate(Double operand1, Double operand2, String operator) {
        Double result = 0.0;
        switch (operator) {
            case "+":
                result = operand2 + operand1;
                break;
            case "-":
                result = operand2 - operand1;
                break;
            case "*":
                result = operand2 * operand1;
                break;
            case "/":
                result = operand2 / operand1;
                break;
            case "%":
                result = operand2 % operand1;
                break;
	    case "^":
		result = Math.pow(operand2, operand1);
        }
        return result;
    }

    public Double eval(Double value, String function) {
	switch (function) {
	    case "sqrt":
		return Math.sqrt(value);
	}
	System.out.println("unregconize function: " + function);
	return value;
    }

        // Test if token is an operator
    private boolean isOperator(String token) {
        // find the token in the hash map
        return OPERATORS.containsKey(token);
    }

    // Test if token is an separator
    private boolean isSeperator(String token) {
        // find the token in the hash map
        return SEPARATORS.containsKey(token);
    }

    private boolean isFunction(String token) {
	    return FUNCTIONS.contains(token);
    }

    // Compare precedence of operators.
    private Boolean isPrecedent(String token1, String token2) {
        // token 1 is precedent if it is greater than token 2
        return OPERATORS.get(token1) >= OPERATORS.get(token2);
    }
    
	// Takes tokens and converts to Reverse Polish Notation (RPN), this is one where the operator follows its operands.
    private void tokensToReversePolishNotation () {
        // contains final list of tokens in RPN
        this.reverse_polish = new ArrayList<>();

        // stack is used to reorder for appropriate grouping and precedence
        Stack<String> tokenStack = new Stack<String>();
        for (String token : tokens) {
            switch (token) {
		// functions should be together with (
		case "sqrt":
                // If left bracket push token on to stack
                case "(":
                    tokenStack.push(token);
                    break;
                case ")":
                    while (tokenStack.peek() != null && !tokenStack.peek().equals("("))
                    {
                        reverse_polish.add( (String)tokenStack.pop() );
                    }
                    tokenStack.pop(); // pop )
		    if(tokenStack.size() != 0 && isFunction(tokenStack.peek())) {
                        reverse_polish.add( (String)tokenStack.pop() );
		    }
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
		case "^":
                    // While stack
                    // not empty AND stack top element
                    // and is an operator
                    while (tokenStack.size() != 0 && isOperator((String) tokenStack.peek()))
                    {
                        if (isPrecedent(token, (String) tokenStack.peek() )) {
                            reverse_polish.add((String)tokenStack.pop());
                            continue;
                        }
                        break;
                    }
                    // Push the new operator on the stack
                    tokenStack.push(token);
                    break;
                default:    // Default should be a number, there could be test here
                    this.reverse_polish.add(token);
             }
        }
        // Empty remaining tokens
        while (tokenStack.size() != 0) {
             reverse_polish.add((String)tokenStack.pop());
        }

    }
        // Term Tokenizer takes original expression and converts it to ArrayList of tokens
    private void termTokenizer() {
        // contains final list of tokens
        this.tokens = new ArrayList<String>();

        int start = 0;  // term split starting index
        StringBuilder multiCharTerm = new StringBuilder();    // term holder
        for (int i = 0; i < this.expression.length(); i++) {
            Character c = this.expression.charAt(i);
            if ( isOperator(c.toString() ) || isSeperator(c.toString())  ) {
                // 1st check for working term and add if it exists
                if (multiCharTerm.length() > 0) {
                    tokens.add(this.expression.substring(start, i));
                }
                // Add operator or parenthesis term to list
                // if (c != ' ') {
                //     tokens.add(c.toString());
                // }
                tokens.add(c.toString());

                // Get ready for next term
                start = i + 1;
                multiCharTerm = new StringBuilder();
            } else {
                // multi character terms: numbers, functions, perhaps non-supported elements
                // Add next character to working term
                multiCharTerm.append(c);
            }

        }
        // Add last term
        if (multiCharTerm.length() > 0) {
            tokens.add(this.expression.substring(start));
        }
    }

    public double getResult() { return this.result; }

    public static void main(String[] args) {
	    Calculator simpleMath = new Calculator("100 + 200  * 3");
            System.out.println("Simple Math\n" + simpleMath);
	    assert 100.0+200.0*3.0 == simpleMath.getResult();
           
            Calculator parenthesisMath = new Calculator("(100 + 200)  * 3");
            System.out.println("Parenthesis Math\n" + parenthesisMath);
	    assert (100 + 200)  * 3 == parenthesisMath.getResult();
           
            Calculator allMath = new Calculator("200 % 300 + 5 + 300 / 200 + 1 * 100");
            System.out.println("All Math\n" + allMath);
	    assert 200.0 % 300.0 + 5.0 + 300.0 / 200.0 + 1.0 * 100.0 == allMath.getResult();
           
            Calculator allMath2 = new Calculator("200 % (300 + 5 + 300) / 200 + 1 * 100");
            System.out.println("All Math2\n" + allMath2);
	    assert 200.0 % (300.0 + 5.0 + 300.0) / 200.0 + 1.0 * 100.0 == allMath2.getResult();

            Calculator allMath3 = new Calculator("2 ^ 3 * 2 / 2");
            System.out.println("All Math3\n" + allMath3);
	    assert Math.pow(2.0, 3.0) * 2.0 / 2.0 == allMath3.getResult();

            Calculator test1 = new Calculator("69");
	    System.out.println("test1\n" + test1);

	    Calculator test2 = new Calculator("a = 1; b = a - 3 + 5; a * 3 + 2 * b");
	    System.out.println("test2\n" + test2);

	    Calculator test3 = new Calculator("a = 1; b = a - 3 + 5; sqrt(a * 3 + 2 * b)");
	    System.out.println("test3\n" + test3);
	    // TODO: fix variable name replacing letters in functions
    }
}
