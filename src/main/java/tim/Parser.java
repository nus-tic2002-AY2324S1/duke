package tim;

import java.util.ArrayList;
import java.util.Scanner;


public class Parser {
     static void inputParser(ArrayList<Task> list){
        String input = "init" ;
        int index = -1;
        Scanner in = new Scanner(System.in);
        while(!input.equalsIgnoreCase("bye")) {
            input = in.nextLine();
            String[] token = input.split(" ", 2);
            String taskName;
            String mode = token[0].toLowerCase();
            System.out.println("|| " + mode + " ||");
            switch (mode) {
                case "bye":
                    Messenger.goodbyeGreet();
                    break;
                case "list":
                    Messenger.printList(list);
                    break;
                case "date":
                    Messenger.printDate();
                    break;
                case "mark":
                    try {
                        index = Integer.parseInt(token[1]);
                        ListManager.markUnmarkTask(index, true, list);
                    } catch (ArrayIndexOutOfBoundsException AIO) {
                        System.err.println("incorrect input for index!");
                    }
                    break;
                case "unmark":
                    try {
                        index = Integer.parseInt(token[1]);
                        ListManager.markUnmarkTask(index, false, list);
                    } catch (ArrayIndexOutOfBoundsException AIO) {
                        System.err.println("incorrect input for index!");
                    }
                    break;
                case "todo":
                    try {
                        taskName = token[1];
                        ListManager.addToDo(taskName, list);
                    } catch (ArrayIndexOutOfBoundsException AIO) {
                        System.err.println("what is the name of the task to complete?");
                    }
                    break;
                case "deadline":
                    try {

                        taskName = token[1];
                        ListManager.addDeadline(taskName, list);
                    } catch (ArrayIndexOutOfBoundsException AIO) {
                        System.err.println("what is the name of the deadline agenda?");
                    }
                    break;
                case "event":
                    try {
                        taskName = token[1];
                        ListManager.addEvent(taskName, list);
                    } catch (ArrayIndexOutOfBoundsException AIO) {
                        System.err.println("what is the name of the event?");
                    }
                    break;
                case "delete":
                    try {
                        int deleteIndex = Integer.parseInt(token[1]);
                        ListManager.deleteFromList(deleteIndex, list);
                    } catch (Exception e) {
                        System.err.println("please include valid index of task to delete");
                    }
                    break;
                default:
                    System.err.println("I've not learn to do this yet!!");
            }
            if (!(mode.equals("list") || mode.equals("date") || mode.equals("bye"))) {
                FileManager.saveList(list);
            }
        }
    }
}
