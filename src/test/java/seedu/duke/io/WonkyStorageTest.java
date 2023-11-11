package seedu.duke.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandType;
import seedu.duke.commands.types.DeadlineCommand;
import seedu.duke.commands.types.EventCommand;
import seedu.duke.commands.types.TodoCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.WonkyManager;

public class WonkyStorageTest {
    private WonkyStorage wonkyStorage;
    private WonkyLogger wonkyLogger;
    private WonkyManager wonkyManager;
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

        wonkyScanner.setScannerMode();
        wonkyManager.resetCmdTypes();
    }

    @Test
    public void addStashPopStashIssuccess() throws DukeException, IOException {
        List<CommandType> cmdTypes = new ArrayList<>();
        cmdTypes.add(new TodoCommand(CommandEnum.TODO, "test todo"));
        cmdTypes.add(new DeadlineCommand(CommandEnum.DEADLINE, "test deadline | today "));
        cmdTypes.add(new EventCommand(CommandEnum.EVENT, "test event | today | tmr "));
        wonkyStorage.addStash("testStash2", cmdTypes);

        File stashFile = new File("./stash/testStash2");
        assertTrue(stashFile.exists());
        assertEquals(3, countLines(stashFile));

        wonkyStorage.popStash("testStash");
        assertTrue(!stashFile.exists());
        assertEquals(3, wonkyManager.getTasks().size());
    }

    @Test
    public void applyStash_validStash_success() throws DukeException, IOException {
        List<CommandType> cmdTypes = new ArrayList<>();
        cmdTypes.add(new TodoCommand(CommandEnum.TODO, "test todo"));
        cmdTypes.add(new DeadlineCommand(CommandEnum.DEADLINE, "test deadline | today "));
        cmdTypes.add(new EventCommand(CommandEnum.EVENT, "test event | today | tmr "));
        wonkyStorage.addStash("testStash3", cmdTypes);

        wonkyStorage.applyStash("testStash3");
        assertEquals(3, wonkyManager.getCmdTypes().size());
    }

    @Test
    public void clearStash_validStash_success() throws DukeException, IOException {
        List<CommandType> cmdTypes = new ArrayList<>();
        cmdTypes.add(new TodoCommand(CommandEnum.TODO, "test todo"));
        cmdTypes.add(new DeadlineCommand(CommandEnum.DEADLINE, "test deadline | today "));
        cmdTypes.add(new EventCommand(CommandEnum.EVENT, "test event | today | tmr "));
        wonkyStorage.addStash("testStash", cmdTypes);

        wonkyStorage.clearStash();
        File stashFolder = new File("./stash/");
        assertTrue(stashFolder.exists());
        assertEquals(0, stashFolder.list().length);
    }

    private int countLines(File file) throws IOException {
        int count = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }
        }
        return count;
    }
}
