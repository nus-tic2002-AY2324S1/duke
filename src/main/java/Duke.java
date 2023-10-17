import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import Exception.DukeException;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n\n\n" + logo);

        printMessage("Hello! I am Duke , your chatBot, set a name for me!!");
        String name;
        Scanner input = new Scanner(System.in);
        printMessage("Please enter a name for your chatBot (press enter to set to default 'Duke'):");
        name = input.nextLine();
        if(name.isBlank()){
            name = "Duke";
        }
        printLine();
        String greeting = getGreeting();
        printMessage(greeting+"! I am "+name + ", Your personal chatBot.");
        printMessage("What can I do for you today?");
        printLine();

        ArrayList<Task> taskList = new ArrayList<>();
        while(true){
            printMessage("Please input command:");
            String command = input.nextLine();
            if(command.equalsIgnoreCase("bye")){
                printLine();
                printMessage("Bye. Hope to see you again soon!");
                printLine();
                break;
            }else if (command.equalsIgnoreCase("list")){
                if (!taskList.isEmpty()){
                    int idx = 1;
                    printLine();
                    printMessage("Here are the tasks in your list:");
                    for (Task task:taskList) {
                        printMessage(idx + ". "  + task.getStatusIcon() + " " + task.description);
                        idx++;
                    }
                    printLine();
                }else{
                    printLine();
                    printMessage(command);
                    printLine();
                }
            }else if(command.toLowerCase().contains("mark")) {
                String[] split = command.split(" ");
                if (split[0].trim().equalsIgnoreCase("mark")) {
                    int index = 0;
                    try {
                        index = Integer.parseInt(split[1].trim());
                    } catch (NumberFormatException e) {
                        printLine();
                        System.out.println(split[1] + " is not a valid integer.");
                        printLine();
                    }
                    if (taskList.size() >= index) {
                        taskList.get(index - 1).markAsDone();
                        printLine();
                        printMessage("Nice! I've marked this task as done:");
                        printMessage(taskList.get(index - 1).getStatusIcon() + taskList.get(index - 1).description);
                        printLine();
                    }
                    save(taskList);
                } else if (split[0].trim().equalsIgnoreCase("unmark")) {
                    int index = 0;
                    try {
                        index = Integer.parseInt(split[1].trim());
                    } catch (NumberFormatException e) {
                        System.out.println(split[1] + " is not a valid integer.");
                    }
                    if (taskList.size() >= index) {
                        taskList.get(index - 1).markAsNotDone();
                        printLine();
                        printMessage("OK, I've marked this task as not done yet:");
                        printMessage(taskList.get(index - 1).getStatusIcon() + taskList.get(index - 1).description);
                        printLine();
                    }
                    save(taskList);
                }
            }else if(command.toLowerCase().startsWith("todo")){
                String[] split = command.split(" ");
                try{
                    if(split.length<2){
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String descWithBracket = Arrays.toString(Arrays.copyOfRange(split, 1, split.length));
                    String desc = descWithBracket.substring(1,descWithBracket.length()-1).trim().replace(","," ");
                    printMessage(desc);
                    Todos todoTask = new Todos(desc);
                    taskList.add(todoTask);
                    printLine();
                    printMessage("Got it. I've added this task:");
                    System.out.println(todoTask.getStatusIcon()+" "+ desc);
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                    printLine();
                    save(taskList);
                }catch (DukeException e){
                    printLine();
                    printMessage(e.getMessage());
                    printLine();
                }
            }else if(command.toLowerCase().startsWith("deadline")){
                try {
                    String descString = command.replace("deadline","");
                    if (descString.isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }
                    int index = descString.indexOf("/");
                    String desc = descString.substring(0,index-1).trim();
                    String deadline = descString.substring(index).replace("/by"," (by: ")+ ")";
                    desc = desc + deadline;
                    printMessage(desc);
                    Deadlines deadlineTask = new Deadlines(desc);
                    taskList.add(deadlineTask);
                    printLine();
                    printMessage("Got it. I've added this task:");
                    System.out.println(deadlineTask.getStatusIcon()+" "+ desc);
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                    printLine();
                    save(taskList);
                } catch ( DukeException e ) {
                    printLine();
                    printMessage(e.getMessage());
                    printLine();
                }
            }else if(command.toLowerCase().startsWith("event")){
                try{
                    String descString = command.replace("event","");
                    if (descString.isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }
                    int index = descString.indexOf("/");
                    String desc = descString.substring(0,index-1).trim();
                    String time = descString.substring(index).replace("/from"," (from:").replace("/to","to:") + ")";
                    desc = desc + time;
                    printMessage(desc);
                    Events eventTask = new Events(desc);
                    taskList.add(eventTask);
                    printLine();
                    printMessage("Got it. I've added this task:");
                    System.out.println(eventTask.getStatusIcon()+" "+ desc);
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                    printLine();
                    save(taskList);
                }catch (DukeException e) {
                    printLine();
                    printMessage(e.getMessage());
                    printLine();
                }
            } else if(command.toLowerCase().startsWith("delete")) {
                try{
                    String descString = command.replace("delete","");
                    if (descString.trim().isEmpty()) {
                        throw new DukeException("OOPS!!! Please input item number you want to delete");
                    }
                    int itemNumber = Integer.parseInt(descString.trim());
                    try {
                        Task removedItem = taskList.remove(itemNumber-1);
                        printLine();
                        printMessage("Noted. I've removed this task:");
                        printMessage(removedItem.getStatusIcon() + removedItem.description);
                        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                        printLine();
                    } catch (IndexOutOfBoundsException e) {
                        // Handle the exception, e.g., by displaying an error message
                        System.err.println("Index out of bounds: " + e.getMessage());
                    }
                    save(taskList);
                } catch (DukeException e) {
                    printLine();
                    printMessage(e.getMessage());
                    printLine();
                }
            } else {
                String[] split = command.split(" ");
                try{
                    if(split.length<2){
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                }catch(DukeException e){
                    printLine();
                    printMessage(e.getMessage());
                    printLine();
                }
            }
        }

    }

    public static String getGreeting(){
        LocalDateTime currentTime = LocalDateTime.now();
        int hour = currentTime.getHour();
        if(hour < 12){
            return "Good morning";
        }else if(hour < 18){
            return "Good afternoon";
        }else {
            return "Good evening";
        }
    }

    public static void printLine(){
        System.out.println("----".repeat(10));
    }

    public static void printMessage(String message){
        System.out.println(message);
    }

    public static void save(ArrayList<Task> TaskList) throws IOException {
        String path = "data/duke.txt";
        File file = new File(path);
//        if file not exist create
        if (!file.exists()){
            try{
                String folderName=path.split("/")[0];
                File folder = new File(folderName);
                if (!folder.exists()){
                    folder.mkdirs();
                }
                file.createNewFile();
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        FileWriter fw = new FileWriter(file,false);
        for (Task task : TaskList){
            String taskType = String.valueOf(task.getTaskType());
            String status = String.valueOf(task.isDone? 1:0);
            String description = task.description;
            String text = taskType + " | " + status + " | "+description + "\n";
            fw.write(text);
        }
        fw.flush();
        fw.close();
    }

}
