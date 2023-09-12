package command;

import task.Task;

import java.util.List;

public class ListCommand extends CrabyMessage{
    public void handleListCommand(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("   Your list is empty!\n" + line);
            return;
        }
        int i = 0;
        System.out.println("    ╭─────────────────────────────────╮");
        System.out.println("     Here are the tasks in your list:");
        for (Task s : tasks) {
            i++;
            System.out.println("   " + i + ". " + s);
        }
        System.out.println("    ꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦");
        System.out.println(line);

    }
}
