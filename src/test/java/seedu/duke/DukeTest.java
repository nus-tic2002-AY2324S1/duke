package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.DukeException;
import seedu.duke.helper.WonkyMode;

public class DukeTest {
    @Test
    public void emptyArgsReturnNormalMode() {
        String[] args = {};
        WonkyMode mode = Duke.checkMode(args);
        assertEquals(WonkyMode.NORMAL, mode);
    }

    @Test
    public void invalidModeReturnNormalMode() {
        String[] args = {"invalid_mode"};
        WonkyMode mode = Duke.checkMode(args);
        assertEquals(WonkyMode.NORMAL, mode);
    }

    @Test
    public void validModeReturnSpecifiedMode() {
        String[] args = {WonkyMode.TEST.getLitr()};
        WonkyMode mode = Duke.checkMode(args);
        assertEquals(WonkyMode.TEST, mode);
    }

    @Test
    public void initialiseTestModeSuccess() {
        try {
            Duke.initialise(WonkyMode.TEST);
        } catch (DukeException e) {
            fail("Unexpected DukeException thrown");
        }
    }
}
