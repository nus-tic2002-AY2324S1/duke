package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static duke.command.Command.DATE_TIME_ERR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventCommandTest {
    EventCommand evt = new EventCommand();
    TaskList taskList;
    Ui ui;
    String err = "";

    @BeforeEach
    void setUp() throws Exception {
        taskList = new TaskList(TestUtil.getTestTasks());
        ui = new Ui();
    }

    @Test
    void executeCommand_missingParameter_errorMessage() {
        String errMsg = String.format(Command.DESC_ERR_MESSAGE, EventCommand.COMMAND_WORD);
        String expect = Message.concat(errMsg, EventCommand.EXAMPLE_USAGE);
        UserKeywordArgument keywordArgument = new UserKeywordArgument("event");
        try {
            evt.executeCommand(taskList,ui, keywordArgument);
        } catch (InvalidArgumentException e) {
            err = e.getMessage();
        }
        assertEquals(expect, err);
    }

}