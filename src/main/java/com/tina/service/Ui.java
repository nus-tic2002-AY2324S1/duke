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
    public void showWelcome() {
        String logo =   "                      \n" +
                        "  ___________   _____ \n" +
                        " /_  __/  _/ | / /   |\n" +
                        "  / /  / //  |/ / /| |\n" +
                        " / / _/ // /|  / ___ |\n" +
                        "/_/ /___/_/ |_/_/  |_|\n"+
                        "                      ";
        System.out.println(logo);
        System.out.println("Hello! I'm TINA. \nHow can I help you?\n");
        System.out.println("type help to get command list");
        printDividerLine();
    }

    /**
     * Print command list.
     */
    public void printCommandList() {
        System.out.println("Command List:");
        System.out.println("bye");
        System.out.println("list");
        System.out.println("todo [task name]");
        System.out.println("deadline [task name] /by [date]");
        System.out.println("event [task name] /from [date] /to [date]");
        System.out.println("mark [task number]");
        System.out.println("unmark [task number]");
        System.out.println("delete [task number]");
        System.out.println("schedule [date]");
        System.out.println("archive [file name].txt");
    }

    /**
     * Print task according to the given task type.
     *
     * @param task     the task
     * @param isDelete the flag indicates the task is added or removed.
     * @param size     the size
     */
    public void printTask(Task task, boolean isDelete, int size) {
        String str = isDelete ? "removed" : "added";
        printLine("Got it. I've " + str + " this Tina.task:");
        printLine("  " + task.toString());
        printLine("Now you have " + size + " tasks in the list.");
    }

    /**
     * Print confirmation message for mark/unmark command.
     *
     * @param task   the task
     * @param isMark a flag to indicate the task is mark as done or not done
     */
    public void printMark(Task task, boolean isMark) {
        String str = isMark ? "" : "not ";
        printLine("Nice! I've marked this Tina.task as " + str +"done:");
        printLine("[" + task.isDone() +"]" + task.getTaskName());
    }

    /**
     * Print divider line.
     */
    public void printDividerLine() {
        printLine("**************************************************");
    }

    /**
     * Print line.
     *
     * @param str the message to be printed
     */
    public void printLine(String str) {
        System.out.print("  ");
        System.out.println(str);
    }

    /**
     * Print error when unknown command is received.
     */
    public void printError() {
        printLine("OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDividerLine();
        printCommandList();
    }

    /**
     * Print loading error.
     */
    public void printLoadingError() {
        printLine("Failed to load task list due to missing or invalid file.");
    }

    /**
     * Print goodbye message when exiting program.
     */
    public void printBye() {
        printLine("Bye. Hope to see you again.");
    }

    /**
     * Read input string from user.
     *
     * @return the string
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Print task list.
     *
     * @param taskList the task list
     */
    public void printTaskList(TaskList taskList) {
        int count = 1;
        for (Task task : taskList.getTaskList()) {
            printLine(count + "." + task.toString());
            count++;
        }
    }
}
