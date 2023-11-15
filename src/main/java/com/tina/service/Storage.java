package com.tina.service;

import com.tina.exception.ExistedFileException;
import com.tina.exception.InvalidFileFormatException;
import com.tina.exception.InvalidFilePathException;
import com.tina.task.Task;
import com.tina.task.TaskList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents a Storage class.
 * Loads task list from a local file.
 * Saves task list to a local file.
 */
public class Storage {

    private final Path path;
    private final Path archivePath;

    /**
     * Instantiates a new Storage.
     *
     * @param path        the path of the local file.
     * @param archivePath the archive path
     */
    public Storage(Path path, Path archivePath) {
        this.path = path;
        this.archivePath = archivePath;
    }

    /**
     * Loads task list from a local file.
     * Throws exception if file path or file format is invalid.
     *
     * @return the array list.
     * @throws InvalidFileFormatException if file format is invalid.
     * @throws InvalidFilePathException   if file path is invalid.
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
     * Saves task list to a local file.
     * Throws exception if the file path is invalid.
     *
     * @param taskList the task list.
     * @throws InvalidFilePathException if the file path is invalid.
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

    /**
     * Archives the current task list.
     * Saves the file as given file name.
     * Throws error if the file is already existed.
     *
     * @param taskList the task list.
     * @param fileName the file name.
     * @throws InvalidFilePathException if the file path is invalid.
     * @throws IOException              if any IO error occurs.
     * @throws ExistedFileException     if the file is already existed.
     */
    public void archive(TaskList taskList, String fileName)
            throws InvalidFilePathException, com.tina.exception.IOException, ExistedFileException {
        ArrayList<String> tasks = Parser.parseTasksToStorage(taskList);
        try {
            Path archiveFile = archivePath.resolve(fileName);
            Files.createFile(archiveFile);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archiveFile.toFile()))) {
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

    /**
     * Gets archive path.
     *
     * @return the archive path.
     */
    public Path getArchivePath() {
        return archivePath;
    }
}
