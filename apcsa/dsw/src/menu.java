/*
Creator: Nighthawk Coding Society
Mini Lab Name: Hello Series, featuring Monkey Jumpers
Level: Easy
*/

/*
Imports allow you to use code already written by others.
Java has many standard libraries. The names around the dots in import often give you a hint to the originator of the code.
 */

import java.util.*;

/** Main - entry point class for this project
1.'C' and Java have a main function/method that is the ENTRY into code execution. Both languages need a file to contain that ENTRY method/function.  Common convention for 'C': main.c.  Convention for Java: Main.java.
2. Replit requires a "Main.java" file and a main class ("public Class Main").  Inside the class it expects a method named "public static void main(String[] args)"."
3. Conventions and structures are part of a programming language and the tools you use.  Python people like to pick on these Java conventions, saying they are difficult.  What do you think?
*/


public class menu {   //Everything in Java is inside a class, Squigs, Squigalies, or Curly brackets denote a code block in Java.  This is the beginning of class code block.

    /** main - entry point method for this project
    main is the entry or pri·mor·di·al code block for Java
    */
    static public void main(String[] args)  {  // open squig begins the method
        startMenu(); // 'coder' defined method/function call to a different code block
    } // close squig ends the method.  What did this method do?


    public static void banner() {
        System.out.println("-------------------------\n");
        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
    }

    public static void (String obj, String args) {
	    if (args == null) return obj.main;
	    
    }

    /**
     * menu - method that is activated by main, this will perform Java code
     */
    public static void startMenu() {
        //Primitive types: AP CSA Unit 1
        int selection;  //user selection variable of type Inteteger

        //Using a Class: AP CSA Unit 2
        //Scanner is well know Java class for text based input
        Scanner scan = new Scanner(System.in);  //defining an object to scan/get input from user, notice the use of "new", this means you are making an object of type Scanner.

        //System.out.println -  A Class or Static method call that is used to output a message to the terminal.
        //The block of outputs below is used to present a menu to the user.

	Map<String, Object> OPERATORS = new HashMap<>();
	OPERATORS.put("0", );
        OPERATORS.put("1", asdflkji);
        OPERATORS.put("2", );
        OPERATORS.put("3", );
        OPERATORS.put("4", );
	
        System.out.println("1 - Hello");
        System.out.println("2 - Printy");
        System.out.println("3 - Loopy");
        System.out.println("4 - Classy Print");
        System.out.println("5 - Classy Poem");
        System.out.println("6 - Binary");
        System.out.println("7 - Termy");
        System.out.println("0 - Quit");

        //Using Scanner Class to get integer as input
        selection = scan.nextInt();  //Using nextInt() method to get an integer value

        //Control Statements: AP CSA Unit 3
        //Switch or Switch/Case is used to evaluate the input and execute the case that matches the selection.
        switch (selection) {
            case 0:
                return;
            case 1:
                //Prints message to console/terminal
                System.out.println("Hello, World of Monkeys!");
                break;
            case 2:
                //Using a Class: AP CSA Unit 2
                //Run Printy main method with null parameter
                Printy.main(null);
                break;
            case 3:
                //Using a Class: AP CSA Unit 2
                //Run MonkeyLoop() constructor and run a method from temporary object, defined in class
                new MonkeyLoop().printPoem();
                break;
            case 4:
                //Using a Class: AP CSA Unit 2
                //Run MonkeyList() constructor and run a method from temporary object, defined in class
                new MonkeyList().print();
                break;
            case 5:
                //Using a Class: AP CSA Unit 2
                //Create object to run methods from Classy.java
                MonkeyList ml = new MonkeyList();
                ml.print();
                ml.printPoem();
                break;

            case 6:
                //Using a Class: AP CSA Unit 2
                //Run Biny main method with null parameter
                Biny.main(null);
                break;

            case 7:
                //Run Termy main method with null parameter
                Termy.main(null);
                break;

            default:
                //Prints error message from console
                System.out.println("Unexpected choice, try again.");
        }

        //Recursion: AP CSA requirement 10
        //Calling the menu() method inside of the menu() code block is called recursion.
        //Using recursion we provide the ability to stay in program and select and additional option.
        //Repeating an operation is often called iteration.  Recursion is a special form of iteration.
        menu();
    }

}
