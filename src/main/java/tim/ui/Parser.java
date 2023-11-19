package tim.ui;
import tim.util.TaskList;
import java.util.Scanner;

/**
 * Represents as a Parser object.
 * This class is responsible for parsing the user input
 * before passing it to the Logic class to execute the action.
 */
public class Parser {

    /**
     * Parses the user input and passes it to the Logic class to execute the action.
     *
     * @param tasks List of tasks.
     */
     public static void inputParser(TaskList tasks){
        String input = "init" ;
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            input = in.nextLine().replaceAll("\\s+", " ");
            input = input.trim();
            String[] token = input.split(" ", 2);
            String mode = token[0].toLowerCase();
            System.out.println("|| " + mode + " ||");
            Logic.executeAction(mode,tasks, token);
        }

        in.close();
    }
}
