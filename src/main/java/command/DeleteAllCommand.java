package command;

import task.Task;

import java.util.List;

public class DeleteAllCommand extends CrabyMessage implements CommandInterface {
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        System.out.println(blank + "✂ Ok, I clean up all your tasks");
        System.out.println(blank + "╰┈➤ Let's start a new checklist");
        tasks.clear();
        System.out.println(line);
    }
}