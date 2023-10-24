package duke.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    private final String cmd = "todo";
    private final String desc = "bake a cake";

    private String myCode = "";
    private String myString = "";

    @Test
    public void todoMark(){
        Task todo = new Todo(true, desc);
        myCode = cmd + " -m-" + desc;
        myString = "[T][X] " + desc;
        assertAll("returnString",
                ()->assertEquals(myCode, todo.toCode()),
                ()->assertEquals(myString, todo.toString())
        );
    }

    @Test
    public void todoUnmark(){
        Task todo = new Todo(false, desc);
        myCode = cmd + " " + desc;
        myString = "[T][ ] " + desc;
        assertAll("returnString",
                ()->assertEquals(myCode, todo.toCode()),
                ()->assertEquals(myString, todo.toString())
        );
    }

}
