package tim.commands;

import tim.ui.Display;
import tim.util.TaskList;
import tim.exceptions.BlankInputException;
import tim.tasks.Deadline;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command{

    /**
     * Identifies Deadline's task name to be added.
     * Calls TaskList.addDeadline() to add Deadline task to task list.
     *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        try {
            String taskName = token[1];
            addDeadline(taskName, tasks);
        } catch (ArrayIndexOutOfBoundsException AIO) {
            System.out.println("oh no!  what is the name of the deadline agenda?");
            Display.printDash();
        }
    }

    /**
     * Adds a deadline to the back of the list.
     * inputEntry is parsed into description and byDate.
     *
     * @param inputEntry Input string from the user.
     * @param tasks List of tasks.
     */
    static void addDeadline(String inputEntry, TaskList tasks){
        String[] inputTokenized;
        String byDate;
        try{
            inputTokenized = inputEntry.split("/by ",2);
            byDate = inputTokenized[1];
            String description = inputTokenized[0];
            if(description.isBlank()){
                throw new BlankInputException("missing description");
            }
            tasks.add(new Deadline(description,byDate));
            System.out.println("Gotcha! Added this task:");
            Display.printSingle(tasks.size(),tasks);
            System.out.println("now there is: "+ tasks.size() + " item(s)");
            Display.printDash();
        } catch (ArrayIndexOutOfBoundsException AIO) {
            System.out.println("oh no!  you've missed out [/by] !");
            Display.printDash();
        } catch(DateTimeParseException DTPE ){
            System.out.println("oh no!  that's not a valid date, please enter << /by yyyy-mm-dd >>");
            Display.printDash();
        } catch (BlankInputException BIE) {
            System.out.println("oh no!  you've missed out the description");
            Display.printDash();
        }

    }
}
