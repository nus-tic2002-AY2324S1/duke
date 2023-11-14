package com.tina.service;

import com.tina.command.DeleteCommand;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.util.Scanner;

/**
 * The Ui class.
 * Display the user interface, and it's the only interactive channel with user.
 * Read input from user.
 * Print output based on the given command.
 * Print error message if exceptions are captured.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Show welcome message and give command list for help.
     */
    public static String showWelcome() {
        return "Hello! I'm TINA. \nHow can I help you?\n" +
                "type help to get command list";
    }

    /**
     * Print command list.
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
     * Print task according to the given task type.
     *
     * @param task     the task
     * @param isDelete the flag indicates the task is added or removed.
     * @param size     the size
     */
    public String printTask(Task task, boolean isDelete, int size) {
        String str = isDelete ? "removed" : "added";
        return "Got it. I've " + str + " this Tina.task:\n" +
                "  " + task.toString() + "\n" +
                "Now you have " + size + " tasks in the list.";
    }

    /**
     * Print confirmation message for mark/unmark command.
     *
     * @param task   the task
     * @param isMark a flag to indicate the task is mark as done or not done
     */
    public String printMark(Task task, boolean isMark) {
        String str = isMark ? "" : "not ";
        return "Nice! I've marked this Tina.task as " + str + "done:\n" +
                "[" + task.isDone() + "]" + task.getTaskName();
    }

    /**
     * Print line.
     *
     * @param str the message to be printed
     */
    public String printLine(String str) {
        return "  " + str;
    }

    /**
     * Print error when unknown command is received.
     */
    public String printError() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(\n" + printCommandList();
    }

    /**
     * Print loading error.
     */
    public String printLoadingError() {
        return "Failed to load task list due to missing or invalid file.";
    }

    /**
     * Print goodbye message when exiting program.
     */
    public String printBye() {
        return "Bye. Hope to see you again.";
    }

    /**
     * Print task list.
     *
     * @param taskList the task list
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
