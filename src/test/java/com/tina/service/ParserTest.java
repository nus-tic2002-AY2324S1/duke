package com.tina.service;

import com.tina.exception.InvalidDateFormatException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseDate_success() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2023-10-30T00:00:00");
        assertEquals(date, Parser.parseDate("30/10/2023"));
    }

    @Test
    public void parseDate_invalidDate_exceptionThrown() {
        LocalDateTime date = LocalDateTime.parse("2023-10-30T00:00:00");
        try {
            assertEquals(date, Parser.parseDate("30/30/2020"));
        } catch (InvalidDateFormatException e) {
            assertEquals("OOPS!!! Invalid date or invalid date format. Please use format: dd/mm/yyyy, e.g., 2/12/2019", e.getMessage());
        }
    }

    @Test
    public void parseDate_nonsenseText_exceptionThrown() {
        LocalDateTime date = LocalDateTime.parse("2023-10-30T00:00:00");
        try {
            assertEquals(date, Parser.parseDate("abcdefg"));
        } catch (InvalidDateFormatException e) {
            assertEquals("OOPS!!! Invalid date or invalid date format. Please use format: dd/mm/yyyy, e.g., 2/12/2019", e.getMessage());
        }
    }
}
