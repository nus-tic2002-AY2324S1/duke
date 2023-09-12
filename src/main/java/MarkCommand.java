import java.util.List;

public class MarkCommand extends CrabyMessage {
    public void handleMarkCommand(String input, List<Task> tasks) {
        try {
            int tmp = input.indexOf("k");
            String checkMark = input.substring(tmp + 1).trim();
            int a = (Integer.parseInt(checkMark)) - 1;
            if (a >= tasks.size() || a < 0) {
                System.out.println("   Oops, something wrong! Your list only have 1 to " + tasks.size() + " tasks.\n" + "   Please try again!\n" + line);
                return;
            }
            tasks.get(a).setDone(true);
            System.out.println("   Nice! I've marked this task as DONE ツ:" + "\n" + "   ╰┈➤ " + tasks.get(a) + "\n" + line);

        } catch (NumberFormatException e) {
            System.out.println("   '" + input.split(" ")[1] + "' is invalid number. Please try again!\n" + line);
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("   Oops!!! Looks like you used the wrong format.\n   Try format: mark [integer] e.g: mark 1\n" + line);

        }
    }
}
