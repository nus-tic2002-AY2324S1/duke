package duke.task;

import duke.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getDateEqualTasks_availableDate_foundDate() {
        TaskList tasks = new TaskList(TestUtil.getTestTasks());
        LocalDateTime date = LocalDateTime.of(2023, 12, 2, 0, 0);
        ArrayList<Task> foundTasks = tasks.getTasksByDate(date);
        assertEquals(getSuccessTask().toString(), foundTasks.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    void getAbbreviation_outOfRange_returnX(int i) {
        TaskList taskList = new TaskList(TestUtil.getTestTasks());
        assertEquals(Character.toString('X'), Character.toString(taskList.getAbbreviation(i)));
    }

    @Test
    void getAbbreviation_1_success() {
        TaskList taskList = new TaskList(TestUtil.getTestTasks());
        assertEquals(Character.toString('T'), Character.toString(taskList.getAbbreviation(1)));
    }
    @Test
    void getAbbreviation_2_success() {
        TaskList taskList = new TaskList(TestUtil.getTestTasks());
        assertEquals(Character.toString('D'), Character.toString(taskList.getAbbreviation(2)));
    }

    @Test
    public void getDateEqualTasks_notAvailableDate_noDateFound() {
        ArrayList<Task> emptyTasks = new ArrayList<>();
        TaskList tasks = new TaskList(TestUtil.getTestTasks());
        LocalDateTime date = LocalDateTime.of(2023, 6, 2, 0, 0);
        ArrayList<Task> foundTasks = tasks.getTasksByDate(date);
        assertEquals(emptyTasks.toString(), foundTasks.toString());
    }

    private ArrayList<Task> getSuccessTask() {
        ArrayList<Task> result = new ArrayList<>();
        TaskList tasks = new TaskList(TestUtil.getTestTasks());
        result.add(tasks.get(1));
        result.add(tasks.get(2));
        return result;
    }
}
