package io;

import task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public void sort(String input, List<Task> tasks) {

        if (input.equals("type") || input.equals("t")) {
            Collections.sort(tasks, new Comparator<Task>() {
                @Override
                public int compare(Task task1, Task task2) {
                    return task1.toString().compareTo(task2.toString());
                }
            });
        } else if (input.equals("type1") || input.equals("t1")) {
            Collections.sort(tasks, new Comparator<Task>() {
                @Override
                public int compare(Task task1, Task task2) {
                    return task2.toString().compareTo(task1.toString());
                }
            });
        }
    }
}




