import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Duke Scheduling Robot venni </h1>
 *  This program allow user to put in their task and turn it into a list.
 * The list will be stored in a file called duke.txt as well.
 * <p>
 * User can mark/unmark the tasks as "done",add and delete task.
 * There are three types of Task: Event, Deadline, Todo
 *
 * @author  WanYing Li
 * @version 1.0
 * @since   2023-08-18
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public static ArrayList<Task> actions;
    public static int inputCount =0;


    public static void main(String[] args) throws IOException {
        actions = new ArrayList<>();

        //Adding testing items in Task
        //what if the situation without todo
        Task task1 = new Task("todo read book", false);
        Task task2 = new Task("todo return book", false);
        Task task3 = new Task("todo buy bread", false);

        actions.add(task1);
        actions.add(task2);
        actions.add(task3);
        inputCount +=3;
        tasks = new TaskList();
        storage.convertToTxtFile(actions);

        Scanner scanner = new Scanner(System.in);
        ui.printHeader();
        String input = scanner.nextLine();
        String trimInput = input.trim().toLowerCase();
        //read string and execute functions accordingly.
        while (!trimInput.equalsIgnoreCase("bye")){
            if (trimInput.startsWith("mark")){
                int index = tasks.getNumber(input);
                if (index> -1 && index <= actions.size()) {
                    actions.get(index - 1).setIsDone(true);
                    ui.markDone();//--> print "[X] return book"
                    actions.get(index-1).printTask();
                    ui.printLine();
                }else{
                    System.out.println("Task you wish to unmark doesn't exist!");
                }
            }else if (trimInput.startsWith("unmark")){
                int index = tasks.getNumber(input);
                if (index> -1 && index <= actions.size()) {
                    actions.get(index - 1).setIsDone(false);
                    ui.markNotDone();//--> print "[ ] return book"
                    actions.get(index-1).printTask();
                    ui.printLine();
                }else{
                    System.out.println("Task you wish to unmark doesn't exist!");
                }
            }else if (trimInput.startsWith("delete")){
                actions = tasks.removeTasks(input, actions);
            }else if (!trimInput.equalsIgnoreCase("list")) {
                actions = tasks.addTasks(input, actions);
            }else{
                tasks.printTaskList(actions);
            }
            storage.convertToTxtFile(actions);
            input = scanner.nextLine();
            trimInput = input.trim().toLowerCase();

        }
        ui.printBye();
    }

}
