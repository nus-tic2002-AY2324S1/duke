package task;

public class TaskFactory {

    public static Task createTask(String taskType, boolean isDone, String description, String fromDate, String toDate) {
        if ("todo".equalsIgnoreCase(taskType)) {
            return new ToDo(description, isDone);
        } else if ("Deadline".equalsIgnoreCase(taskType)) {
            return new Deadline(description, isDone, fromDate);
        } else if ("Event".equalsIgnoreCase(taskType)) {
            return new Event(description, isDone, fromDate, toDate);
        } else {
            throw new IllegalArgumentException("Unsupported task type: " + taskType);
        }
    }
}
