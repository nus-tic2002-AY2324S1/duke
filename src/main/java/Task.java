public class Task {
    public String taskName;
    public boolean completed;

    private static int totalTasks = 0;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
        totalTasks++;
    }

    // Method to mark the task as completed
    public void markAsCompleted() {
        this.completed = true;
    }

    // Method to mark the task as not completed
    public void markAsNotCompleted() {
        this.completed = false;
    }

    // Getter method to get the task name
    public String getTaskName() {
        return taskName;
    }


    // Getter method to check if the task is completed
    public boolean isCompleted() {
        return completed;
    }

    // Getter method to get the total number of tasks
    public static int getTotalTasks() {
        return totalTasks;
    }

}