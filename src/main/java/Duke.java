import java.util.ArrayList;
import java.util.List;

class Duke {
    private final List<String> userInputList;

    private final UserInterface userInterface;

    private final MessageDisplay messageDisplay;

    public Duke() {
        userInputList = new ArrayList<>();
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
                messageDisplay.UserInputList(userInputList);
            } else {
                storeUserInput(userInput);
            }
        }
        userInterface.closeScanner();
        messageDisplay.Goodbye();
    }

    private void storeUserInput(String userInput) {
        userInputList.add(userInput);
        messageDisplay.AddedMessage(userInput);
    }

}
