package com.duke.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.duke.task.Deadline;
import com.duke.task.Task;

public class Duke {
    public static void main(String[] args) {
        String logo =
                "__          __         _            ____        _   \n"
                + "\\ \\        / /        | |          |  _ \\      | |  \n"
                + " \\ \\  /\\  / /__  _ __ | | ___   _  | |_) | ___ | |_ \n"
                + "  \\ \\/  \\/ / _ \\| '_ \\| |/ / | | | |  _ < / _ \\| __|\n"
                + "   \\  /\\  / (_) | | | |   <| |_| | | |_) | (_) | |_ \n"
                + "    \\/  \\/ \\___/|_| |_|_|\\_\\\\__, | |____/ \\___/ \\__|\n"
                + "                             __/ |                  \n"
                + "                            |___/                  \n";
        System.out.println("\tWonky: Hello from\n" + logo);
        System.out.println("\tWonky: I'm Wonky the Fairy.");
        System.out.println("\tWonky: What can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while (!(line = in.nextLine()).equals("bye")) {
            if (line.equals("list")) {
                System.out.println("\tWonky: Below are the list of items you have added!");
                for (Task task : tasks) {
                    System.out.println("\t\t" + task.getStatusMsg());
                }
            } else {
                Deadline newTask = new Deadline(tasks.size() + 1 + ": " + line, "27/08/2023");
                System.out.println("\tWonky: I have added " + "[" + line + "] to our list!");
                tasks.add(newTask);
            }
        }
        System.out.println("\tWonky: Bye! Thank you for using Wonky Bot. Hope to see you again soon!");
        in.close();
    }
}
