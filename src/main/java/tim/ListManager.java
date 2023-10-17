package tim;
import tim.tasks.Task;
import tim.tasks.ToDo;
import tim.tasks.Event;
import tim.tasks.Deadline;
import java.util.ArrayList;

public class ListManager {
    static void addToDo (String inputEntry, ArrayList<Task> list){
        list.add(new ToDo(inputEntry));
        System.out.println("Gotcha! Added this task:");
        Messenger.printSingle(list.size(),list);
        System.out.println("now there is: "+ list.size() + " item(s)");
        Messenger.printDashes();
    }

    static void addDeadline (String inputEntry, ArrayList<Task> list){
        String[] inputTokenized;
        String by;
        try{
            inputTokenized = inputEntry.split("/by ",2);
            by = inputTokenized[1];
            String description = inputTokenized[0];
            list.add(new Deadline(description,by));
            System.out.println("Gotcha! Added this task:");
            Messenger.printSingle(list.size(),list);
            System.out.println("now there is: "+ list.size() + " item(s)");
            Messenger.printDashes();
        } catch (Exception e) {
            System.err.println("you've missed out [/by] !");
        }

    }

    static void addEvent (String inputEntry,ArrayList<Task> list){
        String[] inputTokenized;
        String from;
        String to;
        try {
            inputTokenized =  inputEntry.split("/from ",2);
            String description = inputTokenized[0];
            to = inputTokenized[1].split("/to ", 2)[1];
            from = inputTokenized[1].split("/to ", 2)[0];
            list.add(new Event(description,from,to));
            System.out.println("Gotcha! Added this task:");
            Messenger.printSingle(list.size(),list);
            System.out.println("now there is: "+ list.size() + " item(s)");
            Messenger.printDashes();
        } catch (Exception e) {
            System.err.println("you've missed out either the description, [/from] or [/to] !");
        }

    }

    static void deleteFromList (int deleteIndex,ArrayList<Task> list){
        System.out.print("Deleting: ");
        Messenger.printSingle(deleteIndex,list);
        Messenger.printDashes();
        list.remove(deleteIndex-1);
        Messenger.printList(list);
    }

    static void markUnmarkTask(int index, boolean markUnmark, ArrayList<Task> list){
        Task target = list.get(index-1);

        if((target.getIsDone().equals("x")) != markUnmark){
            if(markUnmark){
                System.out.println("Nice! I've marked this task as done:");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
            target.setIsDone(markUnmark);
            Messenger.printSingle(index,list);
        } else {
            System.out.print("Task is already " + (markUnmark ? "marked" : "unmarked") + ".");

        }
        Messenger.printDashes();

    }
}
