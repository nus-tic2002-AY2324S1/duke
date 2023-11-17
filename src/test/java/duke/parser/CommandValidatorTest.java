package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.EmptyArgumentException;
import duke.dukeexceptions.InvalidDateFormatException;
import duke.dukeexceptions.TaskNotFoundException;
import duke.filehandler.FileStorage;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import duke.userinterface.UserInterface.MessageDisplay;

class CommandValidatorTest {
    private FileStorage fileStorage;
    private MessageDisplay messageDisplay;
    private List<Task> taskList;
    private CommandValidator commandValidator;
    private ByteArrayOutputStream outputStream;
    @BeforeEach
    void setUp() {
        fileStorage = new FileStorage();
        messageDisplay = new MessageDisplay();
        taskList = new ArrayList<>();
        commandValidator = new CommandValidator();
        outputStream = new ByteArrayOutputStream();
    }
    /**
     * Clear the task list after each test case.
    */
    @AfterEach
    public void tearDown() {
        taskList.clear();
    }
    @Test
    void executeCommand_validListCommand_shouldExecuteListCommand() throws DukeException {
        System.setOut(new PrintStream(outputStream));
        taskList.add(new TodoTask("Sample Task"));
        commandValidator.executeCommand(fileStorage, messageDisplay, taskList, CommandValidator.LIST_COMMAND, "list");
        String expectedOutput = "Here are the tasks in your list:" + System.lineSeparator()
                    + MessageDisplay.LINE_BREAK + System.lineSeparator()
            + "1.[T][ ] Sample Task" + System.lineSeparator()
            + MessageDisplay.LINE_BREAK + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
        System.setOut(System.out);
    }
    @Test
    void executeCommand_validTodoCommand_shouldExecuteTodoCommand() throws DukeException {
        System.setOut(new PrintStream(outputStream));
        commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
            CommandValidator.TODO_COMMAND, "todo Buy groceries");

