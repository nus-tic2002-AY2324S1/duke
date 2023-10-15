package duke.command;

import java.util.ArrayList;

import duke.data.UserKeywordArgument;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.Main;

public class List implements ICommand {
    public List(){
//        response();
    }


    public void response(){
        Ui ui = new Ui();
        if(Task.getNumberOfTasks() == 0){
            String str = "Your list is empty. Let's start adding some items! :)";
            ui.showToUser(Ui.prefixRespString() + str);
        }else{
//            TaskList tasks = getTasks();
//            ArrayList<String> inputs = new ArrayList<>();
            String str  = "Here are the tasks in your list:";
//            for (int i = 0; i <= tasks.size()-1; i++) {
//                inputs.add(String.format("%d.%s",i+1, tasks.get(i).toString()));
//            }
            ui.showToUser(Ui.prefixRespString() + str);
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument) {
        if(Task.getNumberOfTasks() == 0){
            String str = "Your list is empty. Let's start adding some items! :)";
            ui.showToUser(Ui.prefixRespString() + str);
        }else{
            ArrayList<Task> tasks = taskList.getTasks();
            ArrayList<String> messages = new ArrayList<>();
            String str  = "Here are the tasks in your list:";
            for (int i = 0; i <= tasks.size()-1; i++) {
                messages.add(String.format("%d.%s",i+1, tasks.get(i).toString()));
            }
            ui.showResponseToUser(messages);
        }
    }
}
