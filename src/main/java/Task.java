public class Task {
    private static int totalTasks = 0;
    private final String taskName;

    private final Character taskType;

    private boolean completed;

    public Task(Character taskType, String taskName) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.completed = false;
        totalTasks++;
    }

    /**
     * Method to mark the task as completed
     **/
    public void markAsCompleted() {
        this.completed = true;
    }

    /**
     * Method to mark the task as not completed
     **/
    public void markAsNotCompleted() {
        this.completed = false;
    }

    /**
     * Getter method to get the task name
     **/
    public String getTaskName() {
        return taskName;
    }

    /**
     * Getter method to get the task type
     **/
    public Character getTaskType() {
        return taskType;
    }

    /**
     * Getter method to check if the task is completed
     **/
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Getter method to get the total number of tasks
     **/
    public static int getTotalTasks() {
        return totalTasks;
    }

    public String toString() {
        String taskName = getTaskName();
        Character taskType = getTaskType();
        char taskComplete = isCompleted() ? 'X' : ' ';
        return "[" + taskType + "]" + "[" + taskComplete + "] " + taskName;
    }

}

class DeadlineTask extends Task {
    private final String taskDueDate;

    public DeadlineTask(Character taskType, String taskName, String taskDueDate) {
        super(taskType, taskName);
        this.taskDueDate = taskDueDate;
    }

    public String getTaskDueDate() {
        return taskDueDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", getTaskDueDate());
    }
}


class EventTask extends Task {
    private final String taskFrom;
    private final String taskTo;

    public EventTask(Character taskType, String taskName, String taskFrom, String taskTo) {
        super(taskType, taskName);
        this.taskFrom = taskFrom;
        this.taskTo = taskTo;
    }

    public String getTaskFrom() {
        return taskFrom;
    }

    public String getTaskTo() {
        return taskTo;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s to: %s)", getTaskFrom(), getTaskTo());
    }
}