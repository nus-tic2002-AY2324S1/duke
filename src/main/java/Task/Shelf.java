package Task;
import Duke.DukeException;
import java.util.ArrayList;

public class Shelf {
    private static ArrayList<SpecialTask> shelf;
    public Shelf(){
        shelf = new ArrayList<>();
    }
    public static void listShelf(){
        if(shelf.isEmpty()){
            Text.printMessage(Text.Message.NOITEM);
            return;
        }
        System.out.print(Text.newline);
        System.out.println("No. [Type] [Marking] Description"); // listing sequence
        for(int i = 0; i < shelf.size(); i++){
            String deadline = "";
            if (shelf.get(i) instanceof DateTask){
                DateTask dateTask = (DateTask) shelf.get(i);
                deadline = dateTask.getDate();
            }
            System.out.println(Integer.toString(i+1)+ ": " + "["  + shelf.get(i).getTypeIcon() + "]" + "["  + shelf.get(i).getStatusIcon() + "]" + shelf.get(i).description + deadline);
        }
        System.out.println(Text.newline);
    }
    public static void addItem(String item){
        SpecialTask t = new SpecialTask(item,"");
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
        System.out.print("[" + t.getTypeIcon() + "]" + "[" + t.getStatusIcon() +"] " + t.getDescription() + "\n" + Text.newline);
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
        System.out.println("[" + shelf.get(idx).getStatusIcon() + "]" + shelf.get(idx).description + "\n" + Text.newline);
    }
    public static void addSpecialTask(String item, String type){
        SpecialTask t = new SpecialTask(item,type);
        shelf.add(t);
        System.out.println(Text.newline + "Got it. I've added this task:");
        System.out.print("[" + t.getTypeIcon() + "]" + "[ ] " + item + "\n" + Text.newline);
        System.out.println("Now you have "+ shelf.size() +" tasks in the list.");
    }
    public static void addDateTask(String item, String type, String date){
        DateTask d = new DateTask(item, type, date);
        shelf.add(d);
        System.out.println(Text.newline + "Got it. I've added this task with a deadline:");
        System.out.print("[" + d.getTypeIcon() + "]" + "[ ] " + item + " " + date +"\n" + Text.newline);
        System.out.println("Now you have "+ shelf.size() +" tasks in the list.");
    }
    public static String ShelftoString(){
        String save = "";
        for (SpecialTask specialTask : shelf) {
            String deadline = "";
            if (specialTask instanceof DateTask){
                DateTask dateTask = (DateTask) specialTask;
                deadline = " /by " + String.valueOf(dateTask.deadline);
            }
            save += specialTask.getTypeIcon() + "|" + specialTask.getStatusIcon() + "|" + specialTask.description + deadline + "\n";
        }
        return save;
    }
}
