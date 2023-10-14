abstract class Task {
    private static int totalTasks = 0;
    private final String taskName;

    private final Character taskType;

    boolean completed;

    public Task(Character taskType, String taskName) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.completed = false;
        totalTasks++;
    }

    public Task(Character taskType, String taskName, boolean isCompleted) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.completed = isCompleted;
        totalTasks++;
    }

    /**
     * Getter method to get the total number of tasks
     **/
    public static int getTotalTasks() {
        return totalTasks;
    }

    public static void removeTask() {
        totalTasks--;
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

    public String toString() {
        String taskName = getTaskName();
        Character taskType = getTaskType();
        char taskComplete = isCompleted() ? 'X' : ' ';
        return "[" + taskType + "]" + "[" + taskComplete + "] " + taskName;
    }

    public String toFile() {
        String taskName = getTaskName();
        Character taskType = getTaskType();
        int taskComplete = isCompleted() ? 1 : 0;
        return taskType + " | " + taskComplete + " | " + taskName;
    }
}

class TodoTask extends Task {

    public TodoTask(String taskName) {
        super('T', taskName);
    }

    public TodoTask(String taskName, boolean completed) {
        super('T', taskName, completed);
    }
}

class DeadlineTask extends Task {
    protected String taskDueDate;

    public DeadlineTask(String taskName, String taskDueDate) {
        super('D', taskName);
        this.taskDueDate = taskDueDate;
    }

    public DeadlineTask(String taskName, boolean completed, String taskDueDate) {
        super('D', taskName, completed);
        this.taskDueDate = taskDueDate;
    }

    public String getTaskDueDate() {
        return taskDueDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", getTaskDueDate());
    }

    @Override
    public String toFile() {
        return super.toFile() + " | " + getTaskDueDate();
    }

}


class EventTask extends Task {
    private final String taskFrom;
    private final String taskTo;

    public EventTask(String taskName, String taskFrom, String taskTo) {
        super('E', taskName);
        this.taskFrom = taskFrom;
        this.taskTo = taskTo;
    }

    public EventTask(String taskName, boolean completed, String taskFrom, String taskTo) {
        super('E', taskName, completed);
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

    @Override
    public String toFile() {
        return super.toFile() + " | " + getTaskFrom() + " | " + getTaskTo();
    }
}