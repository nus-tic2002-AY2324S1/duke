package com.tina.service;

import com.tina.command.Command;
import com.tina.exception.TinaException;
import com.tina.exception.InvalidFilePathException;
import com.tina.task.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    public void start(Stage primaryStage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        Stage stage = new Stage();
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}



