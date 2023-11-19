package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void test_todo_toString(){
        assertEquals("[T] [ ] read book", new ToDo("read book").toString());
    }

    @Test
    public void test_todo_toStorageString(){
        assertEquals("T | 0 | read book", new ToDo("read book").toStorageString());
    }
}
