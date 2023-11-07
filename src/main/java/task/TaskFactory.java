package task;

public class TaskFactory {

    public static Task createTask(String taskType, boolean isDone, String desc, String fromDate, String toDate) {
        if ("todo".equalsIgnoreCase(taskType)) {
            return new ToDo(desc, isDone);
        } else if ("Deadline".equalsIgnoreCase(taskType)) {
            return new Deadline(desc, isDone, fromDate);
        } else if ("Event".equalsIgnoreCase(taskType)) {
            return new Event(desc, isDone, fromDate, toDate);
        } else {
            throw new IllegalArgumentException("Unsupported task type: " + taskType);
        }
    }
}
