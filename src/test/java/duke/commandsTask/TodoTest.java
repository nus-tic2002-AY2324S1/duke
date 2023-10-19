package duke.commandsTask;

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
        myCode = cmd + " " + desc;
        myString = "[T][ ] " + desc;
        assertAll("returnString",
                ()->assertEquals(cmd + " " + desc, todo.toCode()),
                ()->assertEquals(cmd + " " + desc, todo.toString())
        );
    }

}
