package com.tina;

import com.tina.command.Command;
import com.tina.exception.DukeException;
import com.tina.exception.InvalidFilePathException;
import com.tina.service.Parser;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.*;

import java.nio.file.Path;

/**
 * The main program Tina, a chatbot, allows to schedule tasks with flexible functions.
 *
 */
public class Tina {

    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

    private static final String PATH = "data/Tina.txt";

    /**
     * Instantiates a new Tina program.
     *
     * @param path the path where task list to be loaded or saved
     */
    public Tina(Path path) {
        this.ui = new Ui();
        storage = new Storage(path);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.printLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InvalidFilePathException the invalid file path exception
     */
    public static void main(String[] args) throws InvalidFilePathException {
        String dir = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(dir, Tina.PATH);

        new Tina(path).run();
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
                command.execute(taskList, ui);
                isBye = command.getIsBye();
            } catch (DukeException e) {
                // end line
                ui.printDividerLine();
                ui.printLine(e.getMessage());
            }

            // end line
            ui.printDividerLine();
        }

        storage.save(taskList);
    }
}



