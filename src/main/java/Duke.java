
class Duke {
    Task[] userInputArray = new Task[100];

    private final UserInterface userInterface;

    private final MessageDisplay messageDisplay;

    public Duke() {
        userInterface = new UserInterface();
        messageDisplay = new MessageDisplay();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    public void start() {

        // Greet the user and ask for input
        messageDisplay.Hello();

        while (true) {
            String userInput = userInterface.getUserInput();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.isEmpty()) {
                messageDisplay.MissingInput();
            } else if (userInput.equals("list")) {
                messageDisplay.UserInputList(userInputArray);
            } else if (markUnmarkValidate(userInput)) {
                toggleStatus(userInput);
            } else {
                storeUserInput(userInput);
            }
        }
        userInterface.closeScanner();
        messageDisplay.Goodbye();
    }

    private void storeUserInput(String userInput) {
        Task task = new Task(userInput);
        userInputArray[Task.getTotalTasks() - 1] = task;
        messageDisplay.AddedMessage(userInput);
    }

    public boolean markUnmarkValidate(String userInput) {
        int spaceIndex = userInput.indexOf(' ');
        if (spaceIndex != -1) {
            String commandBeforeSpace = userInput.substring(0, spaceIndex);
            return commandBeforeSpace.equals("mark") || commandBeforeSpace.equals("unmark");
        }
        return false;
    }

    public void toggleStatus(String userInput) {
        int spaceIndex = userInput.indexOf(' ');
        String integerPart = userInput.substring(spaceIndex + 1);
        String commandBeforeSpace = userInput.substring(0, spaceIndex);

        try {
            int itemIndex = Integer.parseInt(integerPart) - 1;
            if (commandBeforeSpace.equals("mark")) {
                if (userInputArray[itemIndex].isCompleted()) {
                    messageDisplay.alreadyMark(userInputArray[itemIndex].taskName);
                    return;
                }
                userInputArray[itemIndex].markAsCompleted();
                messageDisplay.completeMessage(userInputArray, itemIndex);
            } else if (commandBeforeSpace.equals("unmark")) {
                if (!userInputArray[itemIndex].isCompleted()) {
                    messageDisplay.notMark(userInputArray[itemIndex].taskName);
                    return;
                }
                userInputArray[itemIndex].markAsNotCompleted();
                messageDisplay.unCompleteMessage(userInputArray, itemIndex);
            }
        } catch (NumberFormatException e1) {
            messageDisplay.invalidNumberFormat();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e2) {
            messageDisplay.notDeclared();
        }
    }
}

