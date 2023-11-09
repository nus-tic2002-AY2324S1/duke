package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandArgument;
import seedu.duke.exceptions.DukeException;

public class WonkyManagerTest {

    @BeforeEach
    public void setUp() throws DukeException {
        WonkyManager.getInstance().resetTaskList();
        CommandArgument todoCmd = new CommandArgument(CommandEnum.TODO, "test task 1");
        WonkyManager.getInstance().executeCommand(todoCmd);
        CommandArgument deadlineCmd = new CommandArgument(CommandEnum.DEADLINE, "test task 1 | 2022-12-31 23:59");
        WonkyManager.getInstance().executeCommand(deadlineCmd);
        CommandArgument eventCmd =
            new CommandArgument(CommandEnum.EVENT, "test task 1 | 2022-12-31 23:59 | 2023-01-01 00:01");
        WonkyManager.getInstance().executeCommand(eventCmd);
    }

    @Test
    public void executeCommand_validCommandArgument_addsTaskToList() throws DukeException {
        CommandArgument cmdArg = new CommandArgument(CommandEnum.TODO, "test task 4");
        WonkyManager.getInstance().executeCommand(cmdArg);
        assertEquals(4, WonkyManager.getInstance().getTasks().size());
        assertEquals("test task 4", WonkyManager.getInstance().getTasks().get(3).getDescription());
    }

    @Test
    public void modifyTask_validCommandArgument_marksTaskAsDone() throws DukeException {
        CommandArgument cmdArg = new CommandArgument(CommandEnum.MARK, "1");
        WonkyManager.getInstance().executeCommand(cmdArg);
        assertEquals(true, WonkyManager.getInstance().getTasks().get(0).getDone());
    }
}
