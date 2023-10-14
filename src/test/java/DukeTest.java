///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Filename: Duke.java
//Filetype: Java Source file
//Author: Peh Qing Wen
//Created on: 13/10/2023 17:51
//Last Modified On:
//Copy Rights: me!
//Description: My own code were added after referencing from others' codes from the open source Project Code Dashboard
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package seedu.duke;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }
}

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import duke.userinterface.UserInterface;
// %n : new line

class Duke {

    private static ArrayList<Task>userInputTasks = new ArrayList<>();

    public enum taskType{
        TODO, DEADLINE, EVENT, FIXEDDURATION
    }

    private UserInterface userInterface;

    private Display display;

    //constructor Duke to initialise classes Display & UserInterface
    public Duke(){
        userInterface = new UserInterface();
        display = new Display();
    }

    //method to call start() & save last-updated list of tasks to tasks.txt
    public static void main(String[] inputs){
        Duke duke = new Duke();
        duke.start();

        try{
            PrintWriter writer = new PrintWriter(new FileWriter("tasks.txt"));
            for(int i = 0; i<userInputTasks.size(); i++){
                writer.println(i+1 + ". " + userInputTasks.get(i));
            }
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //method to start the task management application
    public void start(){
        display.greetings();

        try{
            File file = new File("C:/repos/duke/src/main/java/TestCases");
            Scanner scanner = new Scanner(file);

            //Read the test cases from the file
            while(scanner.hasNextLine()){
                String testCase = scanner.nextLine();
                //Process the test case here
                if(testCase.equals("bye")){
                    break;
                }else{
                    checkCommand(testCase);
                    System.out.println("***************************************");
                }
            }
//            scanner.close();
            userInterface.closeScanner();
        Display.goodbye();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //method to store the tasks according to taskType
    public void storeTask(taskType taskType, String arguments){
        Task task = createTask(taskType,arguments);
        if(task != null){
            userInputTasks.add(task);
            int itemIndex = Task.getTaskCount() - 1;
            display.notification(userInputTasks, itemIndex);
        }
    }

    //method to check the first word of input for further action (call other functions)
    public void checkCommand(String userInput){
        try{
            String[] inputs = userInput.split("\\s+");
            if(inputs.length == 0 || userInput.isEmpty()){
                throw new EmptyInputException();
            }

            String command = inputs[0].toLowerCase(); //front part task type
            String arguments = userInput.toLowerCase().substring(command.length()).trim(); //behind part info

            switch(command){
                case "list": {
                    display.UserInputList(userInputTasks);
                    break;
                }
                case "todo": {
                    if (arguments.isEmpty()) {
                        throw new InvalidTaskException();
                    }else{
                        storeTask(taskType.TODO, arguments);
                    }
                    break;
                }
                case "deadline": {
                    if(!arguments.contains("/by")){
                        throw new InvalidDeadlineException();
                    }else{
                        storeTask(taskType.DEADLINE, arguments);
                    }
                    break;
                }
                case "event": {
                    if(!arguments.contains("/from") || !arguments.contains("/to")){
                        throw new InvalidEventException();
                    }else{
                        storeTask(taskType.EVENT, arguments);
                    }
                    break;
                }
                case "delete": {
                    try {
                        int indexPosition = Integer.parseInt(arguments);
                        indexPosition = indexPosition - 1;
                        if (indexPosition < Task.taskCount) {
                            deleteTask(indexPosition);
                        }else{
                            throw new NumberIndexOutOfBoundsException();
                        }
                    }catch(InvalidIndexException e){
                        throw new InvalidIndexException();
                    }
                    break;
                }
                case "mark", "unmark": {
                    modifyTask(userInput);
                    break;
                }
                case "find": {
                    if(userInputTasks.isEmpty()){
                        throw new EmptyListException();
                    }else if(arguments.isEmpty()){
                        throw new InvalidKeywordException();
                    }else{
                        findTask(arguments);
                    }
                    break;
                }
                default:
                    newCheckTask(userInput);
            }
        }catch(DukeExceptions e){
            System.out.println(e.getMessage());
        }
    }

    //method to check for taskType and call other method(s) to create that taskType
private Task createTask(taskType taskType, String arguments) {
        Task task = null;
        switch(taskType){
            case TODO:
                task = new Task('T', arguments);
                break;
            case DEADLINE:
                task = createDeadlineTask(arguments);
                break;
            case EVENT:
                task = createEventTask(arguments);
                break;
            case FIXEDDURATION:
                task = createFixedDuration(arguments);
                break;
        }
        return task;
}

//method for finding specific tasks with a particular keyword (input) provided
private void findTask(String input) throws InvalidKeywordException{
        //arguments (input) should be a single word
    int count = 1;
    for (int i = 0; i<userInputTasks.size(); i++){
        String[] inputParts = input.split("\\s+");
        if(inputParts.length != 1){
            throw new InvalidKeywordException();
        }else{
            //the input is a single word but may not be THE keyword
            if(userInputTasks.get(i).getTaskName().contains(inputParts[0])){
                if(count == 1){
                    display.findMessage(); //need print ONCE only
                    count++;
                }
                System.out.print(i+1 + ".");
                System.out.println(userInputTasks.get(i));
            }
        }
    }
}

//method for creating Deadline tasks
private Task createDeadlineTask(String arguments){
        int byIndex = arguments.indexOf("/by");
        String taskName = arguments.substring(0, byIndex).trim();
        String date = arguments.substring(byIndex + 3).trim();
        String[] dateTime = date.split("\\s+");
        String dueDate = dateTime[0];
        String dueTime = dateTime[1];

        return new Deadline('D', taskName, dueDate, dueTime);
    }

    //method for creating Event tasks
private Task createEventTask(String arguments) {

    int fromIndex = arguments.indexOf("/from");
    int toIndex = arguments.indexOf("/to");
    String taskName = arguments.substring(0, fromIndex).trim();
    String from = arguments.substring(fromIndex + 5, toIndex).trim();
    String fromSet[] = from.split("\\s+");
    String fromDate = fromSet[0];
    String fromTime = fromSet[1];
    String to = arguments.substring(toIndex + 3).trim();
    String toSet[] = to.split("\\s+");
    String toDate = toSet[0];
    String toTime = toSet[1];

    return new Event('E', taskName, fromDate, fromTime, toDate, toTime);

}

//method used in conjunction with checkCommand() for checking if it is a FIXEDDURATION taskType
public void newCheckTask(String userInput) throws InvalidInputException{
        if(userInput.contains("(needs") || userInput.contains("at least") || userInput.contains("need")){
            storeTask(taskType.FIXEDDURATION, userInput);
        }else{
            throw new InvalidInputException();
        }
}

//method for creating a FIXEDDURATION taskType
private Task createFixedDuration(String userInput){
        String taskName = userInput;
        return new FixedDuration('F', taskName);
    }

    //method for checking if instructions is for mark, unmark, or delete tasks
    public void modifyTask(String userInput) throws DukeExceptions {
        int spaceIndex = userInput.indexOf(' ');
        String integerPart = userInput.substring(spaceIndex + 1);
        String commandBeforeSpace = userInput.substring(0, spaceIndex).toLowerCase();

        try {
            int itemIndex = Integer.parseInt(integerPart) - 1;
            if (itemIndex < 0 || itemIndex >= Task.getTaskCount()) {
                // Handle exception case where the item index is out of bounds or does not exist
                throw new NumberIndexOutOfBoundsException();
            }

            if (commandBeforeSpace.equals("mark")) {
                markAsComplete(itemIndex);
            } else if (commandBeforeSpace.equals("unmark")) {
                markAsIncomplete(itemIndex);
            } else if (commandBeforeSpace.equals("delete")) {
                deleteTask(itemIndex);
            } else {
                // Handle exception case where the command is neither mark nor unmark
                throw new InvalidInputException();
            }
        } catch (InvalidInputException e) {
            //integer portion is not a valid number
            System.out.printf(e.getMessage()+"\n");
        } catch (NumberIndexOutOfBoundsException e) {
            throw new NumberIndexOutOfBoundsException();
        }
    }

//method for marking tasks as complete
    public void markAsComplete(int indexPosition){
        if (userInputTasks.get(indexPosition).getTaskCompletion() == true) {
            display.alreadyCompleted(userInputTasks.get(indexPosition).getTaskName());
        } else {
            userInputTasks.get(indexPosition).taskCompleted();
            userInputTasks.set(indexPosition, userInputTasks.get(indexPosition));
            //.set(int, Task)
            display.markAsComplete(userInputTasks, indexPosition);
        }
    }

    //method for unmarking tasks (incomplete)
    public void markAsIncomplete(int itemIndex){
        if (userInputTasks.get(itemIndex).getTaskCompletion() == false) {
            display.alreadyUnchecked(userInputTasks.get(itemIndex).getTaskName());
        } else {
            userInputTasks.get(itemIndex).taskNotCompleted();
            display.markAsNotComplete(userInputTasks, itemIndex);
        }
    }

    //method for deleting tasks
    public void deleteTask(int itemIndex) throws InvalidIndexException{
        Task deletedTask = userInputTasks.remove(itemIndex);
        Task.removeTask();
        display.deletedMessage(deletedTask);
    }

    /*
    public static void main(String[] args) {
        String logo = "__________              .__    .__ \n"
                + "\\______   \\__ __   ____ |  |__ |__|\n"
                + " |     ___/  |  \\_/ ___\\|  |  \\|  |\n"
                + " |    |   |  |  /\\  \\___|   Y  \\  |\n"
                + " |____|   |____/  \\___  >___|  /__|\n"
                + "                      \\/     \\/    \n";
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");

        String echo = "";
        ArrayList<String> list = new ArrayList<String>();
        Scanner typed = new Scanner(System.in);
        String[] instructions;
        int count = 0;
        int store = 0; //counter for updates array
        String from = "";
        String to = "";
        String[] by = new String[100];
        String[] event = new String[100];
        String[] event2 = new String[100];
        ArrayList<String> list1 = new ArrayList<String>();


        while (!echo.matches("bye(.*)")) {
                    echo = typed.nextLine();
                    if (!echo.equals("bye") && !echo.equals("list")) {
                        if (echo.contains("/from") && echo.contains("/to")) {
                            event = echo.split("/from");
                            event2 = event[1].split("/to");
                            from = event2[0];
                            to = event2[1];
                            list.add("[E]" + "[ ] " + event[0] + "(from:" + from + " to:" + to + ")");
                            //list[i-1][4] change for mark&unmark [X] [ ]
                            //i is counter for printing 1. 2. 3.
                            System.out.println("Got it. I've added this task:");
                            System.out.println(list.get(count));
                            count++;
                            System.out.println("Now you have " + count + " tasks in the list.");
                        } else if (echo.contains("/by")) {
                            by = echo.split("/by");
                            list.add("[D][ ] " + by[0] + "(by:" + by[1] + ")");
                            System.out.println("Got it. I've added this task:");
                            System.out.println(list.get(count));
                            count++;
                            System.out.println("Now you have " + count + " tasks in the list.");

                        }else if(echo.matches("blah") || echo.matches("bleah") || echo.matches("boo") || echo.matches("boom") || echo.matches("bang")){
                            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                            System.out.println("please be clear");
                        }else if(echo.matches("to") || echo.matches("do") || echo.matches("todo")){
                            System.out.println("OOPS!!! The description of a todo cannot be empty.");
                            System.out.println("please include what to do in a todo");
                        } else if (!echo.matches("mark(.*)") && !echo.matches("unmark(.*)") && !echo.matches("delete(.*)")) {
                            list.add("[T][ ] " + echo);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(list.get(count));
                            count++;
                            System.out.println("Now you have " + count + " tasks in the list.");
                        } else if (echo.matches("mark(.*)") && echo.contains(" ") && !list.isEmpty()) {
                            instructions = echo.split(" ");
                            store = Integer.parseInt(instructions[1]); //position number start from 1
                            if (list.size() >= store) {
                                list.set(store-1, (list.get(store-1).substring(0,4) + "X" + list.get(store-1).substring(5))); //.replace
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println(list.get(store-1));
                            }
                        } else if (echo.matches("unmark(.*)") && echo.contains(" ") && !list.isEmpty()) {
                            instructions = echo.split(" ");
                            store = Integer.parseInt(instructions[1]); //position number start from 1
                            if (list.size() >= store) {
                                list.set(store-1, (list.get(store-1).substring(0,4) + " " + list.get(store-1).substring(5)));
                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.println(list.get(store-1));
                            }
                        }else if (echo.matches("delete(.*)") && echo.contains(" ") && !list.isEmpty()){
                            instructions = echo.split(" ");
                            store = Integer.parseInt(instructions[1]);
                            if(list.size() >= store){
                                System.out.println("Noted. I've removed this task:");
                                System.out.println("    " + list.get(store-1));
                                list.remove(store-1);
                                System.out.println("Now you have " + list.size() + " tasks in the list.");
                            }
                        }

                    } else if (echo.matches("list")) {
                        for (int i = 1; i <= list.size(); i++) {
                            if (list.get(i - 1) != null) {
                                System.out.println(i + "." + list.get(i - 1));
                            } else {
                                break;
                            }
                        }
                    }

                try{
                    if (echo == "blah" || echo == "bleah" || echo == "boo" || echo == "boom" || echo == "bang") {
                        throw new InvalidEntryException();
                    }else if(echo == "to" || echo == "do" || echo == "todo"){
                        throw new CannotBeEmptyException();
                    }
                }catch(InvalidEntryException e){
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("please be clear");
                }catch(CannotBeEmptyException e){
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("please include what to do in a todo");
                }

    }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static class InvalidEntryException extends Exception{ //method to access own error messages
        //no other code needed
    }

    public static class CannotBeEmptyException extends Exception{
        //no other code needed
    }
*/
}
