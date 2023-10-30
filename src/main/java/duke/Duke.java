package duke;

import duke.filehandler.FileRead;
import duke.filehandler.FileStorage;
import duke.parser.DukeParser;
import duke.task.Task;
import duke.userinterface.UserInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * The Duke class represents a task management application.
 * Users can interact with it through a command-line interface.
 */
public class Duke {

  public static FileStorage fileStorage;
  private static DukeParser dukeParser;
  public final UserInterface userInterface;
  private final FileRead fileRead;
  public List<Task> taskList;

  /**
   * Initializes the Duke application by creating instances of the user interface,
   * file storage, file reader, task list, and parser.
   */
  public Duke() {

    userInterface = new UserInterface();  // Initialize the user interface
    fileStorage = new FileStorage();       // Initialize the file storage
    fileRead = new FileRead();             // Initialize the file reader
    taskList = new ArrayList<>();          // Initialize the task list
    dukeParser = new DukeParser();         // Initialize the parser
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
  public void start() {

    fileRead.getSavedTask(taskList);
    // Greet the user
    userInterface.messageDisplay.hello();
    while (true) {
      String userInput = this.userInterface.userInput.getUserInput();
      System.out.println(UserInterface.MessageDisplay.LINE_BREAK);
      if (userInput.equals("bye")) {
        break;
      } else {
        dukeParser.parseUserInput(userInterface.messageDisplay, taskList, userInput);
      }
    }
    userInterface.userInput.closeScanner();
    userInterface.messageDisplay.goodbye();
  }

}
