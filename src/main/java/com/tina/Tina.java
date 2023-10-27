package com.tina;

import com.tina.command.Command;
import com.tina.exception.DukeException;
import com.tina.exception.InvalidFilePathException;
import com.tina.task.*;

import java.nio.file.Path;

public class Tina {

    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

    private static final String PATH = "data/Tina.txt";

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

    public static void main(String[] args) throws InvalidFilePathException {
        String dir = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(dir, Tina.PATH);

        new Tina(path).run();
    }

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



