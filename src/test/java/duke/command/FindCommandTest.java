package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.filehandler.FileStorage;
import duke.task.Task;
import duke.task.TodoTask;
import duke.userinterface.UserInterface.MessageDisplay;

class FindCommandTest {

    private List<Task> taskList;
    private ByteArrayOutputStream outputStream;

    private MessageDisplay display;
    private FileStorage fileStorage;

    /**
     * Set up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {
        taskList = new ArrayList<>();
        outputStream = new ByteArrayOutputStream();
        display = new MessageDisplay();
        fileStorage = new FileStorage();
    }

    /**
     * Clear the task list after each test case.
     */
    @AfterEach
    public void tearDown() {
        taskList.clear();
    }

    @Test
    void printListWithKeyword_emptyTaskList_shouldPrintNoTasksMessage() {
        System.setOut(new PrintStream(outputStream));
        FindCommand findCommand = new FindCommand("keyword");
        findCommand.execute(fileStorage, display, taskList);
        String expectedOutput =
            "There's nothing in your list" + System.lineSeparator() + MessageDisplay.LINE_BREAK
                + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
        System.setOut(System.out);
    }

    @Test
    void printListWithKeyword_matchingTasks_shouldPrintMatchingTasks() {
        System.setOut(new PrintStream(outputStream));
        FindCommand findCommand = new FindCommand("Task");
        taskList.add(new TodoTask("TaskOne"));
        taskList.add(new TodoTask("TaskTwo"));
        taskList.add(new TodoTask("AnotherTask"));
        findCommand.printListWithKeyword(taskList);
        String expectedOutput =
            "Here are the matching tasks in your list:" + System.lineSeparator()
                + MessageDisplay.LINE_BREAK + System.lineSeparator()
                + "1.[T][ ] TaskOne" + System.lineSeparator()
                + "2.[T][ ] TaskTwo" + System.lineSeparator()
                + "3.[T][ ] AnotherTask" + System.lineSeparator()
                + MessageDisplay.LINE_BREAK + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
        System.setOut(System.out);
    }

}
