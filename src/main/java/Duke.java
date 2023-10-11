import java.util.ArrayList;
import java.util.List;

/**
 * The Duke class represents a task management application.
 * Users can interact with it through a command-line interface.
 */
class Duke {
    // Initialize a taskList array to store user tasks.
    private final List<Task> taskList = new ArrayList<>();
    private final UserInterface userInterface;
    private final MessageDisplay messageDisplay;
    // Initializes user interface and message display class.
    public Duke() {
        userInterface = new UserInterface();
        messageDisplay = new MessageDisplay();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    /**
     * Starts the Duke application, greet the user and handle user input.
     */
    public void start() {

        // Greet the user
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
                    handleListCommand();
                    break;
                }
                case "todo": {
                    handleTodoCommand(arguments);
                    break;
                }
                case "deadline": {
                    handleDeadlineCommand(arguments);
                    break;
                }
                case "event": {
                    handleEventCommand(arguments);
                    break;
                }
                case "delete":
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

    private void handleListCommand() {
        messageDisplay.printList(taskList);
    }

    private void handleTodoCommand(String arguments) throws EmptyTodoArgumentException {
        if (arguments.isEmpty()) {
            throw new EmptyTodoArgumentException();
        }
        storeUserTask(TaskType.TODO, arguments);
    }

    private void handleDeadlineCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new EmptyDeadlineArgumentException();
        }
        if (!arguments.contains("/by")) {
            throw new InvalidTaskFormatException("deadline");
        }
        storeUserTask(TaskType.DEADLINE, arguments);
    }

    private void handleEventCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new EmptyEventArgumentException();
        }
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            throw new InvalidTaskFormatException("event");
        }
        storeUserTask(TaskType.EVENT, arguments);
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
            taskList.add(task);
            int itemIndex = Task.getTotalTasks() - 1;
            messageDisplay.addedMessage(taskList, itemIndex);
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
    public void modifyTask(String userInput) {
        try {
            int itemIndex = extractItemIndex(userInput);
            switch (getCommandFromInput(userInput)) {
                case "mark":
                    markAsComplete(itemIndex);
                    break;
                case "unmark":
                    markAsIncomplete(itemIndex);
                    break;
                case "delete":
                    deleteTask(itemIndex);
                    break;
                default:
                    // Handle exception case where the command is neither mark nor unmark
                    throw new InvalidNumberFormatException();
            }
        } catch (InvalidNumberFormatException e) {
            // Handle the case where the integer part is not a valid number
            System.out.printf("%s\n%s\n", e.getMessage(), MessageDisplay.LINE_BREAK);
        }
    }

    private String getCommandFromInput(String userInput) {
        int spaceIndex = userInput.indexOf(' ');
        return userInput.substring(0, spaceIndex);
    }

    private int extractItemIndex(String userInput) throws InvalidNumberFormatException {
        try {
            int spaceIndex = userInput.indexOf(' ');
            String integerPart = userInput.substring(spaceIndex + 1).trim();
            int itemIndex = Integer.parseInt(integerPart) - 1;
            if (itemIndex < 0 || itemIndex >= Task.getTotalTasks()) {
                // Handle exception case where the item index is out of bounds or does not exists
                throw new TaskNotFoundException();
            }
            return itemIndex;
        }catch(TaskNotFoundException e){
            // Handle the case where task is not found from index
            System.out.printf("%s\n%s\n", e.getMessage(), MessageDisplay.LINE_BREAK);
        }catch(NumberFormatException e){
            throw new InvalidNumberFormatException();
        }
        return 0;
    }

    public void markAsComplete(int itemIndex) {
        boolean isCompleted = taskList.get(itemIndex).isCompleted();
        if (isCompleted) {
            messageDisplay.alreadyMark(taskList.get(itemIndex).getTaskName());
        } else {
            taskList.get(itemIndex).markAsCompleted();
            messageDisplay.completeMessage(taskList, itemIndex);
        }
    }

    public void markAsIncomplete(int itemIndex) {
        boolean notComplete = !taskList.get(itemIndex).isCompleted();
        if (notComplete) {
            messageDisplay.notMark(taskList.get(itemIndex).getTaskName());
        } else {
            taskList.get(itemIndex).markAsNotCompleted();
            messageDisplay.unCompleteMessage(taskList, itemIndex);
        }
    }

    public void deleteTask(int itemIndex) {
        Task.removeTask();
        Task deletedTask = taskList.remove(itemIndex);
        messageDisplay.deleteMessage(deletedTask);
    }

    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

}



