import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

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
        tasks = new TaskList(actions);
        storage.convertToTxtFile(actions);

        Scanner scanner = new Scanner(System.in);
        ui.printHeader();
        String input = scanner.nextLine();
        String trimInput = input.trim().toLowerCase();
        //read string and execute functions accordingly.
        while (!input.trim().equalsIgnoreCase("bye")){
            //added function that handles whitespace as well
//            if (input.trim().isEmpty()){
//                continue;
//            }
            if (input.trim().toLowerCase().startsWith("mark")){
                int index = tasks.getNumber(input);
                actions.get(index-1).setIsDone(true);
                ui.markDone();
                //print "[X] return book"
                actions.get(index-1).printTask();

            }else if (input.trim().toLowerCase().startsWith("unmark")){
                int index = tasks.getNumber(input);
                actions.get(index-1).setIsDone(false);
                ui.markNotDone();
                //print "[ ] return book"
                actions.get(index-1).printTask();
                ui.printLine();
            }else if (input.trim().toLowerCase().startsWith("delete")){
                actions = tasks.removeTasks(input, actions);
            }else if (!input.trim().equalsIgnoreCase("list")) {
                actions = tasks.addTasks(input, actions);
            }else{
                tasks.printTaskList(actions);
            }
            storage.convertToTxtFile(actions);
            input = scanner.nextLine();

        }
        ui.printBye();
    }

}
