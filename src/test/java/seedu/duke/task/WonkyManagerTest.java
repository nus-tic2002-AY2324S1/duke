package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.commands.types.TodoCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.helper.WonkyMode;
import seedu.duke.io.WonkyLogger;
import seedu.duke.io.WonkyScanner;
import seedu.duke.io.WonkyStorage;

public class WonkyManagerTest {
    private WonkyManager wonkyManager;
    private WonkyLogger wonkyLogger;
    private WonkyStorage wonkyStorage;
    private WonkyScanner wonkyScanner;

    @BeforeEach
    public void setUp() throws DukeException {
        wonkyLogger = WonkyLogger.getInstance();
        wonkyStorage = WonkyStorage.getInstance();
        wonkyScanner = WonkyScanner.getInstance();
        wonkyManager = WonkyManager.getInstance();

        wonkyStorage.setReferences(wonkyLogger, wonkyManager, wonkyScanner);
        wonkyManager.setReferences(wonkyLogger, wonkyStorage);
        wonkyScanner.setReferences(wonkyLogger, wonkyManager);

        wonkyStorage.startUp(WonkyMode.TEST);
        wonkyLogger.startUp(WonkyMode.TEST);
        wonkyScanner.setScannerMode();
    }

    @Test
    public void checkCommand_validCommand_success() throws DukeException {
        wonkyManager.checkCommand(CommandEnum.TODO, "test todo");
        assertEquals(2, wonkyManager.getTasks().size());
    }

    @Test
    public void checkCommand_invalidCommand_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> wonkyManager.checkCommand(CommandEnum.getEnum("UNKNOWN"), ""));
    }

    @Test
    public void addTask_validTask_success() throws DukeException {
        Task task = new Todo("test todo");
        wonkyManager.addTask(task);
        assertEquals(1, wonkyManager.getTasks().size());
    }

    @Test
    public void addCmdType_validCmdType_success() throws DukeException {
        CommandType cmdType = new TodoCommand(CommandEnum.TODO, "test todo");
        wonkyManager.addCmdType(cmdType);
        assertEquals(2, wonkyManager.getCmdTypes().size());
    }

    @Test
    public void resetCmdTypes_validCmdTypes_success() throws DukeException {
        CommandType cmdType = new TodoCommand(CommandEnum.TODO, "test todo");
        wonkyManager.addCmdType(cmdType);
        wonkyManager.resetCmdTypes();
        assertEquals(0, wonkyManager.getCmdTypes().size());
    }
}