package commands;

import java.util.ArrayList;
import java.util.regex.*;

import java.time.*;

import duke.Storage;
import duke.UI;
import exceptions.DukeException;
import tasks.Task;
import tasks.TaskList;

public class DueCommand extends Command{
    LocalDate date;
    ArrayList<Task> tasksDue;
    
    public DueCommand(String line) throws DukeException{
        if (line.split(" ").length < 2) throw new DukeException("Missing date!");
        if (line.split(" ").length > 2) throw new DukeException("Please follow the correct format.");
        String userDate = line.split(" ")[1];

        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        
        if(!Pattern.matches(regex, userDate)) throw new DukeException("Please provide the date in this format: YYYY-MM-DD");
        
        this.date = LocalDate.parse(userDate);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) { 
        this.tasksDue = tasks.getTasksDue(this.date);
        ui.printDue(this.tasksDue, this.date);
    }
}
