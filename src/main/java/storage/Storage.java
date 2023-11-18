package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Priority;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import tasks.ToDoWithinPeriod;

public class Storage {
    private String filePath;
    private File file, directory;
    private static final String TRUE_STRING = "true";

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);

        int endIndex = filePath.lastIndexOf("/");

        String directoryPath = filePath.substring(0, endIndex + 1);
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
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(file);
            int counter = 0;
            Task t;

            while (s.hasNext()) {
                String string = s.nextLine();
                String[] line = string.split(RegExp.LINE_DELIMITER);

                switch (line[0].toUpperCase()) {
                case "E":
                    // doesnt add invalid task
                    if (!Pattern.matches(RegExp.FILE_STORAGE_EVENT_FORMAT, string)) {
                        continue;
                    }
                    t = new Event(line[3], LocalDate.parse(line[4]), LocalDate.parse(line[5]));
                    tasks.add(t);
                    break;

                case "D":
                    // doesnt add invalid task
                    if (!Pattern.matches(RegExp.FILE_STORAGE_DEADLINE_FORMAT, string)) {
                        continue;
                    }
                    t = new Deadline(line[3], LocalDate.parse(line[4]));
                    tasks.add(t);
                    break;

                case "T":
                    // doesnt add invalid task
                    if (Pattern.matches(RegExp.FILE_STORAGE_TODO_WITHIN_PERIOD_FORMAT, string)) {
                        t = new ToDoWithinPeriod(line[3], LocalDate.parse(line[4]), LocalDate.parse(line[5]));
                        tasks.add(t);
                        break;
                    }
                    if (!Pattern.matches(RegExp.FILE_STORAGE_TODO_FORMAT, string)) {
                        continue;
                    }
                    t = new ToDo(line[3]);
                    tasks.add(t);
                    break;

                default:
                    continue;
                }

                tasks.get(counter).setTaskPriority(Priority.valueOf(line[1].toUpperCase()));

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
    public void storeData(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            int totalTasks = tasks.getTotalTasks();
            ArrayList<Task> taskList = tasks.getTaskList();
            for (int i = 0; i < totalTasks; i++) {
                String textToAdd = taskList.get(i).writeToFile();
                fw.write(textToAdd + RegExp.NEW_LINE);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(ErrorMessages.ERROR_STORING_TO_FILE);
        }
    }


}
