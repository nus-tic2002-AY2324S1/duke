//this file contains all methods for tasks.

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
        public Deadline(Character taskType, String taskName, String dueDate){
            //super() calls the existing constructor
            super(taskType, taskName);
            this.dueDate = dueDate;
        }
        public String getDueDate(){
            return dueDate;
        }

        @Override
        public String toString(){
            return super.toString() + String.format(" (by: %s)", getDueDate());
        }
}

//Event inheritance of Task
class Event extends Task{
        private String startDate;
        private String endDate;
        public Event(Character taskType, String taskName, String startDate, String endDate){
            super(taskType, taskName);
            this.startDate = startDate;
            this.endDate = endDate;
        }
        public String getStartDate(){
            return startDate;
        }
        public String getEndDate(){
            return endDate;
        }
        @Override
        public String toString(){
            return super.toString() + String.format(" (from: %s to: %s)", getStartDate(), getEndDate());
        }
}
