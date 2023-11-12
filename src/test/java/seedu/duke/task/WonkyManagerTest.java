package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        WonkyLogger.reset();
        WonkyStorage.reset();
        WonkyScanner.reset();
        WonkyManager.reset();
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
        wonkyManager.resetCmdTypes();
    }

    @Test
    public void executeCommand_validCommand_success() throws DukeException {
        assertEquals(0, wonkyManager.getTasks().size());

        wonkyManager.executeCommand(CommandEnum.TODO, "test todo");
        wonkyManager.executeCommand(CommandEnum.DEADLINE, "test deadline | today ");
        wonkyManager.executeCommand(CommandEnum.EVENT, "test event | today | tmr ");
        assertEquals(3, wonkyManager.getTasks().size());

        wonkyManager.executeCommand(CommandEnum.TODO, "invalid todo | invalid");
        wonkyManager.executeCommand(CommandEnum.MARK, "1");
        assertEquals(3, wonkyManager.getTasks().size());
        assertEquals(4, wonkyManager.getCmdTypes().size());
        assertTrue(wonkyManager.getTasks().get(0).getDone());

        wonkyManager.executeCommand(CommandEnum.DELETE, "2");
        wonkyManager.executeCommand(CommandEnum.UNMARK, "1");
        assertEquals(2, wonkyManager.getTasks().size());
        assertEquals(6, wonkyManager.getCmdTypes().size());
        assertFalse(wonkyManager.getTasks().get(0).getDone());
    }

    @Test
    public void executeCommand_invalidCommand_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () ->
            wonkyManager.executeCommand(CommandEnum.getEnum("UNKNOWN"), ""));
    }

    @Test
    public void resetCmdTypes_validCmdTypes_success() throws DukeException {
        CommandType cmdType = new TodoCommand(CommandEnum.TODO, "test todo");
        wonkyManager.addCmdType(cmdType);
        wonkyManager.resetCmdTypes();
        assertEquals(0, wonkyManager.getCmdTypes().size());
    }
}
