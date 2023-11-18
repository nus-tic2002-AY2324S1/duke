//This java file involves using Scanner to get input (from TestCases.txt)
package NUS.duke;

import java.util.Scanner;

public class UserInterface {
    //declare attribute
    private Scanner scanner;
    //take in input
    public UserInterface(){
        scanner = new Scanner(System.in);
    }
    //store input after editing
    public String getUserInput(){
        String message = scanner.nextLine().trim().toLowerCase();
        return message;
    }
    //close Scanner
    public void closeScanner(){
        scanner.close();
    }
}
