package com.tina.command;

import com.tina.exception.ExistedFileException;
import com.tina.exception.IOException;
import com.tina.exception.InvalidFilePathException;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.TaskList;

/**
 * Represents an archive command.
 */
public class ArchiveCommand extends Command {
    private final String fileName;

    /**
     * Instantiates a new Archive Command.
     * Save all current task list into archive folder and clear current task list.
     *
     * @param fileName the file name
     */
    public ArchiveCommand(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Archives the current task list as desired file name.
     * Clears current task list.
     * Saves the new blank task list.
     *
     * @param taskList the task list.
     * @param ui       the ui util.
     * @param storage  the storage util.
     * @return successful message.
     * @throws InvalidFilePathException if file path is invalid.
     * @throws ExistedFileException     if a file with desired file name is already
     *                                  existed.
     * @throws IOException              if any IO error occurs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidFilePathException,
            ExistedFileException, IOException {
        storage.archive(taskList, fileName);
        taskList.clear();
        storage.save(taskList);
        return "Current tasks are successfully archived as " + storage.getArchivePath() + fileName;
    }
}
