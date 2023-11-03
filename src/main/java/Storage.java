import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Storage {
    private final String FILEPATH = "C:\\Users\\holy_\\IdeaProjects\\duke\\src\\main\\java\\DataStorage.txt";

    public Storage() {

    }


    public ListTask load() throws FileNotFoundException {
        File file = new File(FILEPATH);
        Scanner scanner = new Scanner(file);
        ListTask list = new ListTask();

        while (scanner.hasNext()) {
            Task task = decodeTaskFromStorage(scanner.nextLine());
            list.add(task);
        }

        return list;
    }




    public void save(ListTask tasklist) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        for (int i = 0; i < tasklist.size(); i++) {
            Task task = tasklist.get(i);
            String encodedTask = encodeTaskForStorage(task);
            fw.write(encodedTask);
        }
        fw.close();
    }

    private String encodeTaskForStorage(Task task) {
        final StringBuilder encodedTaskBuilder = new StringBuilder();
        String taskType = encodeTaskType(task);

        encodedTaskBuilder.append(taskType).append(" | ");
        encodedTaskBuilder.append(task.getIsDone() ? "1" : "0").append(" | ");
        encodedTaskBuilder.append(task.getDescription());

        if (taskType.equals("D")) {
            Deadline deadline = (Deadline) task;
            encodedTaskBuilder.append(" | ").append(deadline.getBy());
        }
        else if (taskType.equals("E")) {
            Event event = (Event) task;
            encodedTaskBuilder.append(" | ").append(event.getFrom()).append(" | ").append(event.getTo());
        }

        encodedTaskBuilder.append("\n");

        return encodedTaskBuilder.toString();
    }

    private String encodeTaskType(Task task) {
        String taskType = "";
        if (task.getClass().equals(Todo.class)) {
            taskType = "T";
        }
        else if (task.getClass().equals(Deadline.class)) {
            taskType = "D";
        }
        else if (task.getClass().equals(Event.class)) {
            taskType = "E";
        }
        return taskType;
    }
    private Task decodeTaskFromStorage(String line){
        String[] words = line.split(" ");
        Task Selector = null;
        String temp=line;
        int dividerPosition=0;
        switch (words[0]) {
            case "T":
                Selector = new Todo(temp.substring(8));
                break;
            case "D":
                dividerPosition = temp.indexOf("| ",9);
                Selector = new Deadline(temp.substring(8,dividerPosition) , temp.substring(dividerPosition+2));
                break;
            case "E":
                dividerPosition = temp.indexOf("| ",9);
                String event = temp.substring(8,dividerPosition);
                temp = temp.substring(dividerPosition+2);
                dividerPosition = temp.indexOf("| ");
                String from = temp.substring(0,dividerPosition);
                String to = temp.substring(dividerPosition+2);
                        Selector = new Event(event , from , to);
                break;
        }
        return Selector;
    }
}

