package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    LocalDate from;
    LocalDate to;

    public Events(String instruction) {
        super("");
        String[] items = derive(instruction);
        this.description = items[0];
        this.from = convertDate(items[1]);
        this.to = convertDate(items[2]);
    }

    public Events(String description, boolean isDone, String from, String to) {
        super("", false);
        this.description = description;
        this.from = convertDate(from);
        this.to = convertDate(to);
        this.isDone = isDone;
    }

    private String[] derive(String instruction) {
        String[] result = instruction.split("/");
        if (result.length != 3) {
            throw new IllegalArgumentException("Invalid format");
        } else {
            result[0] = result[0];
            // We are not handling if they put invalid dates
            // We simply take the 2nd and 3rd values and store it as from and to for now.
            result[1] = result[1].replaceAll("from", "").trim();
            result[2] = result[2].replaceAll("to", "").trim();
            return result;
        }
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toFileString() {
        return getType() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public String getPrintStatus() {
        DateTimeFormatter patt = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[" + (isDone ? "X" : " ") + "] " + description + " (from: " + from.format(patt) + " to:" + to.format(patt) + ")";
    }
}
