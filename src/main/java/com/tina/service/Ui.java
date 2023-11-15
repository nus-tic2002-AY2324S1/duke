package com.tina.service;

import com.tina.task.Task;
import com.tina.task.TaskList;

/**
 * Represents an Ui class.
 * Prints output based on the given command.
 * Prints error message if exceptions are captured.
 */
public class Ui {
    /**
     * Shows welcome message.
     *
     * @return the message.
     */
    public static String showWelcome() {
        return "Hello! I'm TINA. \nHow can I help you?\n" +
                "type help to get command list";
    }

    /**
     * Prints command list.
     *
     * @return the command list.
     */
    public String printCommandList() {
        return "Command List:\n" +
                "bye\n" +
                "list\n" +
                "todo [task name]\n" +
                "deadline [task name] /by [date]\n" +
                "event [task name] /from [date] /to [date]\n" +
                "mark [task number]\n" +
                "unmark [task number]\n" +
                "delete [task number]\n" +
                "schedule [date]\n" +
                "archive [file name].txt";
    }

    /**
     * Prints task according to the given task type.
     *
     * @param task     the task.
     * @param isDelete the flag indicates the task is added or removed.
     * @param size     the size.
     * @return the confirmation message.
     */
    public String printTask(Task task, boolean isDelete, int size) {
        String str = isDelete ? "removed" : "added";
        return "Got it. I've " + str + " this Tina.task:\n" +
                "  " + task.toString() + "\n" +
                "Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints confirmation message for mark/unmark command.
     *
     * @param task   the task.
     * @param isMark a flag to indicate the task is mark as done or not done.
     * @return the confirmation message.
     */
    public String printMark(Task task, boolean isMark) {
        String str = isMark ? "" : "not ";
        return "Nice! I've marked this Tina.task as " + str + "done:\n" +
                "[" + task.isDone() + "]" + task.getTaskName();
    }

    /**
     * Prints error when unknown command is received.
     *
     * @return the error message.
     */
    public String printError() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(\n" + printCommandList();
    }

    /**
     * Prints IO error message.
     *
     * @return the error message.
     */
    public static String printIOError() {
        return "Failed due to IO error.";
    }

    /**
     * Prints loading error message.
     *
     * @return the error message.
     */
    public static String printLoadingError() {
        return "Failed to load task list due to missing or invalid file.";
    }

    /**
     * Prints goodbye message when exiting program.
     *
     * @return the message.
     */
    public String printBye() {
        return "Bye. Hope to see you again.";
    }

    /**
     * Prints task list.
     *
     * @param taskList the task list.
     * @return the task list.
     */
    public String printTaskList(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Task task : taskList.getTaskList()) {
            sb.append(count).append(".").append(task.toString()).append("\n");
            count++;
        }
        return sb.toString();
    }
}
