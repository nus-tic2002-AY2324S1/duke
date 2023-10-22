package Duke;

import Duke.FileHandler.FileRead;
import Duke.FileHandler.FileStorage;
import Duke.Parser.Parser;
import Duke.Task.Task;
import Duke.UserInterface.UserInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * The Duke class represents a task management application.
 * Users can interact with it through a command-line interface.
 */
public class Duke {

    public static List<Task> taskList;
    public static FileStorage fileStorage;
    private static Parser parser;
    private final UserInterface userInterface;
    private final FileRead fileRead;

    // Initializes user interface and message display class, File Read, File Storage and tasklist.
    public Duke() {
        userInterface = new UserInterface();
        fileStorage = new FileStorage();
        fileRead = new FileRead();
        taskList = new ArrayList<>();
        parser = new Parser();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    /**
     * Starts the Duke application, greet the user and handle user input.
     */
    public void start() {

        fileRead.getSavedTask(taskList);
        // Greet the user
        userInterface.messageDisplay.Hello();

        while (true) {
            String userInput = this.userInterface.userInput.getUserInput();
            System.out.println(UserInterface.MessageDisplay.LINE_BREAK);
            if (userInput.equals("bye")) {
                break;
            } else {
                parser.parseUserInput(userInput);
            }
        }
        userInterface.userInput.closeScanner();
        userInterface.messageDisplay.Goodbye();
    }
}



