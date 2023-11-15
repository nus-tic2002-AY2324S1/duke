package duke.storage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

import duke.tasks.Task;
import duke.constants.RegExp;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.tasks.Priority;

public class Storage {
    // default values for storage
    private String filePath, directoryPath;
    private File file, directory;
    private FileWriter fw;
    private ArrayList<Task> tasks;
    protected static final int EVENT_STORAGE_REQUIRED_WORDS = 6;
    protected static final int DEADLINE_STORAGE_REQUIRED_WORDS = 5;
    protected static final int TODO_STORAGE_REQUIRED_WORDS = 4;
    protected static final String TRUE_STRING = "true";


    public Storage(String filePath) {
        this.tasks = new ArrayList<Task>();

        this.filePath = filePath;
        this.file = new File(filePath);

        int endIndex = filePath.lastIndexOf("/");

        this.directoryPath = filePath.substring(0, endIndex + 1);
        directory = new File(directoryPath);
    }

    /**
     * Reads the contents from the object's {@code filePath}, validates the data format, and creates
     * Task objects.
     * <p>
     * This method checks if the data is in the expected format and that the date follows the specified
     * format before creating Task objects and adding them to the ArrayList of tasks.
     * <p>
     * If the file path is not present in the current workspace, this method will create a new file. It
     * will also create the necessary directory if it is not already present. If file creation fails, an
     * IOException is thrown.
     *
     * @return An {@code ArrayList<Task>} containing all the tasks stored in the specified file path.
     * @throws IOException If file creation fails.
     */
    public ArrayList<Task> loadData() throws IOException {
        try {
            Scanner s = new Scanner(file);
            int counter = 0;
            Task t;

            while (s.hasNext()) {
                String[] line = s.nextLine().split(RegExp.LINE_DELIMITER);

                switch (line[0]) {
                case "E":
                    // doesnt add invalid task
                    if (line.length != EVENT_STORAGE_REQUIRED_WORDS) {
                        continue;
                    }
                    if (!Pattern.matches(RegExp.DATE_REGEX, line[4])) {
                        continue;
                    }
                    if (!Pattern.matches(RegExp.DATE_REGEX, line[5])) {
                        continue;
                    }
                    t = new Event(line[3], LocalDate.parse(line[4]), LocalDate.parse(line[5]));
                    tasks.add(t);
                    break;

                case "D":
                    if (line.length != DEADLINE_STORAGE_REQUIRED_WORDS) {
                        continue;
                    }
                    if (!Pattern.matches(RegExp.DATE_REGEX, line[4])) {
                        continue;
                    }
                    t = new Deadline(line[3], LocalDate.parse(line[4]));
                    tasks.add(t);
                    break;

                case "T":
                    // doesnt add invalid task
                    if (line.length != TODO_STORAGE_REQUIRED_WORDS) {
                        continue;
                    }

                    t = new ToDo(line[3]);
                    tasks.add(t);
                    break;

                default:
                    continue;
                }

                tasks.get(counter).setTaskPriority(Priority.valueOf(line[1]));

                if (line[2].equals(TRUE_STRING)) {
                    t = tasks.get(counter);
                    t.setDone();
                }
                counter++;
            }

            s.close();
        } catch (FileNotFoundException e) {
            if (!directory.exists()) {
                directory.mkdir();
            } 
    
            file.createNewFile();
        }

        return tasks;

    }

    /**
     * Stores the user's {@code tasks} in the objects' {@code filePath}.
     * <p>
     * This method saves the user's tasks to the file path specified by the {@code filePath} attribute
     * of this object.
     * </p>
     * 
     * @param tasks The users current TaskList object.
     */
    public void storeData(TaskList tasks) {
        try {
            fw = new FileWriter(filePath);
            int totalTasks = tasks.getTotalTasks();
            ArrayList<Task> taskList = tasks.getTaskList();
            for (int i = 0; i < totalTasks; i++) {
                String textToAdd = taskList.get(i).writeToFile();
                fw.write(textToAdd + RegExp.NEW_LINE);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error Writing to File!");
        }
    }


}
