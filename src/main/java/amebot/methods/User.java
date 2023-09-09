package amebot.methods;

import java.util.Scanner;

import amebot.errors.Message;

public class User {
    private String input;
    private boolean isExit;

    public User() {
        input = "";
        isExit = false;
    }

    public void userInput() {
        Scanner scanInput = new Scanner(System.in);
        Task tasks = new Task();

        do {
            input = scanInput.nextLine().toLowerCase();

            echo(tasks);
        } while (!isExit);
    }

    public void echo(Task tasks) {
        System.out.println("________________________________________________________________________________");

        String[] line = input.trim().split(" ");
        String command = line[0];
        int index = 0;

        if (line.length > 1) {
            for (char c : line[1].toCharArray()) {
                if (Character.isDigit(c)) {
                    index = Integer.parseInt(line[1]);
                    break;
                }
            }
        }

        switch (command) {
            case "add":
                tasks.addToList(input.substring(4));
                break;
            case "list":
                tasks.listAllTasks();
                break;
            case "check":
                // Fallthrough
            case "uncheck":
                tasks.setStatus(index - 1, command);
                break;
            case "bye":
                exit();
                break;
            default:
                Message.userInputError();
                break;
        }

        System.out.println("--------------------------------------------------------------------------------\n");
    }

    public void exit() {
        System.out.println("Thanks for using AMEBOT~!");
        isExit = true;
    }

    public String getInput() {
        return input;
    }
}
