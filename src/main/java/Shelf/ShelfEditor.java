package Shelf;

import Task.DateTask;
import Task.Text;

import java.util.ArrayList;

/**
 * Class to edit information about Tasks
 * Correct unique feature based on student number ending with No.5
 * Previously worked on adding tags as assumed to be my unique feature to work on x_x
 */
public class ShelfEditor extends ShelfManager {
    public static void editSelector(String editType, String pos) {
        switch (editType) {
        case "description":
            editDescription(Integer.parseInt(pos)-1);
            break;
        case "date":
            editDate(Integer.parseInt(pos)-1);
            break;
        default:
            System.out.println("No such type: [" + editType + "], reverting you back to main commands");
            System.out.println(Text.newline);
        }
    }
    public static void editDescription (Integer idx){
        String newDes = Text.showPrompt("Enter the new description: ");
        ShelfManager.getShelf().get(idx).replaceDescription(newDes);
        System.out.println("Task description has been successfully changed");
        System.out.println(listItem(idx));
        System.out.println(Text.newline);
    }
    public static void editDate (Integer idx){
        if (ShelfManager.getShelf().get(idx) instanceof DateTask){
            String newDate = Text.showPrompt("Enter the new deadline in this format [yyyy-mm-dd]: ");

            DateTask dateTask = (DateTask) ShelfManager.getShelf().get(idx);
            dateTask.replaceDate(newDate);
            System.out.println("Task deadline has been successfully changed");
            System.out.println(listItem(idx));
            System.out.println(Text.newline);
        }else{
            System.out.println("Error, task does not contain a deadline, reverting you back to main commands");
            System.out.println(Text.newline);
        }
    }
}
