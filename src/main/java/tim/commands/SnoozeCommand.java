package tim.commands;

import tim.ui.Display;
import tim.exceptions.DateException;
import tim.util.TaskList;
import tim.tasks.Deadline;
import tim.tasks.Event;

/**
 * Represents a SnoozeCommand object.
 * The object snoozes a task by one week or postpones a task to a specified date.
 */
public class SnoozeCommand extends Command{

    /**
     * Identifies index of task to be snoozed.
     * If no date is specified, snooze by one week.
     * Else, snooze to the specified date.
     * trueIndex is the user facing index of the task.
     * index is the array index of the task.
     *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        try {
            String[] temp = token[1].split(" ",2);
            int trueIndex = Integer.parseInt(temp[0]);
            if(tasks.get(trueIndex-1).getType() == 'T'){
                System.out.println("oh no!  ToDo tasks cannot be snoozed!");
                throw new Exception();
            }
            if(temp.length>1 && !temp[1].isBlank()) {
                postPoneTask(trueIndex, temp[1], tasks);
                System.out.println("Snoozed task " + trueIndex + ", new end date set.");
            } else {
                snoozeTaskOneWeek(trueIndex, tasks);
                System.out.println("Snoozed task " + trueIndex + " by one week.");
            }
            System.out.println("Updated Details: ");
            Display.printSingle(trueIndex, tasks);
            Display.printDash();
        } catch (Exception e) {
            System.out.println("oh no!  please check index or date to postpone to!");
        }
    }

    /**
     * Postpones the task at the given index from the list to the specified date.
     *
     * @param index Index of the task to be postponed.
     * @param newDate The date to postpone the task to.
     * @param tasks List of tasks.
     * @throws DateException
     */
    static void postPoneTask(int index, String newDate, TaskList tasks) throws DateException {
        index--;
        assert tasks.get(index).getType() != 'T' : "assert ToDo tasks cannot be snoozed!";
        switch (tasks.get(index).getType()){
        case 'D':
            Deadline deadline = (Deadline) tasks.get(index);
            deadline.postponeByDate(newDate);
            break;
        case 'E':
            Event event = (Event) tasks.get(index);
            event.postponeToDate(newDate);
            break;
        case 'T':
            System.out.println("oh no!  ToDo tasks cannot be snoozed!");
        default:
            System.out.println("oh no!  unhandled error");
        }

    }

    /**
     * Snoozes the task at the given index from the list by one week.
     *
     * @param index Index of the task to be postponed.
     * @param tasks List of tasks.
     * @throws DateException
     */
    static void snoozeTaskOneWeek(int index, TaskList tasks) throws DateException {
        index--;
        assert tasks.get(index).getType() != 'T' : "assert ToDo tasks cannot be snoozed!";
        switch (tasks.get(index).getType()){
            case 'D':
                Deadline deadline = (Deadline) tasks.get(index);
                deadline.snoozeByDateByOneWeek();
                break;
            case 'E':
                Event event = (Event) tasks.get(index);
                event.snoozeToDateByOneWeek();
                break;
            case 'T':
                System.out.println("oh no!  ToDo tasks cannot be snoozed!");
            default:
                System.out.println("oh no!  unhandled error");
        }

    }
}
