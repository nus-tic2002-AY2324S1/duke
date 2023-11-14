package com.tina.command;

import com.tina.exception.ExistedFileException;
import com.tina.exception.IOException;
import com.tina.exception.InvalidFilePathException;
import com.tina.exception.InvalidTaskNumberException;
import com.tina.service.Storage;
import com.tina.service.Ui;
import com.tina.task.TaskList;

public class ArchiveCommand extends Command {
    private final String fileName;

    /**
     * Instantiates a new Archive Command.
     * Save all current task list into archive folder and clear current session.
     */
    public ArchiveCommand(String fileName) {
        super(CommandEnum.ARCHIVE);
        this.fileName = fileName;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException, InvalidFilePathException, ExistedFileException, IOException {
        storage.archive(taskList, fileName);
        taskList.clear();
        return "Current tasks are successfully archived as " + storage.getArchivePath() + fileName;
    }
}
