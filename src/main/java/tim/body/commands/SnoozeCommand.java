package tim.body.commands;

import tim.body.DateException;
import tim.body.TaskList;
import tim.body.UI;
import tim.tasks.Deadline;
import tim.tasks.Event;

public class SnoozeCommand extends Command{


    public void execute(String[] token, TaskList tasks){
        try {
            int trueIndex = Integer.parseInt(token[1]);
            if(tasks.get(trueIndex-1).getType() == 'T'){
                throw new Exception();
            }
            snoozeTaskOneWeek(trueIndex, tasks);
            System.out.println("Snoozed task " + trueIndex + " by one week.");
            System.out.println("Updated Details: ");
            UI.printSingle(trueIndex, tasks);
            UI.printDash();
        } catch (Exception e) {
            System.err.println("ToDo tasks cannot be snoozed!");
        }
    }


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
