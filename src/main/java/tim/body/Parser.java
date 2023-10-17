package tim.body;
import tim.tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
     static void inputParser(ArrayList<Task> list){
        String input = "init" ;
        int index = -1;
        Scanner in = new Scanner(System.in);
        while(!input.equalsIgnoreCase("bye")) {
            input = in.nextLine().replaceAll("\\s+", " ");
            input = input.trim();
            String[] token = input.split(" ", 2);
            String taskName = "";
            String mode = token[0].toLowerCase();
            System.out.println("|| " + mode + " ||");
            Logic.executeAction(mode,list, index, token, taskName);
        }
    }
}
