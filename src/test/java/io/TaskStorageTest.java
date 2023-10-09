package io;

import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskStorageTest {

    @Test
    void save() {
        /*generate code to test save function
        * 1. create a file
        * 2. create a list of tasks
        * 3. save the list of tasks
        * 4. check if the file is created
        * 5. check if the file is empty
        * 6. check if the file is not empty
        */
        File f = new File("./data/craby.txt");
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("test", "12/12/2020 1200"));
        TaskStorage taskStorage = new TaskStorage("./data/craby.txt");
        taskStorage.save(tasks);
        assertTrue(f.exists());
        assertTrue(f.length() > 0);
        assertFalse(f.length() == 0);
        // DO WE NEED TO DELETE THE FILE?
        f.delete();
    }

    @Test
    void load() {
        // generate the code to test load function
        // 1. create a file
        // 2. create a list of tasks
        // 3. save the list of tasks
        // 4. load the list of tasks
        // 5. check if the list of tasks is empty
        // 6. check if the list of tasks is not empty
        File f = new File("./data/craby.txt");
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("test", "12/12/2020 1200"));
        TaskStorage taskStorage = new TaskStorage("./data/craby.txt");
        taskStorage.save(tasks);
        List<Task> tasks1 = taskStorage.load();
        assertTrue(tasks1.size() == 1);
        f.delete();
    }
}