        String expectedOutput =
                "Got it. I've added this task:" + System.lineSeparator()
                + "   [T][ ] Buy groceries" + System.lineSeparator()
                + "Now you have 1 tasks in the list." + System.lineSeparator()
                + MessageDisplay.LINE_BREAK + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof TodoTask);
    }

    @Test
    void executeCommand_emptyTodoArgument_shouldThrowEmptyTodoArgumentException() {
        DukeException exception = assertThrows(EmptyArgumentException.class, () -> commandValidator.executeCommand(
            fileStorage, messageDisplay, taskList, CommandValidator.TODO_COMMAND, "todo"));
        String expectedOutput = "The description of a todo task cannot be empty!";
        assertEquals(expectedOutput, exception.getMessage());
    }

    @Test
    void executeCommand_validDeadlineCommand_shouldExecuteDeadlineCommand() throws DukeException {
        System.setOut(new PrintStream(outputStream));
        commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
            CommandValidator.DEADLINE_COMMAND, "deadline Homework /by 2023-12-31 18:00");
        String expectedOutput = "Got it. I've added this task:" + System.lineSeparator()
            + "   [D][ ] Homework (by: Sun 31 Dec 2023 18:00)" + System.lineSeparator()
            + "Now you have 1 tasks in the list." + System.lineSeparator()
            + MessageDisplay.LINE_BREAK + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof DeadlineTask);
    }
    @Test
    void executeCommand_emptyDeadlineArgument_shouldThrowEmptyDeadlineArgumentException() {
        DukeException exception = assertThrows(EmptyArgumentException.class, () -> commandValidator.executeCommand(
            fileStorage, messageDisplay, taskList, CommandValidator.DEADLINE_COMMAND, "deadline"));

        assertEquals("The description of a deadline task cannot be empty!", exception.getMessage());
    }

    @Test
    void executeCommand_validEventCommand_shouldExecuteEventCommand() throws DukeException {
        System.setOut(new PrintStream(outputStream));
        commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
            CommandValidator.EVENT_COMMAND, "event Homework /from 2023-12-31 18:00 /to 2024-01-01 18:00");
        String expectedOutput = "Got it. I've added this task:" + System.lineSeparator()
            + "   [E][ ] Homework (from: Sun 31 Dec 2023 18:00 to: Mon 01 Jan 2024 18:00)" + System.lineSeparator()
            + "Now you have 1 tasks in the list." + System.lineSeparator()
            + MessageDisplay.LINE_BREAK + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof EventTask);
    }

    @Test
    void executeCommand_emptyEventArgument_shouldThrowEmptyEventArgumentException() {
        DukeException exception = assertThrows(EmptyArgumentException.class, () ->
            commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
                CommandValidator.EVENT_COMMAND, "event"));

        assertEquals("The description of a event task cannot be empty!", exception.getMessage());
    }

    @Test
    void executeCommand_validOnCommand_shouldExecuteOnCommand() throws DukeException {
        System.setOut(new PrintStream(outputStream));
        LocalDateTime date = DukeParser.parseDateTimeOrDate("2023-11-11");
        taskList.add(new DeadlineTask("Sample Task", date));
        commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
            CommandValidator.ON_COMMAND, "on 2023-11-11");
        String expectedOutput = "Here are the tasks in your list as of 2023-11-11" + System.lineSeparator()
            + MessageDisplay.LINE_BREAK + System.lineSeparator()
            + "1.[D][ ] Sample Task (by: Sat 11 Nov 2023 00:00)" + System.lineSeparator()
            + MessageDisplay.LINE_BREAK + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void executeCommand_emptyOnArgument_shouldThrowEmptyOnArgumentException() {
        DukeException exception = assertThrows(EmptyArgumentException.class, () ->
            commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
                CommandValidator.ON_COMMAND, "on"));
        assertEquals("Please provide the specific date that you want to check if a task exists!",
            exception.getMessage());
    }

    @Test
     void executeCommand_invalidOnArgument_shouldThrowInvalidDateFormatException() {
        DukeException exception = assertThrows(InvalidDateFormatException.class, () ->
            commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
                CommandValidator.ON_COMMAND, "on 2023/01/01"));

        assertEquals("Please provide date in the format of yyyy-mm-dd hh:mm:ss", exception.getMessage());
    }

    @Test
    void executeCommand_validFindCommand_shouldExecuteFindCommand() throws DukeException {
        System.setOut(new PrintStream(outputStream));
        taskList.add(new TodoTask("borrow book"));
        taskList.add(new TodoTask("return book"));
        commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
            CommandValidator.FIND_COMMAND, "find book");

        String expectedOutput = "Here are the matching tasks in your list:" + System.lineSeparator()
            + MessageDisplay.LINE_BREAK + System.lineSeparator()
            + "1.[T][ ] borrow book" + System.lineSeparator()
            + "2.[T][ ] return book" + System.lineSeparator()
            + MessageDisplay.LINE_BREAK + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void executeCommand_emptyFindArgument_shouldThrowEmptyFindArgumentException() {
        DukeException exception = assertThrows(EmptyArgumentException.class, () ->
            commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
                CommandValidator.FIND_COMMAND, "find"));
        assertEquals("Please provide the specific keyword that you want to check if a task exists!",
            exception.getMessage());
    }
    @Test
    void executeCommand_validRescheduleCommand_shouldExecuteRescheduleCommand() throws DukeException {
        LocalDateTime date = DukeParser.parseDateTimeOrDate("2050-01-01 18:00");
        taskList.add(new DeadlineTask("Sample Task", date));
        commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
            CommandValidator.RESCHEDULE_COMMAND, "reschedule 1 2050-01-01 18:00");
        assertEquals(taskList.get(0).getTaskEndDate(), date);
    }

    @Test
    void executeCommand_emptyRescheduleArgument_shouldThrowInvalidRescheduleArgumentException() {
        DukeException exception = assertThrows(EmptyArgumentException.class, () ->
            commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
                CommandValidator.RESCHEDULE_COMMAND, "reschedule"));
        assertEquals("Please provide the specific task and date that you want to postpone!", exception.getMessage());
    }

    @Test
    void executeCommand_invalidRescheduleArgument_shouldThrowTaskNotFoundException() {
        System.setOut(new PrintStream(outputStream));
        LocalDateTime date = DukeParser.parseDateTimeOrDate("2050-01-01");
        taskList.add(new DeadlineTask("Sample Task", date));
        DukeException exception = assertThrows(TaskNotFoundException.class, () ->
            commandValidator.executeCommand(fileStorage, messageDisplay, taskList,
                CommandValidator.RESCHEDULE_COMMAND, "reschedule 2 2023-12-31"));
    }

}
