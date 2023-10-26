package io;

import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskStorageTest {

    @Test void save() {

        File f = new File("./data/craby.txt");
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("test", "12/12/2020 1200"));
        TaskStorage taskStorage = new TaskStorage("craby.txt");
        taskStorage.save(tasks);
        assertTrue(f.exists());
        assertTrue(f.length() > 0);
        assertNotEquals(0, f.length());
        f.delete();
    }

    @Test void load() {

        File f = new File("./data/craby.txt");
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("test", "12/12/2020 1200"));
        TaskStorage taskStorage = new TaskStorage("craby.txt");
        taskStorage.save(tasks);
        List<Task> tasks1 = taskStorage.load();
        assertEquals(1, tasks1.size());
    }
}