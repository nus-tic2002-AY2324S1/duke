package List;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

/**
 * Manages and operators a list of tasks.
 */
public class Tasklist {

    private ArrayList<Task> tasks;

    /**
     * Constructs a Tasklist object.
     *
     * @param allTasks An arraylist that contains all the tasks
     */
    public Tasklist(ArrayList<Task> allTasks) {
        this.tasks = allTasks;
    }

    /**
     * List all tasks in the task list.
     */
    public void listTasks(){

        if (tasks.size() == 0){
            System.out.println("No tasks at the moment!");
        } else {
            for (int i = 0; i< tasks.size(); i++){
                System.out.println("(" + (i + 1) + ") " + tasks.get(i));
            }
        }


    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param indexToDelete Index in the Tasklist array to be deleted
     */
    public void deleteTask(int indexToDelete){
        tasks.remove(indexToDelete);
    }


    /**
     * Stores a new task in the task list with a task description.
     *
     * @param userInput Task's description that was inputted by User
     */
    public void storeTasks(String userInput) {

        tasks.add(new Task(userInput, false));

    }


    /**
     * Stores a new deadline in the task list
     *
     * @param userInput Task's description that was inputted by User
     * @param doneBefore Task's deadline that was inputted by User
     */
    public void storeTasks(String userInput, Temporal doneBefore){

        tasks.add(new Deadline(userInput, false, doneBefore));

    }

    /**
     * Stores a new event in the task list
     *
     * @param userInput Task's description that was inputted by User
     * @param doneFrom Task's Date From that was inputted by User
     * @param doneTo Task's Date To that was inputted by User
     */
    public void storeTasks(String userInput, Temporal doneFrom, Temporal doneTo){

        tasks.add(new Event(userInput, false, doneFrom, doneTo));

    }

    /**
     * Checks if a task index is within the valid range.
     *
     * @param taskString Index of tasklist to be checked
     * @return Boolean if within range
     */
    public boolean checkTaskRange(int taskString){

        int taskIndex = taskString - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks(){
        return this.tasks;
    }


    /**
     * Gets a task by its index.
     *
     * @param taskNum Index of a task within the Task List array
     * @return The task at the specified index.
     */
    public Task getTask(int taskNum){
        return this.tasks.get(taskNum-1);
    }


    /**
     * Looks for tasks with a specific search phrase and prints them out
     *
     * @param searchPhrase Search phrase to be searched within task description
     *
     */
    public void findTasksViaDescription(String searchPhrase) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskDescription().contains(searchPhrase)) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.size() >= 1){
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + foundTasks.get(i));
            }
        } else {
            System.out.println("No suitable tasks found!");
        }

    }


    /**
     * Converts input into LocalDate format to be used for findTasksViaDate()
     *
     * @param inputLocalDateTime Temporal variable to be converted to LocalDate
     * @return LocalDate variable
     */

    private LocalDate convertLocalDateTime(Temporal inputLocalDateTime){
        if (inputLocalDateTime instanceof LocalDate){
            LocalDate ldPlaceHolder = (LocalDate) inputLocalDateTime;
            return ldPlaceHolder;
        } else if (inputLocalDateTime instanceof LocalDateTime) {
            LocalDateTime ldtPlaceHolder = (LocalDateTime) inputLocalDateTime;
            LocalDate ldPlaceHolder = ldtPlaceHolder.toLocalDate();
            return ldPlaceHolder;
        }

        return null;
    }


    /**
     * Looks for tasks with a specific date and prints them out
     *
     * @param searchDateInput Search date to be searched
     *
     */
    public void findTasksViaDate(Temporal searchDateInput) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        LocalDate searchDate = convertLocalDateTime(searchDateInput);
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                Temporal doneBefore = deadline.getDoneBefore();
                LocalDate deadlineDate = convertLocalDateTime(doneBefore);

                if (searchDate.compareTo(deadlineDate) == 0) {
                    foundTasks.add(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                Temporal doneFrom = event.getDoneFrom();
                Temporal doneTo = event.getDoneTo();
                LocalDate eventDateFrom = convertLocalDateTime(doneFrom);
                LocalDate eventDateTo = convertLocalDateTime(doneTo);

                //Checks to see if searchDate is between eventDateFrom and eventDateTo
                if (searchDate.compareTo(eventDateFrom) >= 0 && searchDate.compareTo(eventDateTo) <= 0) {
                    foundTasks.add(task);
                }
            }
        }
        if (!foundTasks.isEmpty()) {
            System.out.println("Tasks with date " + searchDate + ":");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + foundTasks.get(i));
            }
        } else {
            System.out.println("No tasks found for date " + searchDate);
        }
    }

}
