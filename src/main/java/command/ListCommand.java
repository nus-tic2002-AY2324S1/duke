package command;

import task.Task;

import java.util.List;

public class ListCommand extends CrabyMessage implements CommandInterface{
    private static void printStartOfList() {
        System.out.println(blank + " ╭─────────────────────────────────╮");
    }

    private static void printEndOfList() {
        System.out.println(blank + " ꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦");
        System.out.println(line);
    }

    /**
     * This method will print out all the tasks in the list.
     *
     * @param input the input from the user.
     * @param tasks the list of tasks.
     * @return
     */
    @Override
    public short handleCommand(String input, List<Task> tasks) {
        if (tasks.isEmpty()) {
            printStartOfList();
            System.out.println(blank + "    ░░░░ Your list is empty! ░░░░");
            printEndOfList();
            return 0;
        }
        int i = 0;
        printStartOfList();
        System.out.println(blank + "  Here are the tasks in your list:");
        for (Task s : tasks) {
            i++;
            if (i <= 9) {
                System.out.println("   " + i + ".  " + s);
            } else if (i > 99) {
                System.out.println("   " + i + " " + s);

            } else {
                System.out.println("   " + i + ". " + s);
            }
        }
        printEndOfList();
        return 0;
    }
}
