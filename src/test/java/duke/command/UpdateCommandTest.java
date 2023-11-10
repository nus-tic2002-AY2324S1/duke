package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.util.TestUtil;
import duke.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import java.nio.file.Path;
import java.time.LocalDateTime;

import static duke.command.Command.DATE_TIME_ERR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpdateCommandTest {
    UpdateCommand up = new UpdateCommand();
    TaskList taskList;
    Ui ui;
    String err = "";

    @BeforeEach
    void setUp() throws Exception {
        taskList = new TaskList(TestUtil.getTestTasks());
        ui = new Ui();
    }

    @Test
    void executeCommand_eventToBeforeFrom_failed() {
        String expect = Message.concat(EventCommand.DATE_TIME_ERROR_MESSAGE, EventCommand.EXAMPLE_USAGE);
        try {
            up.executeCommand(taskList, ui, new UserKeywordArgument("update 3 /to 06/11/2022 2039"));
        } catch (InvalidArgumentException e) {
            err = e.getMessage();
        }
        assertEquals(expect, err);
    }

    @Test
    void executeCommand_deadlineUpdateBy_success() throws InvalidArgumentException {
        LocalDateTime datetime = LocalDateTime.of(2023, 11, 6, 0, 0);
        Deadline expectDeadline = new Deadline(false, "return book", datetime);
        up.executeCommand(taskList, ui, new UserKeywordArgument("update 2 /by 06/11/2023"));
        assertEquals(expectDeadline.toString(), taskList.get(1).toString());
    }

    @Test
    void executeCommand_eventUpdateTo_success() throws InvalidArgumentException {
        LocalDateTime from = LocalDateTime.of(2023, 12, 2, 3, 30);
        LocalDateTime to = LocalDateTime.of(2023, 12, 2, 21, 0);
        Event expectEvent = new Event(true, "project meeting", from, to);
        up.executeCommand(taskList, ui, new UserKeywordArgument("update 3 /to 02/12/2023 2100"));
        assertEquals(expectEvent.toString(), taskList.get(2).toString());
    }

    @Test
    void isValidIndexCommand_emptyArgument_throwException() {
        String errMsg = String.format(IndexBaseCommand.DESC_ERR_MESSAGE, UpdateCommand.COMMAND_WORD);
        String expect = Message.concat(errMsg, UpdateCommand.EXAMPLE_USAGE);
        try {
            up.isValidArgument(new UserKeywordArgument("update"), taskList);
        } catch (InvalidArgumentException e) {
            err = e.getMessage();
        }
        assertEquals(expect, err);
    }

    @Test
    public void isValidIndexCommand_nonInteger_failed() throws InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> up.isValidArgument(new UserKeywordArgument("update a"),
                taskList));
    }

    @Test
    public void isValidArgument_onlyIntegerArgument_failed() throws InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> up.isValidArgument(new UserKeywordArgument("update 1"),
                taskList));
    }

    @Test
    public void isValidArgument_argumentKeyInvalid_failed() throws InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> up.isValidArgument(new UserKeywordArgument("update 1 /de"),
                taskList));
    }

    @Test
    public void isValidArgument_emptyArgument_failed() throws InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> up.isValidArgument(new UserKeywordArgument("update 1 /desc"),
                taskList));
    }

    @Test
    public void isValidArgument_wrongDateFormat_failed() {
        String errMsg = String.format(DATE_TIME_ERR_MESSAGE, "date", UpdateCommand.COMMAND_WORD);
        String expect = Message.concat(errMsg, UpdateCommand.EXAMPLE_USAGE);
        try {
            up.isValidArgument(new UserKeywordArgument("update 2 /by Sunday"), taskList);
        } catch (InvalidArgumentException e) {
            err = e.getMessage();
        }
        assertEquals(expect, err);
    }

    @Test
    public void isValidArgument_todoTaskWrongKey_failed() throws InvalidArgumentException {
        try {
            up.isValidArgument(new UserKeywordArgument("update 1 /by borrow book"), taskList);
        } catch (InvalidArgumentException e) {
            err = e.getMessage();
        }
        assertEquals(UpdateCommand.UPDATE_ERROR_BY, err);
    }

    @Test
    public void isValidArgument_deadlineWrongKey_failed() throws InvalidArgumentException {
        try {
            up.isValidArgument(new UserKeywordArgument("update 2 /from 05/11/2023 2300"), taskList);
        } catch (InvalidArgumentException e) {
            err = e.getMessage();
        }
        assertEquals(UpdateCommand.UPDATE_ERROR_FROM, err);
    }

    @Test
    public void isValidArgument_eventWrongKey_failed() throws InvalidArgumentException {
        try {
            up.isValidArgument(new UserKeywordArgument("update 3 /by 15/11/2023"), taskList);
        } catch (InvalidArgumentException e) {
            err = e.getMessage();
        }
        assertEquals(UpdateCommand.UPDATE_ERROR_BY, err);
    }

    @Test
    public void isValidArgument_deadlineTaskDesc_success() throws InvalidArgumentException {
        assertTrue(up.isValidArgument(new UserKeywordArgument("update 2 /desc borrow book"), taskList));
    }

    @Test
    public void isValidArgument_deadlineTaskBy_success() throws InvalidArgumentException {
        assertTrue(up.isValidArgument(new UserKeywordArgument("update 2 /by 05/11/2023"), taskList));
    }

    @Test
    public void isValidArgument_eventTaskBy_success() throws InvalidArgumentException {
        assertTrue(up.isValidArgument(new UserKeywordArgument("update 3 /to 05/11/2023 1500"), taskList));
    }
}