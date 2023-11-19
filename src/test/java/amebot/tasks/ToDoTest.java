package amebot.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    Task task;

    @BeforeEach
    public void addTask_ToDo() {
        this.task = new ToDo(false, "test todo task~");
        assertEquals("[TODO] [ ] TEST TODO TASK~", this.task.getTask());
    }

    @Test
    public void markTask_ToDo() {
        this.task.setStatusAsMarked();
        assertEquals("[TODO] [✓] TEST TODO TASK~", this.task.getTask());
    }

    @Test
    public void unmarkTask_ToDo() {
        this.task.setStatusAsUnmarked();
        assertEquals("[TODO] [ ] TEST TODO TASK~", this.task.getTask());
    }
}
