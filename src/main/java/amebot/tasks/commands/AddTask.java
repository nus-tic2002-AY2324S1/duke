package amebot.tasks.commands;

import amebot.tasks.Task;
import amebot.tasks.ToDo;
import amebot.tasks.Event;
import amebot.tasks.Deadline;

public class AddTask extends Command {
    public static void add(String desc, String date) {
        try {
            int size = date.length();
            Task item;

            if (date.contains(FROM_REGEX)) {
                int index = date.indexOf(TO_REGEX);

                String from = date.substring(0, 6).replace('/', '(');
                String fromTime = date.substring(6, index);
                String fromDate = from + ":" + fromTime;

                String to = date.substring(index + 1, index + 4).replace('/', ' ');
                String toTime = date.substring(index + 4, size);
                String toDate = to + ":" + toTime + ")";

                item = new Event(desc, fromDate, toDate);
            } else if (date.contains(DUE_REGEX)) {
                String due = date.substring(0, 5).replace('/', '(');
                String dueDate = date.substring(5);
                date = due + ":" + dueDate + ")";

                item = new Deadline(desc, date);
            } else {
                item = new ToDo(desc);
            }

            tasks.add(item);

            Command.printOutput(item);
        } catch (StringIndexOutOfBoundsException | NullPointerException err) {
            System.out.print("\n");
        }
    }
}
