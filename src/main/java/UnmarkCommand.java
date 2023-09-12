import java.util.List;

public class UnmarkCommand extends CrabyMessage{
    public void handleUnmarkCommand(String input, List<Task> tasks) {
        try {
            int tmp = input.indexOf("k");
            String checkMark = input.substring(tmp + 1).trim();
            int b = (Integer.parseInt(checkMark)) - 1;
            if (b >= tasks.size() || b < 0) {
                System.out.println("   Oops, something wrong! Your list only have 1 to " + tasks.size() + "tasks. \n" + "   Please try again!\n" + line);
                return;
            }
            tasks.get(b).setDone(false);
            System.out.println("   OK, I've marked this task as ☉⌓☉ NOT DONE yet:" + "\n" + "   ╰┈➤ " + tasks.get(b) + "\n" + line);
        } catch (NumberFormatException e) {
            System.out.println("   '" + input.split(" ")[1] + "' is invalid number. Please try again!\n" + line);
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("   Oops!!! Looks like you used the wrong format.\n   Try format: unmark [integer] e.g: unmark 1\n" + line);
        }
    }
}
