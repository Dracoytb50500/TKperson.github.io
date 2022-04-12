package com;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.lang.ClassCastException;

import com.week0.*;
import com.week1.*;
import com.week2.*;
import com.week3.*;

public class Main {
    static public void main(String[] args) throws IOException  {  
        startMenu(); 
    }

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
    public static void startMenu() throws IOException {
       
        String selection;
        Scanner scan = new Scanner(System.in);
        banner();
        Map<String, operation> OPERATIONS = new HashMap<>();
        OPERATIONS.put("q", new operation("Quit", null));
        OPERATIONS.put("1", new operation("Week 0 - Matrix", () -> Matrix.main(null)));
        OPERATIONS.put("2", new operation("Week 0 - IntByReference", () -> IntByReference.main(null)));
        OPERATIONS.put("3", new operation("Week 1 - queueImpl", () -> queueImpl.main(null)));
        OPERATIONS.put("4", new operation("Week 1 - MergeQueues", () -> MergeQueues.main(null)));
        OPERATIONS.put("5", new operation("Week 2 - Stack", () -> Week3Stack.main(null))); 
        OPERATIONS.put("6", new operation("Week 2 - Calc", () -> Calculator.main(null))); 
        OPERATIONS.put("7", new operation("Week 2 - Calc with user input", () -> CalcWithInput.main(null))); 
        OPERATIONS.put("8", new operation("Week 3 - Sort", () -> Sorts.main(null))); 

        for(String key : OPERATIONS.keySet()) {
            System.out.println(key + " - " + OPERATIONS.get(key).getName());
        }

        System.out.println("Choose an option");
        System.out.print(">> ");
        selection = scan.nextLine();
      
        try {
	    operation o = OPERATIONS.get(selection);
            if(o.getName().equals("Quit")) return;
            o.run();

        } catch(ClassCastException|NoSuchElementException e) { 
            // System.out.println("option doesn't exists type something new"); 
            System.out.println(e);
        } catch(NullPointerException e) {
            System.out.println("index doesn't exist");
        }
        startMenu();
    }
}

class operation {
	private String name;
	private Runnable method;

	public operation(String name, Runnable method) {
		this.name = name;
		this.method = method;
	}

	public void run() {
		this.method.run();
	}

	public String getName() { return this.name; }
}
