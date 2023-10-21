package amebot.parser;

import amebot.enumerations.Keyword;
import amebot.tasks.Task;

import java.util.ArrayList;

public class StorageParser extends Parser {
    public ArrayList<String> parseLoadTask(String task) {
        ArrayList<String> item = new ArrayList<>();
        String[] words = task.toLowerCase().split("\\|");

        String type = words[0].replaceAll("\\[", "").replaceAll("]", "");
        Keyword commandType = Keyword.valueOf(type.trim().toUpperCase());
        item.add(type);

        boolean isSeleted = words[1].contains("✓");
        item.add(String.valueOf(isSeleted));

        String description = words[2];
        item.add(description);

        switch (commandType) {
            case EVENT:
                String fromDateTime = words[3];
                String toDateTime = words[4];
                item.add(fromDateTime);
                item.add(toDateTime);
                break;
            case DEADLINE:
                String dueDateTIme = words[3];
                item.add(dueDateTIme);
                break;
        }

        return item;
    }

    public String parseSaveTask(Task task) {
        String item = "";

        String type = task.getType().replaceAll("\\[", "").replaceAll("]", "");
        Keyword commandType = Keyword.valueOf(type.trim().toUpperCase());

        String status = task.getStatus();
        String description = task.getDescription();

        switch (commandType) {
            case TODO:
                item = type + "|" + status + "|" + description;
                break;
            case EVENT:
                String fromDateTime = task.getFromDateTime();
                String toDateTime = task.getToDateTime();
                item = type + "|" + status + "|" + description + "|" + fromDateTime + "|" + toDateTime;
                break;
            case DEADLINE:
                String dueDateTIme = task.getDueDateTime();
                item = type + "|" + status + "|" + description + "|" + dueDateTIme;
                break;
        }

        return item;
    }
}
