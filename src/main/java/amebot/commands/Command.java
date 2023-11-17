package amebot.commands;

import amebot.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a command that executes the user input.
 */

public abstract class Command {
    protected static ArrayList<Task> tasks = new ArrayList<>();

    public abstract ArrayList<String> executeCommand();

    /**
     * Returns list of tasks.
     *
     * @return List of tasks.
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }
}
