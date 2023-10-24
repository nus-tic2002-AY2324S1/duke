package utils;

import java.time.*;
import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void parseNextDay() {
        assertEquals("2023-10-25T23:59", Objects.requireNonNull(DateTimeUtils.parseNextDay("today")).toString());
    }

}