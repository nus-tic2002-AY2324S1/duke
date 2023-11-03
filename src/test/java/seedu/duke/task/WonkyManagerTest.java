package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandArgument;
import seedu.duke.exceptions.DukeException;

public class WonkyManagerTest {

    @BeforeEach
    public void setUp() throws DukeException {
        WonkyManager.resetTaskList();
        CommandArgument todoCmd = new CommandArgument(Command.TODO, "test task 1");
        WonkyManager.executeCommand(todoCmd);
        CommandArgument deadlineCmd = new CommandArgument(Command.DEADLINE, "test task 1 | 2022-12-31 23:59");
        WonkyManager.executeCommand(deadlineCmd);
        CommandArgument eventCmd =
            new CommandArgument(Command.EVENT, "test task 1 | 2022-12-31 23:59 | 2023-01-01 00:01");
        WonkyManager.executeCommand(eventCmd);
    }

    @Test
    public void executeCommand_validCommandArgument_addsTaskToList() throws DukeException {
        CommandArgument cmdArg = new CommandArgument(Command.TODO, "test task 4");
        WonkyManager.executeCommand(cmdArg);
        assertEquals(4, WonkyManager.getTasks().size());
        assertEquals("test task 4", WonkyManager.getTasks().get(3).getDescription());
    }

    @Test
    public void modifyTask_validCommandArgument_marksTaskAsDone() throws DukeException {
        CommandArgument cmdArg = new CommandArgument(Command.MARK, "1");
        WonkyManager.executeCommand(cmdArg);
        assertEquals(true, WonkyManager.getTasks().get(0).getDone());
    }
}
