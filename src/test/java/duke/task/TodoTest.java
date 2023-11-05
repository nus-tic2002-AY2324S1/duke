package duke.task;

import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testGetTaskType(){
        Todo todo = new Todo("Test Todo");
        assertEquals(TaskType.T, todo.getTaskType());
    }

    @Test
    public void testStatusIconUndoneTodo(){
        Todo todo = new Todo("Test Todo");
        assertEquals("[T][ ]", todo.getStatusIcon());
    }

    @Test
    public void testStatusIconForDoneTodo(){
        Todo todo = new Todo("Test todo");
        todo.markAsDone();
        assertEquals("[T][X]", todo.getStatusIcon());
    }

}
