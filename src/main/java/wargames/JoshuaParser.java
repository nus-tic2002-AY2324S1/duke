package wargames;

import exceptions.InvalidCommandException;
import task.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoshuaParser {

    public JoshuaParser() {

    }
    public boolean isByeCommand(String command) {
        return command.equals("bye");
    }

    public boolean isListCommand(String command) {
        return command.equals("list");
    }

    public boolean isMarkCommand(String command) {
        return command.startsWith("mark ");
    }

    public boolean isUnmarkCommand(String command) {
        return command.startsWith("unmark ");
    }

    public boolean isToDoCommand(String command) {
        return command.startsWith("todo ");
    }

    public boolean isDeadlineCommand(String command) {
        return command.startsWith("deadline ");
    }

    public boolean isEventCommand(String command) {
        return command.startsWith("event ");
    }

    public boolean isDeleteCommand(String command) { return command.startsWith("delete "); }

    public int parseTaskNum(String command) {
        List<String> cmdArrayList = stringToArrayList(command);

        if (cmdArrayList.size() == 2) {
            try {
                String taskNumStr = cmdArrayList.get(1);
                return Integer.parseInt(taskNumStr);
            }
            catch (NumberFormatException e) {
                return -1;
            }
        }
        else {
            return -1;
        }
    }

    public Task createToDo(String command) throws InvalidCommandException {
        List<String> inputArrayList = stringToArrayList(command);
        List<String> descArrayList = inputArrayList.subList(1, inputArrayList.size());

        if (descArrayList.isEmpty()) {
            throw new InvalidCommandException("Enter a description for your todo.");
        }

        String desc = String.join(" ", descArrayList);

        return TaskFactory.createTask("todo", false, desc, null, null);
    }

    public Task createDeadline(String command) throws InvalidCommandException {
        // Get "desc" and "by" from input
        List<String> inputArrayList = stringToArrayList(command);
        int byMarker = inputArrayList.indexOf("/by");

        if (inputArrayList.size() == 1 || byMarker == 1) {
            throw new InvalidCommandException("Enter a description for your deadline");
        }
        else if (byMarker < 0 || byMarker+1 == inputArrayList.size()) {
            throw new InvalidCommandException("Enter the /by parameter for your deadline.");
        }

        List<String> descArrayList = inputArrayList.subList(1, byMarker);
        String desc = String.join(" ", descArrayList);

        List<String> byArrayList = inputArrayList.subList(byMarker+1, inputArrayList.size());
        String by = String.join(" ", byArrayList);

        return TaskFactory.createTask("deadline", false, desc, by, null);
    }

    public Task createEvent(String command) throws InvalidCommandException {
        // TODO: currently only supports /from /to in that order.
        // Get "from" and "to" from the input
        List<String> inputArrayList = stringToArrayList(command);
        int fromMarker = inputArrayList.indexOf("/from");
        int toMarker = inputArrayList.indexOf("/to");

        if (inputArrayList.size() == 1 || fromMarker == 1) {
            throw new InvalidCommandException("Enter a description for your event.");
        }
        else if (fromMarker < 0 || fromMarker+1 == toMarker) {
            throw new InvalidCommandException("Enter the /from parameter for your event.");
        }
        else if (toMarker < 0 || toMarker+1 == inputArrayList.size()) {
            throw new InvalidCommandException("Enter the /to parameter for your event.");
        }

        List<String> descArrayList = inputArrayList.subList(1, fromMarker);
        String desc = String.join(" ", descArrayList);

        List<String> fromArrayList = inputArrayList.subList(fromMarker + 1, toMarker);
        String from = String.join(" ", fromArrayList);

        List<String> toArrayList = inputArrayList.subList(toMarker + 1, inputArrayList.size());
        String to = String.join(" ", toArrayList);

        return TaskFactory.createTask("event", false, desc, from, to);
    }

    private static ArrayList<String> stringToArrayList(String str) {
        String[] strArray = str.split("\\s+"); // Split on any number of whitespaces
        List<String> strList = new ArrayList<>(Arrays.asList(strArray));
        return new ArrayList<>(strList);
    }
}
