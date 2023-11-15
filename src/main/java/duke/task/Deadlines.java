package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.command.TaskType;

public class Deadlines extends Task {
    LocalDate deadline;

    public Deadlines(String instruction) {
        super("");
        String[] items = derive(instruction);
        this.description = items[0];
        this.deadline = convertDate(items[1]);
    }

    public Deadlines(String description, boolean isDone, String deadline) {
        super("", false);
        this.description = description;
        this.deadline = convertDate(deadline);
        this.isDone = isDone;
    }

    private String[] derive(String instruction) {
        String[] result = instruction.split("/");
        if (result.length != 2) {
            throw new IllegalArgumentException("Invalid format");
        } else {
            result[0] = result[0].trim();
            result[1] = result[1].replaceAll("by", "").trim();
            return result;
        }
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toFileString() {
        return getType() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
    }

    @Override
    public String getPrintStatus() {
        return "[" + (isDone ? "X" : " ") + "] " + description + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
