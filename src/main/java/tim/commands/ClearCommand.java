package tim.commands;

import tim.ui.Display;
import tim.util.TaskList;

public class ClearCommand extends Command {


    @Override
    public void execute(String[] token, TaskList tasks) {
        try {
            clearList(tasks);
        } catch (Exception e) {
            System.out.println("oh no!  ");
            Display.printDash();
        }
    }

    private void clearList(TaskList tasks) {
        System.out.print("List is now empty!");
        for(int i = tasks.size() - 1; i >= 0; i--) {
            tasks.remove(i);
        }
        Display.printDash();
    }
}
