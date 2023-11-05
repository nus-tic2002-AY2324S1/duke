package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.task.TaskList;
import duke.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static duke.command.Command.DATE_TIME_ERR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpdateCommandTest {
    UpdateCommand up = new UpdateCommand();
    TaskList taskList;
    String err = "";

    @BeforeEach
    void setUp() {
        taskList = new TaskList(TestUtil.getTestTasks());
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