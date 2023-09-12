package command;

import task.Task;

import java.util.List;

public class ListCommand extends CrabyMessage {
    public void handleListCommand(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("    ╭─────────────────────────────────╮");
            System.out.println("       ░░░░ Your list is empty! ░░░░");
            System.out.println("    ꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦\n" + line);
            return;
        }
        int i = 0;
        System.out.println("    ╭─────────────────────────────────╮");
        System.out.println("     Here are the tasks in your list:");
        for (Task s : tasks) {
            i++;
            if (i <= 9) {
                System.out.println("   " + i + ".  " + s);
            } else {
                System.out.println("   " + i + ". " + s);
            }
        }
        System.out.println("    ꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦");
        System.out.println(line);
    }
}
