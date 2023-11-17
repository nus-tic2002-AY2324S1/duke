package Shelf;
import Duke.DukeException;
import Task.DateTask;
import Task.SpecialTask;
import Task.Task;
import Task.Text;

import java.util.ArrayList;
/**
 * Methods to manipulate a list of information in the database based on user command
 * Such as CRUD of tasks.
 */

public class Shelf {
    private static ArrayList<SpecialTask> shelf;
    public Shelf(){
        shelf = new ArrayList<>();
    }
    public static String listItem(Integer i){
        String listString = i + 1 + ": " + "["  + shelf.get(i).printTypeIcon() + "]" + "["  + shelf.get(i).printStatusIcon() + "]" + shelf.get(i).getDescription();
        if (shelf.get(i) instanceof DateTask){
            DateTask dateTask = (DateTask) shelf.get(i);
            listString += dateTask.showDate();
        }
        String tags = shelf.get(i).getTagSeries();
        if (!tags.isEmpty()) {
            listString += " " + tags;
        }
        return listString;
    }
    public static void listShelf(){
        if(shelf.isEmpty()){
            Text.printMessage(Text.Message.NOITEM);
            return;
        }
        System.out.print(Text.newline);
        System.out.println("No. [Type] [Marking] Description (Date) #tag1..."); // listing sequence
        for(int i = 0; i < shelf.size(); i++){
            System.out.println(listItem(i));
        }
        System.out.println(Text.newline);
    }
    public static void addItem(String item){
        SpecialTask t = new SpecialTask("",item);
        shelf.add(t);
        System.out.println(
                Text.newline
                        + "added: " + item + "\n"
                        + Text.newline
        );
    }
    public static void deleteTask(String num){
        int idx = Integer.parseInt(num) - 1;
        SpecialTask t = shelf.get(idx);
        shelf.remove(idx);
        System.out.println("Noted. I've removed this task:");
        System.out.print("[" + t.printTypeIcon() + "]" + "[" + t.printStatusIcon() +"] " + t.getDescription() + "\n" + Text.newline);
        System.out.println("Now you have "+ shelf.size() +" tasks in the list.");
    }
    public static void markTask (String[] msg) throws DukeException {
        String markingIndication = msg[0];
        int idx = Integer.parseInt(msg[1]) - 1;
        if(idx >= shelf.size()){
            System.out.println("You have selected an invalid task");
            return;
        }
        System.out.print(Text.newline);
        if (markingIndication.equals("mark")){
            Task.setMarked(shelf.get(idx));
            System.out.println("Nice! I've marked this task as done:");
        } else if(markingIndication.equals("unmark")){
            Task.setUnmarked(shelf.get(idx));
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("[" + shelf.get(idx).printStatusIcon() + "]" + shelf.get(idx).getDescription() + "\n" + Text.newline);
    }
    public static void addSpecialTask(String type, String item){
        SpecialTask t = new SpecialTask(type,item);
        shelf.add(t);
        System.out.println(Text.newline + "Got it. I've added this task:");
        System.out.print("[" + t.printTypeIcon() + "]" + "[ ] " + item + "\n" + Text.newline);
        System.out.println("Now you have "+ shelf.size() +" tasks in the list.");
    }
    public static void addDateTask(String type, String item, String date){
        DateTask d = new DateTask(type, item, date);
        shelf.add(d);
        System.out.println(Text.newline + "Got it. I've added this task with a deadline:");
        System.out.print("[" + d.printTypeIcon() + "]" + "[ ] " + item + " " + d.showDate() +"\n" + Text.newline);
        System.out.println("Now you have "+ shelf.size() +" tasks in the list.");
    }
    public static void addTagslist (String tags, String pos) throws DukeException {
        int idx = Integer.parseInt(pos) - 1;
        if(idx >= shelf.size()){
            System.out.println("You have selected an invalid task to add tags");
            return;
        }
        String[] tagArray = tags.split(",");
        for (String tag : tagArray){
            Task.addTag(shelf.get(idx), tag);
        }
    }
    public static void removeTagfromlist (String tags, String pos) throws DukeException {
        int idx = Integer.parseInt(pos) - 1;
        if(idx >= shelf.size()){
            System.out.println("You have selected an invalid task number to remove a tag from...");
            return;
        }
        String[] tagArray = tags.split(",");
        for (String tag : tagArray){
            Task.removeTag(shelf.get(idx), tag);
        }
    }
    public static void findItem(String keyword){
        ArrayList<Integer> idxArray = new ArrayList<>();
        for(int i = 0; i < shelf.size(); i++ ){
            String description = shelf.get(i).getDescription();
            if(description.contains(keyword)){
                idxArray.add(i);
            }
        }
        if(!idxArray.isEmpty()){
            System.out.print(Text.MATCHINGTASK);
            for(Integer idx : idxArray){
                System.out.println(listItem(idx));
            }
            System.out.print(Text.newline);
        }else {
            System.out.println(Text.NOMATCHINGTASK);
        }
    }
    public static String ShelftoString(){
        String save = "";
        String deadline = "~";
        String tags = "~";;
        for (SpecialTask specialTask : shelf) {
            if (specialTask instanceof DateTask){
                DateTask dateTask = (DateTask) specialTask;
                deadline = DateTask.dateToString(dateTask.deadline);
            }
            save += specialTask.getType() + "|" + specialTask.getStatus() + "|" + specialTask.getDescription() + "|" + deadline + "|" + tags + "|" + "\n";
        }
        return save;
    }
}
