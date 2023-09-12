package command;

import task.Task;

import java.util.List;

public class DeleteCommand extends CrabyMessage {
    public void handleDeleteCommand(String input, List<Task> tasks) {
        try {
            int tmp = input.indexOf("t");
            String checkDelete = input.substring(tmp + 2).trim();
            int checkNum = (Integer.parseInt(checkDelete)) - 1;
            if (checkNum >= tasks.size() || checkNum < 0) {
                System.out.print("   Oops!!! Something wrong! Your list only have 1 ➞ ");
                System.out.println(tasks.size() + " tasks.");
                System.out.println("   Please try again!\n" + line);
                return;
            }
            System.out.println("   ✂ Removed:");
            System.out.println("   ╰┈➤ " + tasks.get(checkNum) + " in your list");
            tasks.remove(checkNum);
            System.out.println("   Now you have: " + tasks.size() + " tasks your the list \uD83D\uDDCE.\n" + line);
        } catch (NumberFormatException nfe) {
            System.out.println("   Oops!!! Looks like you used the wrong format.");
            System.out.println("   Try with: delete [integer] e.g: unmark 1\n" + line);
        }
    }
}
