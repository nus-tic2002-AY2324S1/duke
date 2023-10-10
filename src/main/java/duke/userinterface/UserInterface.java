package duke.userinterface;

//this file involves using Scanner to get input.
import java.util.Scanner;

//public class allows other packages to view/access it
//class is only available within the package e.g. custom exceptions specific to project
//private only modifiable within same class
//final means constant at fixed value

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
