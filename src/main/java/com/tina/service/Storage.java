package com.tina.service;

import com.tina.exception.ExistedFileException;
import com.tina.exception.InvalidFileFormatException;
import com.tina.exception.InvalidFilePathException;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * The Storage class.
 * Load task list from a local file.
 * Save task list to a local file.
 */
public class Storage {

    private final Path path;
    private Path archivePath;

    /**
     * Instantiates a new Storage.
     *
     * @param path        the path of the local file
     * @param archivePath
     */
    public Storage(Path path, Path archivePath) {
        this.path = path;
        this.archivePath = archivePath;
    }

    /**
     * Load task list from a local file.
     * Throw exception if file path or file format is invalid.
     *
     * @return the array list
     * @throws InvalidFileFormatException the invalid file format exception
     * @throws InvalidFilePathException   the invalid file path exception
     */
    public ArrayList<Task> load() throws InvalidFileFormatException, InvalidFilePathException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line = br.readLine();
            while (line != null) {
                Task task = Parser.parseStorageToTask(line);
                tasks.add(task);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new InvalidFilePathException(path);
        }

        return tasks;
    }

    /**
     * Save task list to a local file.
     * Throw exception if the file path is invalid.
     *
     * @param taskList the task list
     * @throws InvalidFilePathException the invalid file path exception
     */
    public void save(TaskList taskList) throws InvalidFilePathException {
        ArrayList<String> tasks = Parser.parseTasksToStorage(taskList);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()))) {
            for (String line : tasks) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new InvalidFilePathException(path);
        }
    }

    public void archive(TaskList taskList, String fileName) throws InvalidFilePathException, com.tina.exception.IOException, ExistedFileException {
        ArrayList<String> tasks = Parser.parseTasksToStorage(taskList);
        try {
            archivePath = archivePath.resolve(fileName);
            Files.createFile(archivePath);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivePath.toFile()))) {
                for (String line : tasks) {
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException e) {
                throw new IOException();
            }
        } catch (InvalidPathException e) {
            throw new InvalidFilePathException(fileName);
        } catch (FileAlreadyExistsException e) {
            throw new ExistedFileException(fileName);
        } catch (IOException e) {
            throw new com.tina.exception.IOException();
        }
    }
}
