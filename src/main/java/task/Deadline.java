package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        time = time.trim();
        String[] isTime = time.split(" ");
        if (isTime.length > 1) {
            handleDateTime(time);
        }else{
            handleDate(time);
        }
    }

    private void handleDate(String time) {
        try {
            SimpleDateFormat inputFormat;
            DateTimeFormatter formatter;
            if (time.contains("/")) {
                String[] checkFormat = time.split("/");
                if (checkFormat[0].length() <= 2) {
                    inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                } else {
                    inputFormat = new SimpleDateFormat("yyyy/MM/dd");
                    formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
                }
            } else {
                String[] checkFormat = time.split("-");
                if (checkFormat[0].length() > 2) {
                    inputFormat = new SimpleDateFormat("yyyy-MM-d");
                    formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                } else {
                    inputFormat = new SimpleDateFormat("d-MM-yyyy");
                    formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
                }
            }
            SimpleDateFormat outputFormat = new SimpleDateFormat("d MMM yyyy");
            Date date = inputFormat.parse(time);
            LocalDate deadlineDate = LocalDate.parse(time, formatter);
            String tmp = String.valueOf(deadlineDate.getDayOfWeek());
            String dateOfWeek = tmp.substring(0, 1) + tmp.substring(1).toLowerCase();
            this.time = outputFormat.format(date) + " - " + dateOfWeek;
        } catch (ParseException e) {
            this.time = time;
        }
    }

    private void handleDateTime(String time) {
        try {
            SimpleDateFormat inputFormat;
            DateTimeFormatter formatter;
            if (time.contains("/")) {
                String[] checkFormat = time.split("/");
                if (checkFormat[0].length() <= 2) {
                    inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                    formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                } else {
                    inputFormat = new SimpleDateFormat("yyyy/MM/dd HHmm");
                    formatter = DateTimeFormatter.ofPattern("yyyy/M/d HHmm");
                }
            } else {
                String[] checkFormat = time.split("-");
                if (checkFormat[0].length() > 2) {
                    inputFormat = new SimpleDateFormat("yyyy-MM-d HHmm");
                    formatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
                } else {
                    inputFormat = new SimpleDateFormat("d-MM-yyyy HHmm");
                    formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
                }
            }
            SimpleDateFormat outputFormat = new SimpleDateFormat("d MMM yyyy, ha");
            Date date = inputFormat.parse(time);
            LocalDateTime deadlineDate = LocalDateTime.parse(time, formatter);
            String tmp = String.valueOf(deadlineDate.getDayOfWeek());
            String dateOfWeek = tmp.substring(0, 1) + tmp.substring(1).toLowerCase();
            this.time = outputFormat.format(date) + " - " + dateOfWeek;
        } catch (ParseException e) {
            this.time = time;
        }
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + time.trim() + ")";
    }
}