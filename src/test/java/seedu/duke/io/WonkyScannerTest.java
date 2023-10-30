package seedu.duke.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.DukeException;

public class WonkyScannerTest {

    @Test
    public void processNextLine_validCommandArgument_returnsTrue() throws DukeException {
        String input = "todo test task";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        boolean result = WonkyScanner.processNextLine(input);
        assertEquals(true, result);
    }

    @Test
    public void typoSuggestion_validTypo_returnsSuggestion() {
        String input = "evend";
        String result = WonkyScanner.typoSuggestion(input);
        assertEquals("event", result);
    }

    @Test
    public void typoSuggestion_invalidTypo_returnsNull() {
        String input = "invalidtypo";
        String result = WonkyScanner.typoSuggestion(input);
        assertEquals(null, result);
    }

    @Test
    public void bye_notLoading_closesScanner() throws DukeException {
        WonkyLogger.setIsLoading(false);
        WonkyScanner.bye();
        assertEquals(false, WonkyScanner.getActive());
    }
}
