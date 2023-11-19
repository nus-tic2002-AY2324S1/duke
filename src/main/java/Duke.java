import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static final Storage storage = new Storage("./duke.txt");

    public static void main(String[] args) throws IOException {

        ArrayList<Task> taskList = storage.readFromFile();

        Scanner in = new Scanner(System.in);
        int index = taskList.size();

        UI.welcome();

        while(true) {
            String input = in.nextLine();
            assert input != null : "Input is null";
            String[] stringArray = input.split(" ");
            String command = stringArray[0];
            int taskIndex;
            String description;
            String by;
            String from;
            String to;

            switch (command) {
                case "hello" : {
                    UI.hello();
                    break;
                }
                case "bye" : {
                    UI.bye();
                    return;
                }
                case "list" : {
                    if (taskList.isEmpty()) {
                        UI.printGenericMessage("Your task list is empty.");
                    } else {
                        UI.printTaskList(taskList);
                    }
                    break;
                }
                case "mark" : {
                    // condition for "mark" command without any number
                    try {
                        taskIndex = Integer.parseInt(stringArray[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        UI.errorArrayIndexOutOfBounds();
                        break;
                    } catch (NumberFormatException e) {
                        UI.errorNumberFormatException();
                        break;
                    }

                    // condition for "mark" command with out-of-bounds number
                    try {
                        taskList.get(taskIndex).setDone();
                    } catch (IndexOutOfBoundsException e) {
                        UI.errorIndexOutOfBounds();
                        break;
                    }

                    UI.setDone(taskList, taskIndex);

                    storage.saveToFile(taskList);

                    break;
                }
                case "unmark" : {
                    // condition for "unmark" command without any number
                    try {
                        taskIndex = Integer.parseInt(stringArray[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        UI.errorArrayIndexOutOfBounds();
                        break;
                    } catch (NumberFormatException e) {
                        UI.errorNumberFormatException();
                        break;
                    }

                    // condition for "unmark" command with out-of-bounds number
                    try {
                        taskList.get(taskIndex).setNotDone();
                    } catch (IndexOutOfBoundsException e) {
                        UI.errorIndexOutOfBounds();
                        break;
                    }

                    UI.setNotDone(taskList, taskIndex);

                    storage.saveToFile(taskList);

                    break;
                }
                case "todo" : {
                    try {
                        description = createDescription(input, "todo");
                    } catch (StringIndexOutOfBoundsException e) {
                        UI.errorWrongTodoFormat();
                        break;
                    }

                    taskList.add(index, new Todo(description));
                    UI.printTaskAdded(taskList, index);

                    storage.appendToFile(taskList, index);

                    index++;
                    break;
                }
                case "deadline" : {
                    try {
                        description = createDescription(input, "deadline");
                        by = createByDate(input);
                    } catch (StringIndexOutOfBoundsException e) {
                        UI.errorWrongDeadlineFormat();
                        break;
                    }

                    taskList.add(index, new Deadline(description, by));
                    UI.printTaskAdded(taskList, index);

                    storage.appendToFile(taskList, index);

                    index++;
                    break;
                }
                case "event" : {
                    try {
                        description = createDescription(input, "event");
                        from = createFromDate(input);
                        to = createToDate(input);
                    } catch (StringIndexOutOfBoundsException e) {
                        UI.errorWrongEventFormat();
                        break;
                    }

                    taskList.add(index, new Event(description, from, to));
                    UI.printTaskAdded(taskList, index);

                    storage.appendToFile(taskList, index);

                    index++;
                    break;
                }
                case "delete" : {
                    try {
                        taskIndex = Integer.parseInt(stringArray[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        UI.errorArrayIndexOutOfBounds();
                        break;
                    } catch (NumberFormatException e) {
                        UI.errorNumberFormatException();
                        break;
                    }

                    if (taskIndex > index) {
                        UI.errorIndexOutOfBounds();
                        break;
                    }
                    UI.printTaskDeleted(taskList, taskIndex, index);
                    taskList.remove(taskIndex);

                    storage.saveToFile(taskList);

                    index--;
                    break;
                }
                default : {
                    UI.printDefaultMessage();
                }
            }
        }
    }

    public static String createDescription(String input, String command) {
        int beginningIndex = 0;
        int endingIndex = 0;

        switch (command) {
            case "todo": {
                beginningIndex = 5;
                endingIndex = input.length();
                break;
            }
            case "deadline": {
                beginningIndex = 9;
                endingIndex = input.indexOf(" /by");
                break;
            }
            case "event": {
                beginningIndex = 6;
                endingIndex = input.indexOf(" /from");
                break;
            }
        }

        return input.substring(beginningIndex, endingIndex);
    }

    public static String createByDate(String input) {
        int beginningIndex = input.indexOf("/by") + 4;
        int endingIndex = input.length();

        return input.substring(beginningIndex, endingIndex);
    }

    public static String createFromDate(String input) {
        int beginningIndex = input.indexOf("/from") + 6;
        int endingIndex = input.indexOf(" /to");

        return input.substring(beginningIndex, endingIndex);
    }

    public static String createToDate(String input) {
        int beginningIndex = input.indexOf("/to") + 4;
        int endingIndex = input.length();

        return input.substring(beginningIndex, endingIndex);
    }
}