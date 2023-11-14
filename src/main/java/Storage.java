import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    private File directory;
    private String filePath;

    // constructor
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public String getPath() {
        return filePath;
    }

    // methods
    public ArrayList<Task> readFromFile() {
        ArrayList<Task> contents = new ArrayList<>();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Scanner s;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] input = line.split(" \\| ");

            if (input.length < 2) {
                continue;
            }

            String task = input[0];
            boolean isDone = input[1].equals("1");

            switch (task) {
                case "T": {
                    Todo todo = new Todo(input[2]);
                    if (isDone) {
                        todo.setDone();
                    }
                    contents.add(todo);
                    break;
                }
                case "D": {
                    Deadline deadline = new Deadline(input[2], input[3]);
                    if (isDone) {
                        deadline.setDone();
                    }
                    contents.add(deadline);
                    break;
                }
                case "E": {
                    Event event = new Event(input[2], input[3], input[4]);
                    if (isDone) {
                        event.setDone();
                    }
                    contents.add(event);
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + task);
            }
        }

        return contents;
    }

    public void saveToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(file);
        try {
            StringBuilder tasks = new StringBuilder();
            for (Task task : taskList) {
                tasks.append(task.toSave()).append("\n");
            }
            fw.write(tasks + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fw.close();
    }

    public void appendToFile(String line) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(line + "\n");
        fw.close();
    }
}
