import java.io.IOException;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;




public class Storage {
    private final String FILEPATH = "src\\main\\java\\DataStorage.txt";

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




    public void save(ListTask list) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
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
        boolean marker = words[2].equals("1");

        int dividerPosition=0;
        switch (words[0]) {
            case "T":
                Selector = new Todo(temp.substring(8));
                break;
            case "D":
                dividerPosition = temp.indexOf("| ",9);
                Selector = new Deadline(temp.substring(8,dividerPosition) , Parser.constructDateTime(temp.substring(dividerPosition+2)));
                break;
            case "E":
                dividerPosition = temp.indexOf("| ",9);
                String event = temp.substring(8,dividerPosition);
                temp = temp.substring(dividerPosition+2);
                dividerPosition = temp.indexOf("| ");
                LocalDateTime from = Parser.constructDateTime(temp.substring(0,dividerPosition));
                LocalDateTime to = Parser.constructDateTime(temp.substring(dividerPosition+2));
                        Selector = new Event(event , from , to);
                break;
        }
        assert Selector != null;
        Selector.mark(marker);
        return Selector;
    }
}

