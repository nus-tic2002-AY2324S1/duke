package io;

import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDateTime;

import java.util.Comparator;
import java.util.List;

public class Sort {
    /**
     * Returns the sorted list of tasks according to the input.
     * @param input The input from the user.
     * @param tasks The list of tasks.
     */
    public void sort(String input, List<Task> tasks) {
        if (input.equals("type") || input.equals("t")) {
            sortByType(tasks);
        } else if (input.equals("type-r") || input.equals("tr")) {
            sortByTypeRevert(tasks);
        } else {
            sortByDate(tasks);
        }
    }

    private static void sortByType(List<Task> tasks) {
        System.out.println("   Sorting by type...");
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task1.toString().compareTo(task2.toString());
            }
        });
    }

    private static void sortByTypeRevert(List<Task> tasks) {
        System.out.println("   Sorting by type revert order...");
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task2.toString().compareTo(task1.toString());
            }
        });
    }

    private static void sortByDate(List<Task> tasks) {
        System.out.println("   Sorting by date...");
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                LocalDateTime task1Time = null;
                LocalDateTime task2Time = null;
                if (task1 instanceof Deadline) {
                    task1Time = ((Deadline) task1).getTime();
                } else if (task1 instanceof Event) {
                    task1Time = ((Event) task1).getFromTime();
                }
                if (task2 instanceof Deadline) {
                    task2Time = ((Deadline) task2).getTime();
                } else if (task2 instanceof Event) {
                    task2Time = ((Event) task2).getFromTime();
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
}