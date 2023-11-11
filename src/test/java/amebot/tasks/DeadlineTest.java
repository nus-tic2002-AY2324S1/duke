package amebot.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    ArrayList<Task> tasks = new ArrayList<>();

    @BeforeEach
    public void addTask_Deadline() {
        Task task1 = new Deadline(false, "test deadline task 1~", " (DUE: 20 NOV 2023 (MON) 6:23PM)");
        assertEquals("[DEADLINE] [ ] TEST DEADLINE TASK 1~ (DUE: 20 NOV 2023 (MON) 6:23PM)", task1.getTask());
        this.tasks.add(task1);

        Task task2 = new Deadline(false, "test deadline task 2~", " (DUE: 20 NOV 2023 (MON))");
        assertEquals("[DEADLINE] [ ] TEST DEADLINE TASK 2~ (DUE: 20 NOV 2023 (MON))", task2.getTask());
        this.tasks.add(task2);
    }

    @Test
    public void markTask_Deadline() {
        Task task1 = this.tasks.get(0);
        task1.setStatusAsMarked();
        assertEquals("[DEADLINE] [✓] TEST DEADLINE TASK 1~ (DUE: 20 NOV 2023 (MON) 6:23PM)", task1.getTask());

        Task task2 = this.tasks.get(1);
        task2.setStatusAsMarked();
        assertEquals("[DEADLINE] [✓] TEST DEADLINE TASK 2~ (DUE: 20 NOV 2023 (MON))", task2.getTask());
    }

    @Test
    public void unmarkTask_Deadline() {
        Task task1 = this.tasks.get(0);
        task1.setStatusAsUnmarked();
        assertEquals("[DEADLINE] [ ] TEST DEADLINE TASK 1~ (DUE: 20 NOV 2023 (MON) 6:23PM)", task1.getTask());

        Task task2 = this.tasks.get(1);
        task2.setStatusAsUnmarked();
        assertEquals("[DEADLINE] [ ] TEST DEADLINE TASK 2~ (DUE: 20 NOV 2023 (MON))", task2.getTask());
    }
}
