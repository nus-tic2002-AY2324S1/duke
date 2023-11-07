package com.tina;

import com.tina.command.Command;
import com.tina.exception.TinaException;
import com.tina.exception.InvalidFilePathException;
import com.tina.service.Parser;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.*;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The main program Tina, a chatbot, allows to schedule tasks with flexible functions.
 *
 */
public class Tina {

    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

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

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InvalidFilePathException the invalid file path exception
     */
    public static void main(String[] args) throws InvalidFilePathException {
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "data/Tina.txt");
        Path archviePath = Paths.get(dir, "archive");

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
}



