import java.util.ArrayList;
import java.util.List;

/**
 * The Duke class represents a task management application.
 * Users can interact with it through a command-line interface.
 */
class Duke {
    // An array to store user tasks
    private final List<Task> userInputList = new ArrayList<>();

    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private final UserInterface userInterface;

    private final MessageDisplay messageDisplay;

    // Initializes user interface and message display.
    public Duke() {
        userInterface = new UserInterface();
        messageDisplay = new MessageDisplay();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    /**
     * Starts the Duke application, greeting the user and handling user input.
     */
    public void start() {

        // Greet the user and ask for input
        messageDisplay.Hello();

        while (true) {
            String userInput = userInterface.getUserInput();
            System.out.println(MessageDisplay.LINE_BREAK);
            if (userInput.equals("bye")) {
                break;
            } else {
                checkCommand(userInput);
            }
        }
        userInterface.closeScanner();
        messageDisplay.Goodbye();
    }

    /**
     * Validates if the user input is a valid command.
     *
     * @param userInput The user's input string.
     */
    public void checkCommand(String userInput) {
        try {
            String[] inputs = userInput.split("\\s+");
            if (inputs.length == 0 || userInput.isEmpty()) {
                throw new EmptyCommandException();
            }

            String command = inputs[0];
            String arguments = userInput.substring(command.length()).trim();

            switch (command) {
                case "list": {
                    messageDisplay.UserInputList(userInputList);
                    break;
                }
                case "todo": {
                    if (arguments.isEmpty()) {
                        throw new EmptyArgumentException();
                    } else {
                        storeUserTask(TaskType.TODO, arguments);
                    }
                    break;
                }
                case "deadline": {
                    if (!arguments.contains("/by")) {
                        throw new InvalidTaskFormatException("deadline");
                    } else {
                        storeUserTask(TaskType.DEADLINE, arguments);
                    }
                    break;
                }
                case "event": {
                    if (!arguments.contains("/from") || !arguments.contains("/to")) {
                        throw new InvalidTaskFormatException("event");
                    } else {
                        storeUserTask(TaskType.EVENT, arguments);
                    }
                    break;
                }
                case "delete":{

                }
                case "mark":
                case "unmark": {
                    modifyTask(userInput);
                    break;
                }
                default:
                    throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            System.out.printf("%s\n%s\n", e.getMessage(), MessageDisplay.LINE_BREAK);
        }
    }

    /**
     * Stores a task in the userInputList and displays a message.
     *
     * @param taskType  The type of the task ('Todo', 'Deadline', or 'Event').
     * @param arguments The task arguments.
     */
    private void storeUserTask(TaskType taskType, String arguments) {
        Task task = createTask(taskType, arguments);
        if (task != null) {
            userInputList.add(task);
            int itemIndex = Task.getTotalTasks() - 1;
            messageDisplay.addedMessage(userInputList, itemIndex);
        }
    }

    /**
     * Creates a task based on the task type and arguments.
     *
     * @param taskType  The type of the task ('T', 'D', or 'E').
     * @param arguments The remaining command from user input.
     * @return The created task or null if the creation fails.
     */
    private Task createTask(TaskType taskType, String arguments) {
        Task task = null;
        switch (taskType) {
            case TODO:
                task = new Task('T', arguments);
                break;
            case DEADLINE:
                task = createDeadlineTask(arguments);
                break;
            case EVENT:
                task = createEventTask(arguments);
                break;
        }
        return task;
    }

    /**
     * Creates a deadline task based on the arguments.
     *
     * @param arguments The task arguments that contains task name, by time for an event task.
     * @return The created deadline task or null if the creation fails.
     */
    private Task createDeadlineTask(String arguments) {
        int byIndex = arguments.indexOf("/by");
        String taskName = arguments.substring(0, byIndex).trim();
        String date = arguments.substring(byIndex + 3).trim();
        return new DeadlineTask('D', taskName, date);
    }

    /**
     * Creates an event task based on the arguments.
     *
     * @param arguments The task arguments that contains task name, from time, to time for an event task.
     * @return The created event task or null if the creation fails.
     */
    private Task createEventTask(String arguments) {
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");
        String taskName = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 5, toIndex).trim();
        String to = arguments.substring(toIndex + 3).trim();
        return new EventTask('E', taskName, from, to);
    }

    //Toggle the complete status of a task
    public void modifyTask(String userInput) throws InvalidNumberFormatException {
        int spaceIndex = userInput.indexOf(' ');
        String integerPart = userInput.substring(spaceIndex + 1);
        String commandBeforeSpace = userInput.substring(0, spaceIndex);

        try {
            int itemIndex = Integer.parseInt(integerPart) - 1;
            if (itemIndex < 0 || itemIndex >= Task.getTotalTasks()) {
                // Handle exception case where the item index is out of bounds or does not exists
                throw new InvalidTaskException();
            }

            if (commandBeforeSpace.equals("mark")) {
                markAsComplete(itemIndex);
            } else if (commandBeforeSpace.equals("unmark")) {
                markAsIncomplete(itemIndex);
            } else if (commandBeforeSpace.equals("delete")) {
                deleteTask(itemIndex);
            } else {
                // Handle exception case where the command is neither mark nor unmark
                throw new InvalidNumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException(e.getMessage());
        } catch (InvalidNumberFormatException e) {
            // Handle the case where the integer part is not a valid number
            System.out.printf("%s\n%s\n", e.getMessage(), MessageDisplay.LINE_BREAK);
        } catch (InvalidTaskException e) {
            System.out.printf("%s\n%s\n", e.getMessage(), MessageDisplay.LINE_BREAK);
        }
    }
    public void markAsComplete(int itemIndex){
        if (userInputList.get(itemIndex).isCompleted()) {
            messageDisplay.alreadyMark(userInputList.get(itemIndex).getTaskName());
        } else {
            userInputList.get(itemIndex).markAsCompleted();
            messageDisplay.completeMessage(userInputList, itemIndex);
        }
    }

    public void markAsIncomplete(int itemIndex){
        if (!userInputList.get(itemIndex).isCompleted()) {
            messageDisplay.notMark(userInputList.get(itemIndex).getTaskName());
        } else {
            userInputList.get(itemIndex).markAsNotCompleted();
            messageDisplay.unCompleteMessage(userInputList, itemIndex);
        }
    }

    public void deleteTask(int itemIndex){
        Task.removeTask();
        Task deletedTask = userInputList.remove(itemIndex);
        messageDisplay.deleteMessage(deletedTask);
    }
}



