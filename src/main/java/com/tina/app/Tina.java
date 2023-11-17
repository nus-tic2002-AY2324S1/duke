package com.tina.app;

import com.tina.command.Command;
import com.tina.exception.InvalidFileFormatException;
import com.tina.exception.InvalidFilePathException;
import com.tina.exception.TinaException;
import com.tina.service.Parser;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.TaskList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents the main program Tina, a chatbot, allows to schedule tasks with flexible
 * functions.
 */
public class Tina {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Tina() {
    }

    /**
     * Initiate the Tina object.
     * Sets saving and archiving path based on user home directory.
     *
     * @throws InvalidFileFormatException if loading file format is invalid
     * @throws InvalidFilePathException   if loading file path is invalid
     */
    public void initiate() throws com.tina.exception.IOException, InvalidFileFormatException,
            InvalidFilePathException {
        String dir = System.getProperty("user.home");
        Path path = Paths.get(dir, "tina/");
        Path archviePath = Paths.get(dir, "tina/archive/");
        try {
            Files.createDirectories(path);
            Files.createDirectories(archviePath);
        } catch (IOException e) {
            throw new com.tina.exception.IOException();
        }
        path = path.resolve("Tina.txt");
        this.setPath(path, archviePath);
    }

    /**
     * Sets the saving and archiving path.
     *
     * @param path        the saving path
     * @param archivePath the archiving path
     * @throws InvalidFileFormatException if loading file format is invalid
     * @throws InvalidFilePathException   if loading file path is invalid
     */
    public void setPath(Path path, Path archivePath) throws InvalidFileFormatException,
            InvalidFilePathException {
        this.ui = new Ui();
        storage = new Storage(path, archivePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            taskList = new TaskList();
            throw e;
        }
    }

    /**
     * Gets response based on user input.
     *
     * @param input the input from user
     * @return the response
     */
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



