package duke.command;

import java.util.ArrayList;

import duke.data.UserKeywordArgument;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "LIST";
    public ListCommand(){}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument) {
        if(taskList.size() == 0){
            String str = "Your list is empty. Let's start adding some items! :)";
            ui.showResponseToUser(str);
        }else{
            ArrayList<Task> tasks = taskList.getTasks();
            ArrayList<String> messages = new ArrayList<>();
            messages.add("Here are the tasks in your list:");
            for (int i = 0; i <= tasks.size()-1; i++) {
                messages.add(String.format("%d.%s",i+1, tasks.get(i).toString()));
            }
            ui.showResponseToUser(messages);
        }
    }
}
