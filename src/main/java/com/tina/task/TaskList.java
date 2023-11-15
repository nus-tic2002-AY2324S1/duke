package com.tina.task;

import java.util.ArrayList;

/**
 * Represents a Task list.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Instantiates a new Task list with given task list.
     *
     * @param taskList the task list.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Instantiates a new Task list with blank task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets task list.
     *
     * @return the task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Add task into task list.
     *
     * @param task the task.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Remove task from task list.
     *
     * @param task the task.
     */
    public void remove(Task task) {
        taskList.remove(task);
    }

    /**
     * Returns the size of the task list.
     *
     * @return the size.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Clears the task list.
     */
    public void clear() {
        taskList.clear();
    }

}
