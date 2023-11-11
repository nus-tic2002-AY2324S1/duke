package amebot.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    ArrayList<Task> tasks = new ArrayList<>();

    @BeforeEach
    public void addTask_Event() {
        Task task1 = new Event(false, "test event task 1~", " (FROM: 21 NOV 2023 (TUE) 12:12PM", " TO: 22 NOV 2023 (WED) 3:33PM)");
        assertEquals("[EVENT] [ ] TEST EVENT TASK 1~ (FROM: 21 NOV 2023 (TUE) 12:12PM TO: 22 NOV 2023 (WED) 3:33PM)", task1.getTask());
        this.tasks.add(task1);

        Task task2 = new Event(false, "test event task 2~", " (FROM: 21 NOV 2023 (TUE)", " TO: 22 NOV 2023 (WED))");
        assertEquals("[EVENT] [ ] TEST EVENT TASK 2~ (FROM: 21 NOV 2023 (TUE) TO: 22 NOV 2023 (WED))", task2.getTask());
        this.tasks.add(task2);
    }

    @Test
    public void markTask_Event() {
        Task task1 = this.tasks.get(0);
        task1.setStatusAsMarked();
        assertEquals("[EVENT] [✓] TEST EVENT TASK 1~ (FROM: 21 NOV 2023 (TUE) 12:12PM TO: 22 NOV 2023 (WED) 3:33PM)", task1.getTask());

        Task task2 = this.tasks.get(1);
        task2.setStatusAsMarked();
        assertEquals("[EVENT] [✓] TEST EVENT TASK 2~ (FROM: 21 NOV 2023 (TUE) TO: 22 NOV 2023 (WED))", task2.getTask());
    }

    @Test
    public void unmarkTask_Event() {
        Task task1 = this.tasks.get(0);
        task1.setStatusAsUnmarked();
        assertEquals("[EVENT] [ ] TEST EVENT TASK 1~ (FROM: 21 NOV 2023 (TUE) 12:12PM TO: 22 NOV 2023 (WED) 3:33PM)", task1.getTask());

        Task task2 = this.tasks.get(1);
        task2.setStatusAsUnmarked();
        assertEquals("[EVENT] [ ] TEST EVENT TASK 2~ (FROM: 21 NOV 2023 (TUE) TO: 22 NOV 2023 (WED))", task2.getTask());
    }
}
