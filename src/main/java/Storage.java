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

    // the methods below are adapted from
    // https://github.com/wamps-jp/ip/blob/master/src/main/java/duke/Storage.java

    /**
     * Allows Duke to read from a text file, i.e. ./duke.txt
     * and populate the Task list.
     * <p></p>
     * If the file does not yet exist, a file of the
     * same name will be created.
     * @return an ArrayList of type Task
     * @throws RuntimeException if file does not exist
     */
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

            assert input.length > 2 : "empty line";
//            if (input.length < 2) {
//                continue;
//            }

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

    /**
     * Allows Duke to save the list of created Tasks
     * to a pre-determined location, i.e. ./duke.txt
     * <p></p>
     * The naming follows this format:
     * <br>
     * "T/D/E" | "0/1" | "description" | "by/from" | "to"
     * @param taskList an ArrayList of type Task
     * @throws IOException if the file does not exist
     */
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

    /**
     * Allows Duke to append text to
     * a pre-determined location, i.e. ./duke.txt
     * <p></p>
     * The naming follows this format:
     * <br>
     * "T/D/E" | "0/1" | "description" | "by/from" | "to"
     * @param taskList an ArrayList of type Task
     * @param index the index number of the Task
     * @throws IOException if the file does not exist
     */
    public void appendToFile(ArrayList<Task> taskList, int index) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        try {
            fw.write(taskList.get(index).toSave());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fw.close();
    }
}
