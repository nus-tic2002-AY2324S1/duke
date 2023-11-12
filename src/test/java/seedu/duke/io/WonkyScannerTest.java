package seedu.duke.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class WonkyScannerTest {

    @Test
    public void validTypoReturnsSuggestion() {
        String input = "evend";
        String result = WonkyScanner.getInstance().typoSuggestion(input);
        assertEquals("event", result);
    }

    @Test
    public void invalidTypoReturnsNull() {
        String input = "invalidtypo";
        String result = WonkyScanner.getInstance().typoSuggestion(input);
        assertEquals(null, result);
    }
}
