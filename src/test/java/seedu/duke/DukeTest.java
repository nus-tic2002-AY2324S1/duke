package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.DukeException;
import seedu.duke.helper.WonkyMode;

public class DukeTest {
    @Test
    public void checkMode_emptyArgs_returnsNormalMode() {
        String[] args = {};
        WonkyMode mode = Duke.checkMode(args);
        assertEquals(WonkyMode.NORMAL, mode);
    }

    @Test
    public void checkMode_invalidMode_returnsNormalMode() {
        String[] args = {"invalid_mode"};
        WonkyMode mode = Duke.checkMode(args);
        assertEquals(WonkyMode.NORMAL, mode);
    }

    @Test
    public void checkMode_validMode_returnsSpecifiedMode() {
        String[] args = {WonkyMode.TEST.getLitr()};
        WonkyMode mode = Duke.checkMode(args);
        assertEquals(WonkyMode.TEST, mode);
    }

    @Test
    public void initialise_testMode_success() {
        try {
            Duke.initialise(WonkyMode.TEST);
        } catch (DukeException e) {
            fail("Unexpected DukeException thrown");
        }
    }
}
