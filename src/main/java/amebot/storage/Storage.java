package amebot.storage;

import amebot.commands.Command;
import amebot.common.Messages;
import amebot.parser.StorageParser;
import amebot.tasks.Task;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final String filePathname = "./amebot/data/amebot.txt";
    private File tasksFile = new File(filePathname);

    public void loadTasks(Command command) throws IOException {
        if (findTasksFile()) {
            ArrayList<String> tasksList = getTasksFileData();
            storeTasks(command, tasksList);
        } else {
            createNewTasksFile();
        }
    }

    public void createNewTasksFile() throws IOException {
        if (tasksFile.createNewFile()) {
            System.out.println(Messages.FILE_CREATED);
        }
    }

    public boolean findTasksFile() {
        boolean isFileFound = false;

        if (tasksFile.exists()) {
            isFileFound = true;
        } else {
            System.out.println(Messages.FILE_NOT_FOUND);
        }

        return isFileFound;
    }

    public ArrayList<String> getTasksFileData() {
        ArrayList<String> tasksList = new ArrayList<>();

        if (!isFileEmpty()) {
            try {
                FileReader tasksFileReader = new FileReader(tasksFile);
                BufferedReader bufferedReader = new BufferedReader(tasksFileReader);
                String task = "";

                while ((task = bufferedReader.readLine()) != null) {
                    tasksList.add(task);
                }

                bufferedReader.close();
                tasksFileReader.close();
            } catch (IOException err) {
                System.out.println((Messages.ERROR_MESSAGE));
            }
        }

        return tasksList;
    }

    public boolean isFileEmpty() {
        boolean isFileEmpty = false;

        if (tasksFile.length() == 0) {
            isFileEmpty = true;
            System.out.println((Messages.EMPTY_FILE));
        }

        return isFileEmpty;
    }

    public void storeTasks(Command command, ArrayList<String> tasksList) {
        for (String task : tasksList) {
            ArrayList<String> parsedTask = new StorageParser().parseLoadTask(task);
            command.executeLoadTaskCommand(parsedTask);
        }

        System.out.println(Messages.SUCCESS_LOAD_TASK);
    }

    public void saveTasks() {
        ArrayList<Task> tasksList = Command.getTasks();
        String item = "";

        try {
            FileWriter tasksFileWriter = new FileWriter(tasksFile);
            BufferedWriter bufferedWriter = new BufferedWriter(tasksFileWriter);

            for (Task task : tasksList) {
                item = new StorageParser().parseSaveTask(task);

                bufferedWriter.write(item);
                bufferedWriter.newLine();
            }

            System.out.println(Messages.SUCCESS_SAVE_TASK);

            bufferedWriter.close();
            tasksFileWriter.close();
        } catch (IOException err) {
            System.out.println((Messages.ERROR_MESSAGE));
        }
    }
}
