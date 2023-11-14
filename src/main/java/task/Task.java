package task;

/**
 * Represents a "todo" <code>Task</code> object.
 * A todo task with description of the task  e.g., <code>buy bread</code>
 */
public class Task {
    private boolean isDone;
    private String description;

    /**
     * Constructor for Task object
     *
     * @param d A String contains description of the <code>Task</code> input by the user.
     * @param x represent the condition of the task "done/not done".
     */
    public Task (String d,boolean x){
        this.isDone = x ;
        setDescription(d);
    }

    /**
     * Setter for condition of the <code>Task</code> object
     *
     * @param x represent the condition of the task "done/not done".
     */
    public void setIsDone(boolean x){
        this.isDone = x;
    }

    /**
     * Setter for description of the <code>Task</code> object
     *
     * @param description Description of the todo Task
     * @throws ArrayIndexOutOfBoundsException If there is no input in the description .
     */
    public void setDescription(String description){
        String[] words = description.split(" ", 2);
        try {
            this.description = words[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            //error message display is at main class
        }

    }

    /**
     * Getter for condition of the <code>Task</code> object
     *
     * @return A boolean value represents if the todo Task has been done.
     */
    public boolean getIsDone() {
        return !isDone;
    }

    /**
     * Getter for description of the <code>Task</code> object
     *
     * @return A String value that contains the Description of the task.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Getter for all the details of the task.
     *
     * @return A String value shows the condition and description of the task.
     */
    public String getTask(){
       String result = "";
        if (!isDone){
            result = "[T][ ] "+ getDescription();
        }else {
            result = "[T][X] "+ getDescription();
        }
       return result;
    }
    /**
     * Method to call the getTask() method and print out the details of the task.
     *
     */
    public void printTask(){
            System.out.println(getTask());
    }
}
