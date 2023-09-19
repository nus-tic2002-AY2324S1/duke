import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n\n\n" + logo);

        printMessage("Hello! I am Duke , your chatbot, set a name for me!!");
        String name;
        Scanner input = new Scanner(System.in);
        printMessage("Please enter a name for your chatbot (enter to set to default 'Duke'):");
        name = input.nextLine();
        if(name.isBlank()){
            name = "Duke";
        }
        printLine();
        String greeting = getGreeting();
        printMessage(greeting+"! I am "+name + ", Your personal chatbot.");
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
                        printMessage(idx + ". "  + task.getStatusIcon() +task.description);
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
                        System.out.println(split[1] + " is not a valid integer.");
                    }
                    if (taskList.size() >= index) {
                        taskList.get(index - 1).markAsDone();
                        printLine();
                        printMessage("Nice! I've marked this task as done:");
                        printMessage(taskList.get(index - 1).getStatusIcon() + taskList.get(index - 1).description);
                        printLine();
                    }
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
                }
            }else if(command.toLowerCase().startsWith("todo")){
                String[] split = command.split(" ");
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
            }else if(command.toLowerCase().startsWith("deadline")){
                String[] split = command.split(" ");
                String taskWithBracket = Arrays.toString(Arrays.copyOfRange(split, 1, split.length-2));
                String task = taskWithBracket.substring(1,taskWithBracket.length()-1).replace(","," ");
                String deadlineWithBracket = Arrays.toString(Arrays.copyOfRange(split,split.length-2,split.length));
                String deadline = deadlineWithBracket.substring(2,deadlineWithBracket.length()-1).trim().replace(",",": ");
                String desc = task + " (" + deadline +")";
                printMessage(desc);
                Deadlines deadlineTask = new Deadlines(desc);
                taskList.add(deadlineTask);
                printLine();
                printMessage("Got it. I've added this task:");
                System.out.println(deadlineTask.getStatusIcon()+" "+ desc);
                System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                printLine();
            }else if(command.toLowerCase().startsWith("event")){
                String descString = command.replace("event ","");
                String[] splitString = descString.split("/");
                int index = descString.indexOf("/");
                String desc = descString.substring(0,index-1);
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

//            }else if(split.length>1) {
//                Task tempTask = new Task(command);
//                taskList.add(tempTask);
//                printLine();
//                System.out.println("added: " + command);
//                printLine();
            }else {
                printLine();
                printMessage(command);
                printLine();
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

}
