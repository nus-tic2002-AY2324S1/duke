package wargames;
import commands.Command;
import commands.ByeCommand;
import exceptions.IllegalStorageFormat;
import exceptions.InvalidCommandException;
import task.Task;
import storage.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private JoshuaUi ui;
    private JoshuaParser parser;

    public Duke() {
        ui = new JoshuaUi();
        parser = new JoshuaParser();
        storage = new Storage();
        try {
            taskList = storage.load();
            ui.printSuccessfulLoad();
        } catch (FileNotFoundException | IllegalStorageFormat e) {
            ui.printLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.printGreetings();
        Command c;
        do {
            String fullCommand = ui.readCommand();
            c = parser.parse(fullCommand);
            c.execute(taskList, ui, storage);

        } while(!ByeCommand.isExit(c));
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
