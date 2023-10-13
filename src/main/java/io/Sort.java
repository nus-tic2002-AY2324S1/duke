package io;

import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class will sort the list of tasks.
 * type/t: sort by task type.
 * type1/t1: sort by task type in reverse order.
 * date/d: sort by date.
 */
public class Sort {
    /**
     * This method will sort the list of tasks.
     *
     * @param input The input from the user.
     * @param tasks The list of tasks.
     */
    public void sort(String input, List<Task> tasks) {
        if (input.equals("type") || input.equals("t")) {
            sortByType(tasks);
            return;
        }
        if (input.equals("type1") || input.equals("t1")) {
            sortByType1(tasks);
            return;
        }
        sortByDate(tasks);
    }

    private static void sortByDate(List<Task> tasks) {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                LocalDateTime task1Time = null;
                LocalDateTime task2Time = null;
                if (task1 instanceof Deadline) {
                    task1Time = ((Deadline) task1).getTime();
                } else if (task1 instanceof Event) {
                    task1Time = ((Event) task1).getTime();
                }
                if (task2 instanceof Deadline) {
                    task2Time = ((Deadline) task2).getTime();
                } else if (task2 instanceof Event) {
                    task2Time = ((Event) task2).getTime();
                }
                if (task1Time == null) {
                    return 1;
                }
                if (task2Time == null) {
                    return -1;
                }
                return task1Time.compareTo(task2Time);
            }
        });
    }

    private static void sortByType1(List<Task> tasks) {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task2.toString().compareTo(task1.toString());
            }
        });
    }

    private static void sortByType(List<Task> tasks) {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task1.toString().compareTo(task2.toString());
            }
        });
    }
}




