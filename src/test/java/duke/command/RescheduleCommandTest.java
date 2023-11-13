package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.dukeexceptions.DukeException;
import duke.filehandler.FileStorage;
import duke.task.DeadlineTask;
import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;

class RescheduleCommandTest {
    private List<Task> taskList;
    private MessageDisplay display;

    private FileStorage fileStorage;

    @BeforeEach
    public void setUp() {
        taskList = new ArrayList<>();
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
    void execute_validItemIndex_shouldRescheduleTask() throws DukeException {
        LocalDateTime initialDateTime = LocalDateTime.now();
        taskList.add(new DeadlineTask("Task 1", initialDateTime));
        int itemIndex = 0;
        LocalDateTime revisedDateTime = initialDateTime.plusDays(1);
        RescheduleCommand rescheduleCommand = new RescheduleCommand(itemIndex, revisedDateTime);
        rescheduleCommand.execute(fileStorage, display, taskList);
        assertEquals(revisedDateTime, taskList.get(itemIndex).getTaskEndDate());
    }

}
