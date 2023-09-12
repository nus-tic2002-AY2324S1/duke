package command;

import task.Task;

import java.util.List;

public class UnmarkCommand extends CrabyMessage {
    public void handleUnmarkCommand(String input, List<Task> tasks) {
        try {
            int tmp = input.indexOf("k");
            String checkMark = input.substring(tmp + 1).trim();
            int b = (Integer.parseInt(checkMark)) - 1;
            if (b >= tasks.size() || b < 0) {
                System.out.print("   Oops, something wrong! Your list only have 1 to ");
                System.out.println(tasks.size() + "tasks.");
                System.out.println("   Please try again!\n" + line);
                return;
            }
            tasks.get(b).setDone(false);
            System.out.println("   OK, I've marked this task as ☉⌓☉ NOT DONE yet:");
            System.out.println("   ╰┈➤ " + tasks.get(b) + "\n" + line);
        } catch (NumberFormatException e) {
            System.out.println("   Oops!!! Looks like you used the wrong format.");
            System.out.println("   Try with: unmark [integer] e.g: unmark 1\n" + line);
        }
    }
}
