package com.tina.service;

import com.tina.command.Command;
import com.tina.exception.TinaException;
import com.tina.exception.InvalidFilePathException;
import com.tina.task.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main program Tina, a chatbot, allows to schedule tasks with flexible functions.
 *
 */
public class Tina extends Application {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Instantiates a new Tina program.
     *
     * @param path the path where task list to be loaded or saved
     */
    public Tina(Path path, Path archivePath) {
        this.ui = new Ui();
        storage = new Storage(path, archivePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.printLoadingError();
            taskList = new TaskList();
        }
    }

    public Tina() {}

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InvalidFilePathException the invalid file path exception
     */
    public static void main(String[] args) throws InvalidFilePathException {
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "data/Tina.txt");
        Path archviePath = Paths.get(dir, "data/archive/");

        new Tina(path, archviePath).run();
    }

    /**
     * Invoke the main logic of the Tina program.
     *
     * @throws InvalidFilePathException the invalid file path exception
     */
    public void run() throws InvalidFilePathException {
        ui.showWelcome();
        boolean isBye = false;

        while (!isBye) {
            // format user input
            try {
                Command command = Parser.parseInputToCommand(ui.readInput());
                // start line
                ui.printDividerLine();
                command.execute(taskList, ui, storage);
                isBye = command.getIsBye();
            } catch (TinaException e) {
                // end line
                ui.printDividerLine();
                ui.printLine(e.getMessage());
            }

            // end line
            ui.printDividerLine();
        }

        storage.save(taskList);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Tina");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

    }
}



