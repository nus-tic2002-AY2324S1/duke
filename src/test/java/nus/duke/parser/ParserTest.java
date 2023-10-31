package nus.duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import nus.duke.data.TaskSource;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void parseTaskSource_withoutOptions() throws InvalidCommandArgsDukeException {
        TaskSource taskSource = Parser.parseTaskSource("event project meeting");
        assertEquals("event project meeting", taskSource.getDescription());
        assertEquals(0, taskSource.getOptions().size());
    }

    @Test
    void parseTaskSource_withOptions() throws InvalidCommandArgsDukeException {
        TaskSource taskSource = Parser.parseTaskSource(
            "event project meeting /from 2019-9-1 1430 /to 1630 /after 1 /key value");
        assertEquals("event project meeting", taskSource.getDescription());
        assertEquals(4, taskSource.getOptions().size());
        assertEquals(Optional.of("2019-9-1 1430"), taskSource.getOptionValue("from"));
        assertEquals(Optional.of("1630"), taskSource.getOptionValue("to"));
        assertEquals(Optional.of("1"), taskSource.getOptionValue("after"));
        assertEquals(Optional.of("value"), taskSource.getOptionValue("key"));
    }

}