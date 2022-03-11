import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public void main(String[] args)  {  
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
    public static void startMenu() {
       
        String selection;
        Scanner scan = new Scanner(System.in);
        banner();
        Map<String, Object> OPERATORS = new HashMap<>();
        OPERATORS.put("q", "Quit");
        OPERATORS.put("1", "Matrix");
        OPERATORS.put("2", "IntByReference");

        int index = 0;
        for(Object value : OPERATORS.values()) {
            System.out.println(Integer.toString(index) + " - " + value);
            index++;
        }

        System.out.println("Choose an option");
        System.out.print(">> ");
	
        selection = scan.nextLine();
      
        try {
            String filename = (String) OPERATORS.get(selection);
            if(filename.equals("Quit")) return;
            Process process = Runtime.getRuntime().exec(
                 "java "
                +"apcsa/dsw/src/"
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

