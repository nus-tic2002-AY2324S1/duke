package com.tina.GUI;


import com.tina.app.Tina;
import com.tina.exception.IOException;
import com.tina.exception.InvalidFileFormatException;
import com.tina.exception.InvalidFilePathException;
import com.tina.service.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the controller for MainWindow.
 * Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Tina tina;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    /**
     * Initialize.
     * Shows welcome message in dialog window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().addAll(
                DialogBox.getTinaDialog(Ui.showWelcome(), botImage)
        );
    }

    /**
     * Sets tina.
     * Loads task list from local file.
     * Shows error message if the file format is invalid or file path is invalid.
     *
     * @param tina the tina.
     */
    public void setTina(Tina tina) {
        this.tina = tina;

        try {
            tina.initiate();
        } catch (IOException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getTinaDialog(Ui.printIOError(), botImage)
            );
        } catch (InvalidFileFormatException | InvalidFilePathException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getTinaDialog(Ui.printLoadingError(), botImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Tina'reply.
     * Appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tina.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTinaDialog(response, botImage)
        );
        userInput.clear();
    }
}
