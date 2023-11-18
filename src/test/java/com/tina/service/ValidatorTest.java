package com.tina.service;

import com.tina.command.CommandEnum;
import com.tina.exception.InvalidParameterException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorTest {

    @Test
    public void validateTaskNumber_notNumber_ExceptionThrown() {
        ArrayList<String> tokens = new ArrayList<>();
        tokens.add("mark");
        tokens.add("abc");
        assertThrows(InvalidParameterException.class,() -> Validator.validateTaskNumber(tokens, CommandEnum.MARK));
    }

    @Test
    public void validateTaskNumber_noNumberGiven_ExceptionThrown() {
        ArrayList<String> tokens = new ArrayList<>();
        tokens.add("mark");
        assertThrows(InvalidParameterException.class,() -> Validator.validateTaskNumber(tokens, CommandEnum.MARK));
    }
}
