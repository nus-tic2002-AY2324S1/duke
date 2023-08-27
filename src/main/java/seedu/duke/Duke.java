package seedu.duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.duke.logger.WonkyLogger;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;

public class Duke {
    public static void main(String[] args) {
        WonkyLogger.startUp();
        String line;
        Scanner in = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while (!(line = in.nextLine()).equals("bye")) {
            if (line.equals("list")) {
                WonkyLogger.postList();
                for (Task task : tasks) {
                    WonkyLogger.task(task.getStatusMsg());
                }
            } else {
                Deadline newTask = new Deadline(" : " + line, "27/08/2023");
                WonkyLogger.preList(line);
                tasks.add(newTask);
            }
        }
        WonkyLogger.bye();
        in.close();
    }
}
