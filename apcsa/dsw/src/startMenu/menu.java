/*
Creator: Nighthawk Coding Society
Mini Lab Name: Hello Series, featuring Monkey Jumpers
Level: Easy
*/

/*
Imports allow you to use code already written by others.
Java has many standard libraries. The names around the dots in import often give you a hint to the originator of the code.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
    /**
     * menu - method that is activated by main, this will perform Java code
     */
    public static void startMenu() {
        //Primitive types: AP CSA Unit 1
        String selection;  //user selection variable of type Inteteger
        //Using a Class: AP CSA Unit 2
        //Scanner is well know Java class for text based input
        Scanner scan = new Scanner(System.in);  //defining an object to scan/get input from user, notice the use of "new", this means you are making an object of type Scanner.

        //System.out.println -  A Class or Static method call that is used to output a message to the terminal.
        //The block of outputs below is used to present a menu to the user.

        banner();
        Map<String, Object> OPERATORS = new HashMap<>();
        OPERATORS.put("0", "Quit");
        OPERATORS.put("1", "Hello");
        OPERATORS.put("2", "Printy");

        int index = 0;
        for(Object value : OPERATORS.values()) {
            System.out.println(Integer.toString(index) + " - " + value);
            index++;
        }

        System.out.println("Choose an option");
        System.out.print(">> ");
	
        // System.out.println("1 - Hello");
        // System.out.println("2 - Printy");
        // System.out.println("3 - Loopy");
        // System.out.println("4 - Classy Print");
        // System.out.println("5 - Classy Poem");
        // System.out.println("6 - Binary");
        // System.out.println("7 - Termy");
        // System.out.println("0 - Quit");

        //Using Scanner Class to get integer as input
        selection = scan.nextLine();  //Using nextInt() method to get an integer value
        try {
            String filename = (String) OPERATORS.get(selection);
            if(filename.equals("Quit")) return;
            Process process = Runtime.getRuntime().exec(
                 "java "
                +filename 
                + ".java"
            );

            printResults(process);

        } catch(ClassCastException|NullPointerException|IOException|NoSuchElementException e) { 
            // System.out.println("option doesn't exists type something new"); 
            System.out.println(e);
        }

        startMenu();
    }

}
