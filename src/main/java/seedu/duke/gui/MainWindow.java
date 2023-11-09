package seedu.duke.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seedu.duke.exceptions.DukeException;
import seedu.duke.io.WonkyLogger;
import seedu.duke.io.WonkyScanner;

/**
 * Controls the MainWindow and provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image wonky = new Image(this.getClass().getResourceAsStream("/images/wonky.png"));

    private static WonkyScanner wonkyScanner = WonkyScanner.getInstance();
    private static WonkyLogger wonkyLogger = WonkyLogger.getInstance();

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * @throws DukeException
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        wonkyScanner.processNextLine(input);
        String response = wonkyLogger.flushResponse();
        if (wonkyScanner.isActive()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, user),
                    DialogBox.getWonkyDialog(response, wonky)
            );
            userInput.clear();
            Platform.runLater(() -> {
                scrollPane.setVvalue(dialogContainer.getHeight());
                if (input.equals("bye")) {
                    wonkyScanner.setActive(false);
                }
            });
        }
    }

    /**
     * Creates a dialog box containing Wonky's welcome message.
     *
     * @throws DukeException
     */
    public void startUp() throws DukeException {
        String response = wonkyLogger.flushResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getWonkyDialog(response, wonky)
        );
    }
}
