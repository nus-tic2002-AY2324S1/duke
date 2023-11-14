package com.tina.app;

import com.tina.GUI.DialogBox;
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
 */
public class Tina {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Tina() {
    }

    public void setPath(Path path, Path archivePath) {
        this.ui = new Ui();
        storage = new Storage(path, archivePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.printLoadingError();
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        String msg;
        try {
            Command command = Parser.parseInputToCommand(input);
            msg = command.execute(taskList, ui, storage);
        } catch (TinaException e) {
            msg = e.getMessage();
        }
        return msg;
    }
}



