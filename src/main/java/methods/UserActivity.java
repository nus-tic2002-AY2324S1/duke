package methods;

import java.util.Scanner;

public class UserActivity {
    private String input;
    private boolean isExit;

    public UserActivity() {
        this.input = "";
        this.isExit = false;
    }

    public void UserInput() {
        Scanner scanInput = new Scanner(System.in);
        Tasks task = new Tasks();

        do {
            setInput(scanInput.nextLine());

            if (this.input.isEmpty()) {
                this.ErrorMessage();
                continue;
            }

            if (this.input.equals("bye")) {
                this.Exit();
            } else {
                this.Echo(task);
            }
        } while (!this.isExit);
    }

    public void Echo(Tasks task) {
        System.out.println("________________________________________________________________________________");

        if (this.input.equals("list")) {
            task.List();
        } else {
            task.AddToList(this.input);
        }

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    public void Exit() {
        System.out.println("Thanks for using AMEBOT~!\n");
        this.isExit = true;
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
