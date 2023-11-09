package seedu.duke.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class WonkyScannerTest {

    @Test
    public void typoSuggestion_validTypo_returnsSuggestion() {
        String input = "evend";
        String result = WonkyScanner.getInstance().typoSuggestion(input);
        assertEquals("event", result);
    }

    @Test
    public void typoSuggestion_invalidTypo_returnsNull() {
        String input = "invalidtypo";
        String result = WonkyScanner.getInstance().typoSuggestion(input);
        assertEquals(null, result);
    }
}
