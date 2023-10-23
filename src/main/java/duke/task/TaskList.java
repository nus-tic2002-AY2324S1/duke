package duke.task;

import duke.exception.DukeException;
import duke.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    private ArrayList<Task> taskList;

    public static final String todoPattern = "^todo (.+)$";
    public static final String deadlinePattern = "^deadline (.+) /by (.+)$";
    public static final String eventPattern =  "^event (.+) /from (.+) /to (.+)$";

    static Pattern todoRegex = Pattern.compile(todoPattern);
    static Pattern deadlineRegex = Pattern.compile(deadlinePattern);
    static Pattern eventRegex = Pattern.compile(eventPattern);

    public TaskList(){
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
    }


    public ArrayList<Task> getTaskList(){
        return taskList;
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public Task deleteTask(int index) throws DukeException, IOException {
        if(index>=0 && index<taskList.size()){
            Task deletedTask = taskList.get(index);
            taskList.remove(index);
            Storage.save(new TaskList(taskList));
            return deletedTask;
        } else {
            throw new DukeException("Invalid index, Please enter a valid int number. ");
        }
    }

    public Task markTaskAsDone(int index) throws DukeException, IOException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("Invalid task index. Please specify a valid task to mark as done.");
        }
        taskList.get(index).markAsDone();
        Task task = taskList.get(index);
        Storage.save(new TaskList(taskList));
        return task;
    }

    public Task unmarkTaskAsNotDone(int index) throws DukeException, IOException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("Invalid task index. Please specify a valid task to mark as done.");
        }
        taskList.get(index).unmarkDone();
        Task task = taskList.get(index);
        Storage.save(new TaskList(taskList));
        return task;
    }

    public Todos newTodoTask(String fullCommand) throws DukeException {
        Matcher todoMatcher = todoRegex.matcher(fullCommand);
        if(!todoMatcher.matches()){
            throw new DukeException("Invalid command, Please follow todo task command format");
        }
        return new Todos(todoMatcher.group(0));
    }

    public Deadlines newDeadlineTask(String fullCommand) throws DukeException {
        Matcher deadlineMatcher = deadlineRegex.matcher(fullCommand);
        if(!deadlineMatcher.matches()){
            throw new DukeException("Invalid command, Please follow deadline task command format");
        }
        String desc = deadlineMatcher.group(0) + "(by: " +deadlineMatcher.group(1) + ")";
        return new Deadlines(desc);
    }

    public Events newEventTask(String fullCommand) throws DukeException {
        Matcher eventMatcher = eventRegex.matcher(fullCommand);
        if(!eventMatcher.matches()){
            throw new DukeException("Invalid command, Please follow event task command format");
        }
        String desc = eventMatcher.group(0) + "(from: " + eventMatcher.group(1) + " to: " + eventMatcher.group(2) + ")";
        return new Events(desc);
    }

}
