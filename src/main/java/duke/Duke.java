package duke;

import java.util.ArrayList;
import java.util.List;

import duke.filehandler.FileRead;
import duke.filehandler.FileStorage;
import duke.parser.DukeParser;
import duke.task.Task;
import duke.userinterface.UserInterface;

/**
 * The Duke class represents a task management application.
 * Users can interact with it through a command-line interface.
 */
public class Duke {

    private final UserInterface userInterface;
    private final DukeParser dukeParser;
    private final FileRead fileRead;

    private final FileStorage fileStorage;
    private final List<Task> taskList;

    /**
     * Initializes the Duke application by creating instances of the user interface,
     * file storage, file reader, task list, and parser.
     */
    public Duke() {
        // Initialize the user interface
        userInterface = new UserInterface();
        // Initialize the file storage
        fileStorage = new FileStorage();
        // Initialize the file reader
        fileRead = new FileRead();
        // Initialize the task list
        taskList = new ArrayList<>();
        // Initialize the parser
        dukeParser = new DukeParser();
    }

    /**
     * The main method of the Duke application. It creates an instance of the Duke
     * class and starts the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.start();
    }

    /**
     * Starts the Duke application, greets the user, and handles user input.
     */
    private void start() {

        boolean isNewUser = fileRead.getSavedTask(taskList);
        if (isNewUser) {
            userInterface.getMessageDisplay().newUserHello();
        } else {
            userInterface.getMessageDisplay().existingUserHello();
        }
        while (true) {
            String userInput = this.userInterface.getUserInput().getLine();
            UserInterface.MessageDisplay.printLineBreak();
            if (userInput.equals("bye")) {
                break;
            } else {
                dukeParser.parseUserInput(fileStorage, userInterface.getMessageDisplay(), taskList, userInput);
            }
        }
        userInterface.getUserInput().closeScanner();
        userInterface.getMessageDisplay().goodbye();
    }

}
