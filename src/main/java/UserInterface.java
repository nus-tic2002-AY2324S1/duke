import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine().trim().toLowerCase();
    }

    public void closeScanner() {
        scanner.close();
    }
}
