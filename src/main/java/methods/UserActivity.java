package methods;

import java.util.Scanner;

public class UserActivity {
    private String input;

    public UserActivity() {
        Scanner scanInput = new Scanner(System.in);

        do {
            setInput(scanInput.nextLine());
            if (this.input.isEmpty()) {
                this.ErrorMessage();
                continue;
            }

            this.Echo();
        } while (!this.input.equals("bye"));
    }

    public void Echo() {
        if (this.input.equals("bye")) {
            this.Exit();
        } else {
            System.out.println("________________________________________________________________________________");
            System.out.println(this.input.toUpperCase());
            System.out.println("--------------------------------------------------------------------------------\n");
        }
    }

    public void Exit() {
        System.out.println("Thanks for using AMEBOT~!\n");
    }

    public void ErrorMessage() {
        System.out.println("You've entered an invalid command, please try again!\n");
    }

    public void setInput(String input) {
        this.input = input.toLowerCase();
    }

    public String getInput() {
        return this.input;
    }
}
