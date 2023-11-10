package duke.commands;

import java.util.List;

/**
 * Update task in list to the specified input
 */
public class Update extends Task {
    public static final String CMD = "update";
    public static final String CMD_HELP = "[" + CMD + "] \t"
            + "Update the task in the list using the index and field to override ||"
            + "Syntax: update <index(+ve)> <field> <text>\n"
            + "\t<field> = '/desc', '/by', '/from', '/to'\n"
            + "\tExample_1: update 4 /desc bake a cake\n"
            + "\tExample_2: update 3 /to 2023-02-14";

    /**
     * Update constructor
     *
     * @param index position in the task list
     * @param field String field to be updated
     * @param newText String text to input
     * @param taskList list of tasks
     */
    public Update(int index, String field, String newText, List<Task> taskList) {
        this.isForRecording = false;
        this.isForUpdateList = true;
        this.description = updateTask(index, field, newText, taskList);
    }

    /**
     * Returns a string message to user if the update is a success or failure
     *
     * @param index position in the task list
     * @param field String field to be updated
     * @param newText String text to input
     * @param taskList list of tasks
     * @return string message
     */
    private String updateTask(int index, String field, String newText, List <Task> taskList) {
        try {
            Task task = taskList.get(index - 1);

            switch (field.trim()) {
            case "/desc":
                task.setDescription(newText);
                break;
            case "/by":
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    deadline.setBy(newText);
                    break;
                } else {
                    return "Task " + index + " does not contain a \"by\"";
                }
            case "/from":
                if (task instanceof Event) {
                    Event event = (Event) task;
                    event.setFrom(newText);
                    break;
                } else {
                    return "Task " + index + " does not contain a \"from\"";
                }
            case "/to":
                if (task instanceof Event) {
                    Event event = (Event) task;
                    event.setTo(newText);
                    break;
                } else {
                    return "Task " + index + " does not contain a \"to\"";
                }
            default:
                return "[" + field + "]" + " is not an existing field.";
            }
            return "Task " + index + " updated to: " + taskList.get(index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            return "Indicate a number within the list: " + taskList.size();
        }
    }

    @Override
    public String toString() {
        return description;
    }

}
