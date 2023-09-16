/**
 * The Duke class represents a task management application.
 * Users can interact with it through a command-line interface.
 */
class Duke {
    // An array to store user tasks
    Task[] userInputArray = new Task[100];

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
                    messageDisplay.UserInputList(userInputArray);
                    break;
                }
                case "todo": {
                    if (arguments.isEmpty()) {
                        throw new EmptyArgumentException();
                    } else {
                        storeUserTask('T', arguments);
                    }
                    break;
                }
                case "deadline": {
                    if (!arguments.contains("/by")) {
                        throw new InvalidTaskFormatException("deadline");
                    } else {
                        storeUserTask('D', arguments);
                    }
                    break;
                }
                case "event": {
                    if (!arguments.contains("/from") || !arguments.contains("/to")) {
                        throw new InvalidTaskFormatException("event");
                    } else {
                        storeUserTask('E', arguments);
                    }
                    break;
                }
                case "mark":
                case "unmark": {
                    toggleStatus(userInput);
                    break;
                }
                default:
                    messageDisplay.invalidCommand();
            }
        } catch (EmptyCommandException e){
            System.out.println(e.getMessage());
        }catch (EmptyArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InvalidTaskFormatException e) {
            System.out.println(e.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Stores a task in the userInputArray and displays a message.
     *
     * @param taskType  The type of the task ('T', 'D', or 'E').
     * @param arguments The task arguments.
     */
    private void storeUserTask(Character taskType, String arguments) {
        Task task = createTask(taskType, arguments);
        if (task != null) {
            int itemIndex = Task.getTotalTasks() - 1;
            userInputArray[itemIndex] = task;
            messageDisplay.addedMessage(userInputArray, itemIndex);
        }
    }
    /**
     * Creates a task based on the task type and arguments.
     *
     * @param taskType  The type of the task ('T', 'D', or 'E').
     * @param arguments The remaining command from user input.
     * @return The created task or null if the creation fails.
     */
    private Task createTask(Character taskType, String arguments) {
        Task task = null;
        switch (taskType) {
            case 'T':
                task = new Task('T', arguments);
                break;
            case 'D':
                task = createDeadlineTask(arguments);
                break;
            case 'E':
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
        String TaskName = arguments.substring(0, byIndex).trim();
        String date = arguments.substring(byIndex + 3).trim();
        return new DeadlineTask('D', TaskName, date);
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
        String TaskName = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 5,toIndex).trim();
        String to = arguments.substring(toIndex+3).trim();
        return new EventTask('E', TaskName, from,to);
    }

    //Toggle the complete status of a task
    public void toggleStatus(String userInput) {
        int spaceIndex = userInput.indexOf(' ');
        String integerPart = userInput.substring(spaceIndex + 1);
        String commandBeforeSpace = userInput.substring(0, spaceIndex);

        try {
            int itemIndex = Integer.parseInt(integerPart) - 1;
            if (itemIndex < 0 || itemIndex >= Task.getTotalTasks()) {
                // Handle exception case where the item index is out of bounds
                messageDisplay.notDeclared();
                return;
            }

            if (commandBeforeSpace.equals("mark")) {
                if (userInputArray[itemIndex].isCompleted()) {
                    messageDisplay.alreadyMark(userInputArray[itemIndex].getTaskName());
                } else {
                    userInputArray[itemIndex].markAsCompleted();
                    messageDisplay.completeMessage(userInputArray, itemIndex);
                }
            } else if (commandBeforeSpace.equals("unmark")) {
                if (!userInputArray[itemIndex].isCompleted()) {
                    messageDisplay.notMark(userInputArray[itemIndex].getTaskName());
                } else {
                    userInputArray[itemIndex].markAsNotCompleted();
                    messageDisplay.unCompleteMessage(userInputArray, itemIndex);
                }
            } else {
                // Handle exception case where the command is neither mark nor unmark
                messageDisplay.invalidItemNumber();
            }
        } catch (NumberFormatException e1) {
            // Handle the case where the integer part is not a valid number
            messageDisplay.invalidNumberFormat();
        }
    }
}

