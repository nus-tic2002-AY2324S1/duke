package amebot.tasks.commands;

import amebot.tasks.Task;

public class ListTask extends Command {
    public static void list() {
        if (Task.getTaskListSize() == 0) {
            System.out.println("List is empty!");
        } else {
            int itemNum = 1;

            System.out.println("Item(s) in current list:");
            for (Task task : tasks) {
                System.out.print(itemNum + ". ");
                task.printTask();
                itemNum++;
            }
        }
    }
}
