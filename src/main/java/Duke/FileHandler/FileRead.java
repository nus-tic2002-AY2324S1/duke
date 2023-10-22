package Duke.FileHandler;

import Duke.DukeExceptions.InvalidNumberFormatException;
import Duke.Parser.Parser;
import Duke.Task.*;
import Duke.UserInterface.UserInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents a `FileRead` class for reading and loading saved tasks from Duke's data file.
 */
public class FileRead extends FileHandler {

    /**
     * Reads and loads saved tasks from a file into the task list.
     *
     * @param taskList The list of tasks to load the saved tasks into.
     */
    public void getSavedTask(List<Task> taskList) {
        try {
            // Create a FileReader to open the file
            FileReader fileReader = new FileReader(filePath);

            // Create a BufferedReader to read the file efficiently
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String inputs;
            // Read each line from the file until the end is reached
            while ((inputs = bufferedReader.readLine()) != null) {
                String[] input = inputs.replaceAll(" ", "").split("\\|");
                if (input.length <= 2 || input.length > 5) {
                    throw new FileCorruptedException();
                }
                String taskType = input[0];
                boolean isCompleted;
                try {
                    int temp = Parser.parseInteger(input[1]);
                    isCompleted = temp == 1;
                } catch (NumberFormatException | InvalidNumberFormatException e) {
                    throw new FileCorruptedException();
                }

                String taskName = input[2];
                Task task;


                switch (taskType) {
                    case "T":
                        if (input.length != 3) {
                            throw new FileCorruptedException();
                        }
                        task = new TodoTask(taskName, isCompleted);
                        break;
                    case "D":
                        if (input.length != 4) {
                            throw new FileCorruptedException();
                        }
                        try {
                            LocalDateTime taskDueDate = Parser.parseDateTime(input[3]);
                            task = new DeadlineTask(taskName, isCompleted, taskDueDate);
                        } catch (DateTimeParseException e) {
                            throw new FileCorruptedException();
                        }
                        break;
                    case "E":
                        if (input.length != 5) {
                            throw new FileCorruptedException();
                        }
                        try {
                            LocalDateTime taskFrom = Parser.parseDateTime(input[3]);
                            LocalDateTime taskTo = Parser.parseDateTime(input[4]);
                            task = new EventTask(taskName, isCompleted, taskFrom, taskTo);
                        } catch (DateTimeParseException e) {
                            throw new FileCorruptedException();
                        }
                        break;
                    default:
                        throw new FileCorruptedException();
                }
                taskList.add(task);

            }
            // Close the BufferedReader and FileReader to release system resources
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found, proceed to start.");
        } catch (FileCorruptedException e) {
            System.out.printf("%s\n%s\n", e.getMessage(), UserInterface.MessageDisplay.LINE_BREAK);
        } catch (IOException e) {
            e.printStackTrace(); // Handle any exceptions that may occur
        }
    }
}
