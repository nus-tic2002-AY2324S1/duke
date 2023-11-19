package MainProject;

import Exceptions.InvalidRangeException;
import Interface.UI;
import Parse.ParsedCommand;
import Parse.Parser;
import Storage.Storage;
import List.Tasklist;

/**
 * Main engine for the program
 */
public class Duke {

   public static void main(String[] args) {
       UI ui = new UI();
       Storage storage = new Storage();
       Tasklist myTaskList = new Tasklist(storage.loadTasksFromFile());
       Parser parser = new Parser();

       String userInput;

       ui.showWelcomeMessage();

        while (true){
            ui.askCommands();
            userInput = ui.getUserInput();
            ParsedCommand parsedCommand = parser.parseCommand(userInput);

            assert myTaskList != null;
            assert storage != null;

            switch(parsedCommand.getCommandType()) {
                case "list":
                    System.out.println("Listing out all stored tasks");
                    myTaskList.listTasks();
                    break;

                case "bye":
                    ui.showGoodBye();
                    System.exit(0);
                    break;

                case "help":
                    ui.showHelp();
                    break;

                case "find":
                    ui.findOutput(parsedCommand.getTaskDescription());
                    myTaskList.findTasksViaDescription(parsedCommand.getTaskDescription());
                    break;

                case "view":
                    myTaskList.findTasksViaDate(parsedCommand.getFindDate());
                    break;

                case "todo":
                    ui.showStoreOutput(parsedCommand.getTaskDescription(), "todo");
                    myTaskList.storeTasks(parsedCommand.getTaskDescription());
                    myTaskList.listTasks();
                    break;

                case "deadline":
                    ui.showStoreOutput(parsedCommand.getTaskDescription(), "deadline");
                    myTaskList.storeTasks(parsedCommand.getTaskDescription(), parsedCommand.getDeadline());
                    myTaskList.listTasks();
                    break;

                case "event":
                    ui.showStoreOutput(parsedCommand.getTaskDescription(), "event");
                    myTaskList.storeTasks(parsedCommand.getTaskDescription(), parsedCommand.getStartDate(), parsedCommand.getEndDate());
                    myTaskList.listTasks();
                    break;

                case "mark":
                    try {
                        int taskIndex = parsedCommand.getTaskIndex();
                        if (myTaskList.checkTaskRange(taskIndex)) {
                            ui.showMarkUnMarkOutput(parsedCommand.getTaskIndex(), "Mark");
                            myTaskList.getTask(taskIndex).markDone();
                        } else {
                            throw new InvalidRangeException("Task index is wrong");
                        }
                        myTaskList.listTasks();
                    } catch (InvalidRangeException e) {
                        System.out.println("[-] Error: " + e.getMessage());
                    }
                    break;

                case "unmark":
                    try {
                        int taskIndex = parsedCommand.getTaskIndex();
                        if (myTaskList.checkTaskRange(taskIndex)) {
                            ui.showMarkUnMarkOutput(parsedCommand.getTaskIndex(), "Unmark");
                            myTaskList.getTask(taskIndex).unmarkDone();
                        } else {
                            throw new InvalidRangeException("Task index is wrong");
                        }
                        myTaskList.listTasks();
                    } catch (InvalidRangeException e) {
                        System.out.println("[-] Error: " + e.getMessage());
                    }
                    break;

                case "delete":
                    try {
                        int taskIndex = (parsedCommand.getTaskIndex());
                        if (myTaskList.checkTaskRange(taskIndex)) {
                            ui.showMarkUnMarkOutput(parsedCommand.getTaskIndex(), "Delete");
                            myTaskList.deleteTask(taskIndex - 1);
                        } else {
                            throw new InvalidRangeException("Task index is wrong");
                        }
                        myTaskList.listTasks();
                    } catch (InvalidRangeException e) {
                        System.out.println("[-] Error: " + e.getMessage());
                    }
                    break;

                case "archive":
                    storage.saveTasksToFile(myTaskList.getTasks(), parsedCommand.getTaskDescription());
                    ui.showArchiveOutput(parsedCommand.getTaskDescription());
                    break;
                default:
                    System.out.println("[-] " + parsedCommand.getTaskDescription());
                    break;
            }


            ui.showHorizontalLine();
            storage.saveTasksToFile(myTaskList.getTasks(), null);
        }
    }
}
