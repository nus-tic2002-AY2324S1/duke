package tim.body.commands;

import tim.body.BlankInputException;
import tim.body.DateException;
import tim.body.TaskList;
import tim.body.UI;
import tim.tasks.Event;

import java.time.format.DateTimeParseException;


public class EventCommand extends Command{

    /**
     * Identify Event's task name to be added.
     * Calls TaskList.addEvent() to add task.
     *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        try {
            String taskName = token[1];
            addEvent(taskName, tasks);
        } catch (ArrayIndexOutOfBoundsException AIO) {
            System.err.println("what is the name of the event?");
        }
    }

    /**
     * Adds an event to the back of the list.
     * inputEntry is parsed into description, fromDate and toDate.
     *
     * @param inputEntry Input string from the user.
     * @param list list of tasks.
     */
    public static void addEvent(String inputEntry, TaskList list){
        String[] inputTokenized;
        String fromDate;
        String toDate;
        try {
            inputTokenized =  inputEntry.split("/from ",2);
            String description = inputTokenized[0];
            if(description.isBlank()){
                throw new BlankInputException("missing description");
            }
            toDate = inputTokenized[1].split(" /to ", 2)[1];
            fromDate = inputTokenized[1].split(" /to ", 2)[0];
            list.add(new Event(description,fromDate,toDate));
            System.out.println("Gotcha! Added this task:");
            UI.printSingle(list.size(),list);
            System.out.println("now there is: "+ list.size() + " item(s)");
            UI.printDash();
        } catch (ArrayIndexOutOfBoundsException AIO) {
            System.err.println("you've missed out [/fromDate] or [/toDate] !");
        } catch(DateTimeParseException DTPE ){
            System.err.println("that's not a valid date, please enter << /fromDate yyyy-mm-dd /toDate yyyy-mm-dd>>");
        } catch (BlankInputException BIE) {
            System.err.println("you've missed out the description");
        } catch (DateException DE){
            System.err.println("/fromDate date shouldn't be later than /toDate date");
        }

    }

}
