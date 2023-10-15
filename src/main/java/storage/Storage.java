package storage;

import common.Messages;
import tasks.Task;
import exceptions.AmebotException;

import java.io.*;
import java.util.ArrayList;

public class Storage {
//    public void loadTasks() {
//        String filePathname = "./data/amebot.txt";
//        File tasksFile = new File(filePathname);
//
//        if (findFile(tasksFile)) {
//            ArrayList<Task> lines = new ArrayList<>();
//            try {
//                FileReader tasksFileReader = new FileReader(tasksFile);
//                BufferedReader bufferedReader = new BufferedReader(tasksFileReader);
//                String line;
//                do {
//                    line = bufferedReader.readLine().sp;
//                    lines.add(line);
//                } while (line != null);
//            } catch (IOException err) {
//            }
//        } else {
//            if(tasksFile.createNewFile()) {
//
//            }
//        }
//    }
//
//    public ArrayList<String> getTasks(String filePathname) {
//
//
//        if (findFile(taskListFile) && isValidFileExtension(filePathname)) {
//
//        }
//        return new ArrayList<>();
//    }
//
//    public boolean findFile(File taskListFile) {
//        boolean isFileFound = false;
//
//        if (taskListFile.exists()) {
//            isFileFound = true;
//        } else {
//            System.out.println(Messages.FILE_NOT_FOUND);
//        }
//
//        return isFileFound;
//    }
//
//    public boolean isFileEmpty(File file) {
//        boolean isFileEmpty = false;
//
//        if (file.length() == 0) {
//            isFileEmpty = true;
//            System.out.println((Messages.EMPTY_FILE));
//        }
//
//        return isFileEmpty;
//    }
//
//    public ArrayList<String> getFileContents(String filePathname) {
//        ArrayList<String> commandLines = new ArrayList<>();
//        File inputFile = new File(filePathname);
//        String absolutePathname = inputFile.getAbsolutePath();
//
//        if (!isFileEmpty(inputFile)) {
//            commandLines = extractFileContent(absolutePathname);
//        } else {
//            commandLines.add(0, "bye");
//        }
//
//        return commandLines;
//    }
//
//    public ArrayList<String> extractFileContent(String absolutePathname) {
//        ArrayList<String> commandLines = new ArrayList<>();
//
//        try {
//            FileReader readFile = new FileReader(absolutePathname);
//            BufferedReader bufferRead = new BufferedReader(readFile);
//            String commandLine = "";
//
//            while ((commandLine = bufferRead.readLine()) != null) {
//                commandLines.add(commandLine);
//            }
//
//            bufferRead.close();
//            readFile.close();
//        } catch (IOException err) {
//            System.out.println((Messages.ERROR_MESSAGE));
//        }
//
//        return commandLines;
//    }

    //    public void saveTasks(ArrayList<String> lines) throws IOException {
//        File file = new File(filePath);
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            for (String line : lines) {
//                writer.write(line);
//                writer.newLine();
//            }
//        }
//    }
//
//    public ArrayList<Task> loadTasks() throws AmebotException {
//        String filePath = "./data/task_list.txt";
//        ArrayList<Task> tasks = new ArrayList<>();
//        File tasksFile = new File(filePath);
//
//        if (tasksFile.exists()) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(tasksFile))) {
//                String line;
//
//                while ((line = reader.readLine()) != null) {
//                    Task task = getTasks(line);
//                    tasks.add(task);
//                }
//            } catch (AmebotException | IOException err) {
//            }
//        }
//
//        return tasks;
//    }
}
