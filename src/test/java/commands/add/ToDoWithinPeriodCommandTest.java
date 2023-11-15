import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import exceptions.DukeException;
import commands.add.ToDoWithinPeriodCommand;
import constants.ErrorMessages;

public class ToDoWithinPeriodCommandTest {

    @Test
    public void ToDoWithinPeriodConstructorTest() throws DukeException{
    // Test Set Up
    Throwable exception;

    // Working Test Case
    ToDoWithinPeriodCommand t = new ToDoWithinPeriodCommand("todo colllect certificate /between 2023-10-08 /and 2023-10-10");

    // Test Case 1: Missing Task Description
    exception = assertThrows(DukeException.class, () -> new ToDoWithinPeriodCommand("todo /between 2023 /and 2023-10-20"));
    assertEquals(ErrorMessages.INVALID_TODO_WITHIN_PERIOD_FORMAT, exception.getMessage());

    // Test Case 2: Second Date is after First Date
    exception = assertThrows(DukeException.class, () -> new ToDoWithinPeriodCommand("todo collect certificate /between 2023-10-08 /and 2023-10-07"));
    assertEquals(ErrorMessages.ERROR_END_DATE_BEFORE_START_DATE, exception.getMessage());
    }

}
