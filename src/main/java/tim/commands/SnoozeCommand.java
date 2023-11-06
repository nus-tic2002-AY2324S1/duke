package tim.body.commands;

import tim.body.Exceptions.DateException;
import tim.body.TaskList;
import tim.body.UI;
import tim.tasks.Deadline;
import tim.tasks.Event;

/**
 * Represents a SnoozeCommand object.
 * The object snoozes a task by one week or postpones a task to a specified date.
 */
public class SnoozeCommand extends Command{

    /**
     * Identify index of task to be snoozed.
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
                throw new Exception();
            }
            if(temp.length>1 && !temp[1].isBlank()) {
                postPoneTask(trueIndex, temp[1], tasks);
            } else {
                snoozeTaskOneWeek(trueIndex, tasks);
            }
            System.out.println("Snoozed task " + trueIndex + " by one week.");
            System.out.println("Updated Details: ");
            UI.printSingle(trueIndex, tasks);
            UI.printDash();
        } catch (Exception e) {
            System.err.println("please check index or date to postpone to!");
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
                System.err.println("ToDo tasks cannot be snoozed!");
            default:
                System.err.println("error");
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
                System.err.println("ToDo tasks cannot be snoozed!");
            default:
                System.err.println("error");
        }

    }
}
