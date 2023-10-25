package com.tina;

import com.tina.command.Command;
import com.tina.command.CommandEnum;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        String logo =   "████████ ██ ███    ██  █████  \n" +
                        "   ██    ██ ████   ██ ██   ██ \n" +
                        "   ██    ██ ██ ██  ██ ███████ \n" +
                        "   ██    ██ ██  ██ ██ ██   ██ \n" +
                        "   ██    ██ ██   ████ ██   ██";

        System.out.println(logo);
        System.out.println("Hello! I'm TINA. \nHow can I help you?\n");
        printCommandList();
        printDividerLine();
    }

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
    }

    public void printTask(Task task, boolean isDelete, int size) {
        String str = isDelete ? "removed" : "added";
        printLine("Got it. I've " + str + " this Tina.task:");
        printLine("  " + task.toString());
        printLine("Now you have " + size + " tasks in the list.");
    }

    public void printMark(Task task, boolean isMark) {
        String str = isMark ? "" : "not ";
        printLine("Nice! I've marked this Tina.task as " + str +"done:");
        printLine("[" + task.isDone() +"]" + task.getName());
    }

    public void printDividerLine() {
        printLine("**************************************************");
    }

    public void printLine(String str) {
        System.out.print("  ");
        System.out.println(str);
    }

    public void printError() {
        printLine("OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDividerLine();
        printCommandList();
    }

    public void printBye() {
        printLine("Bye. Hope to see you again.");
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void printTaskList(TaskList taskList) {
        int count = 1;
        for (Task task : taskList.getTaskList()) {
            printLine(count + "." + task.toString());
            count++;
        }
    }
}
