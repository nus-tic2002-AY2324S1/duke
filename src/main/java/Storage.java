import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String FILE_PATH = "./data/duke.txt";

    public List<Task> loadTasks() {
        List<Task> list = new ArrayList<>();
        File file = new File(FILE_PATH);

        // Check if the file exists before attempting to load
        if (!file.exists()) {
            // System.out.println("No saved tasks found. Starting with an empty task list.");
            return list;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Parser.createTaskFromLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void saveTasks(List<Task> list) {
        try {
            // Ensure the directory exists
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Now, save the tasks to the file
            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                for (Task task : list) {
                    writer.write(task.toFileString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
