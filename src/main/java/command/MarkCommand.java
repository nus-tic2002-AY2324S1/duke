package command;

import task.Task;

import java.util.List;

public class MarkCommand extends CrabyMessage implements CommandInterface{

    @Override
    public void handleCommand(String input, List<Task> tasks) {
        try {
            int tmp = input.indexOf("k");
            String checkMark = input.substring(tmp + 1).trim();
            int checkNum = (Integer.parseInt(checkMark)) - 1;
            if (checkNum >= tasks.size() || checkNum < 0) {
                System.out.print(blank + "Oops, something wrong! Your list only have 1 to ");
                System.out.println(tasks.size() + " tasks.");
                System.out.println(blank + "Please try again!");
                System.out.println(line);
                return;
            }
            tasks.get(checkNum).setDone(true);
            System.out.println(blank + "Nice! I've marked this task as DONE ツ:");
            System.out.println(blank + "╰┈➤ " + tasks.get(checkNum) );
            System.out.println(line);
        } catch (NumberFormatException e) {
            System.out.println(blank + "Oops!!! Looks like you used the wrong format.");
            System.out.println(blank + "Try with: mark [integer] e.g: mark 1");
            System.out.println(line);

        }
    }
}
