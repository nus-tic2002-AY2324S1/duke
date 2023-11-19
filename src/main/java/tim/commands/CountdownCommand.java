package tim.commands;

import tim.tasks.Deadline;
import tim.tasks.Event;
import tim.ui.Display;
import tim.util.TaskList;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

public class CountdownCommand extends Command {

    /**
     * Identifies index of task and compares the date of the task to the current date.
     *      *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        LocalDate nowDate = LocalDate.now();
        long daysApart = 0;
        try {
            int index = Integer.parseInt(token[1]) - 1;
            switch (tasks.get(index).getType()){
            case 'D':
                Deadline deadline = (Deadline) tasks.get(index);
                daysApart = DAYS.between(nowDate, deadline.getByDate());
                Display.printCountdown(daysApart);
                break;
            case 'E':
                Event event = (Event) tasks.get(index);
                daysApart = DAYS.between(nowDate, event.getToDate());
                Display.printCountdown(daysApart);
                break;
            case 'T':
                System.out.println("oh no!  ToDo tasks has no end date!");
                Display.printDash();
                break;
            default:
                System.out.println("oh no!  unhandled error");
            }

        } catch (Exception e) {
            System.out.println("oh no!  please provide a valid index of the task!");
            Display.printDash();
        }
    }

}