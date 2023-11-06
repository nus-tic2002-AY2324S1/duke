package tim.body.commands;

import tim.body.BlankInputException;
import tim.body.TaskList;
import tim.body.UI;
import tim.tasks.Deadline;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command{

    /**
     * Identify Deadline's task name to be added.
     * Calls TaskList.addDeadline() to add task.
     *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        try {
            String taskName = token[1];
            addDeadline(taskName, tasks);
        } catch (ArrayIndexOutOfBoundsException AIO) {
            System.err.println("what is the name of the deadline agenda?");
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
            UI.printSingle(tasks.size(),tasks);
            System.out.println("now there is: "+ tasks.size() + " item(s)");
            UI.printDash();
        } catch (ArrayIndexOutOfBoundsException AIO) {
            System.err.println("you've missed out [/by] !");
        } catch(DateTimeParseException DTPE ){
            System.err.println("that's not a valid date, please enter << /by yyyy-mm-dd >>");
        } catch (BlankInputException BIE) {
            System.err.println("you've missed out the description");
        }

    }
}
