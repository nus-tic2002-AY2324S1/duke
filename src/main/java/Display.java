//This java file contains all methods for displaying specific messages

import java.util.ArrayList;

public class Display {
    //group everything into methods to be called & used in Duke.java file
    //logo
        public String logo = "__________              .__    .__ \n"
                + "\\______   \\__ __   ____ |  |__ |__|\n"
                + " |     ___/  |  \\_/ ___\\|  |  \\|  |\n"
                + " |    |   |  |  /\\  \\___|   Y  \\  |\n"
                + " |____|   |____/  \\___  >___|  /__|\n"
                + "                      \\/     \\/    \n";

    //greeting
    public void greetings(){
        System.out.println("Hello! I'm");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println("***************************************");
    }

    //bye
    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    //whenever a task is added
    public void notification(ArrayList<Task>userInputTasks, int taskNumber){
        int totalTasksCount = Task.getTaskCount();
        System.out.println("Got it. I've added this task:");
        System.out.println(userInputTasks.get(taskNumber).toString());
        System.out.println("Now you have " + totalTasksCount + " tasks in the list.");
    }

    //delete
    public void deletedMessage(Task task){
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
    }

    //list
    public void UserInputList(ArrayList<Task>userInputList){
        if(Task.getTaskCount() == 0){
            System.out.println("There is nothing in your list.");
        }
        System.out.println("List of tasks: ");
        for(int j = 0; j<Task.getTaskCount(); j++){
            System.out.println((j+1) + "." + userInputList.get(j).toString());
        }
    }

    //mark
    public void mark(ArrayList<Task>userInputTasks, int indexNumber){
        System.out.println("That's some progress! I've marked this task as done:");
        userInputTasks.get(indexNumber).taskCompleted();
    }

    //unmark
public void unmark(ArrayList<Task>userInputTasks, int indexNumber){
    System.out.println("OK, I've marked this task as not done yet:");
    userInputTasks.get(indexNumber).taskNotCompleted();
}

    //find 
    public void findMessage(){
        System.out.println("Here are the matching tasks in your list: ");
    }

public void changesMadeNotification(ArrayList<Task>userInputTasks, int indexNumber){
    System.out.println("Here is the updated task: ");
    System.out.println(userInputTasks.get(indexNumber));
    }

}

