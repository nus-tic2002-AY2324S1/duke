//This java file contains all methods for tasks

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    //public classes need to be in a separate file
    //declare type for task type
    private Character taskType;
    //declare type for task name
    private String taskName;
    //declare type for task completion status
    private static Boolean taskIsComplete;
    //declare type for count of tasks
    public static int taskCount = 0;

    //constructor of same name
    public Task(Character taskType, String taskName) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskIsComplete = false;
        taskCount++;
    }

    //method to mark the task as completed
    public void taskCompleted() {
        this.taskIsComplete = true;
    }

    //method to mark the task as uncompleted
    public void taskNotCompleted() {
        this.taskIsComplete = false;
    }

    //Getter method for task type
    public Character getTaskType() {
        return taskType;
    }

    //Getter method for task name
    public String getTaskName() {
        return taskName;
    }

    //Getter method for task completion status
    public static Boolean getTaskCompletion() {
        return taskIsComplete;
    }

    //Getter method for count of tasks
    public static int getTaskCount() {
        return taskCount;
    }

    //method for deletion of task
    public static void removeTask() {
        taskCount--;
    }

    //store the task as a whole [][]task name in String
    public String toString() {
        String name = getTaskName();
        Character type = getTaskType();
        Character completion = getTaskCompletion() ? 'X' : ' ';
        return "[" + type + "]" + "[" + completion + "] " + name;
    }

}

//Deadline inheritance of Task
//Deadline inherits from Task == Deadline extends Task
class Deadline extends Task{
        //declare a new String type for storing the /by dueDate
        private String dueDate;

        //declare a new String type for storing the /by dueTime
        private String dueTime;
        public Deadline(Character taskType, String taskName, String dueDate, String dueTime){
            //super() calls the existing constructor in Task
            super(taskType, taskName);
            this.dueDate = dueDate;
            this.dueTime = dueTime;
        }
        public String getDueDate(){
            LocalDate d1 = LocalDate.parse(dueDate);
            return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        public String getdueTime(){
            return dueTime;
        }

        @Override
        public String toString(){
            return super.toString() + " (by: " + getDueDate() + " " + getdueTime() + ")";
        }
}

//Event inheritance of Task
class Event extends Task{
        private String startDate;
        private String endDate;
        private String startTime;
        private String endTime;
        public Event(Character taskType, String taskName, String startDate, String startTime, String endDate, String endTime){
            super(taskType, taskName);
            this.startDate = startDate;
            this.endDate = endDate;
            this.startTime = startTime;
            this.endTime = endTime;
        }
        public String getStartDate(){
            LocalDate d2 = LocalDate.parse(startDate);
            return d2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        public String getEndDate(){
            LocalDate d3 = LocalDate.parse(endDate);
            return d3.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }
        @Override
        public String toString(){
            return super.toString() + " (from: " + getStartDate() + " " + getStartTime() + " to: " + getEndDate() + " " + getEndTime() + ")";
        }
}

//FixedDuration inheritance of Task
class FixedDuration extends Task{
    public FixedDuration(Character taskType, String taskName){
        //super() calls the existing constructor declared in Task
        super(taskType, taskName);
    }
    @Override
    public String toString(){
        return super.toString();
    }
}
