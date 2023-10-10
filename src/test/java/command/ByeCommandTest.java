package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByeCommandTest {

    @Test
    void handleCommand() {
        ByeCommand byeCommand = new ByeCommand();
        byeCommand.handleCommand("bye", null);
        assertEquals(0, byeCommand.handleCommand("bye", null));
    }
}