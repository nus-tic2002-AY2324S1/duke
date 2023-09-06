import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static final ArrayList<Task> task = new ArrayList<>();
    private static int taskCount =0;
    private static final String[] keyWords = {"todo", "deadline", "event"};

    public static boolean searchKeyword (String word){
        for (String keyWord : keyWords){ if (word.equals(keyWord)) { return true; } }
        return false;
    }

    public static void printList(){
        if (taskCount == 0) {
            System.out.println("There are no tasks in your list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i < taskCount; i++){
            System.out.println( (i+1) + ". " + task.get(i).toString());
        }
    }

    public static void markTask(String[] words) throws DukeException {
        if (words.length == 1) { throw new DukeException(); }
        int item = Integer.parseInt(words[1])-1;
        if (words[0].equalsIgnoreCase("mark")) {
            if (task.get(item).getIsDone()){
                System.out.println("The item has already been marked as done!");
            } else {
                task.get(item).setIsDone(true);
                System.out.println("Nice! I've marked this task as done:");
            }
        } else {
            if (task.get(item).getIsDone()){
                task.get(item).setIsDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
            } else {
                System.out.println("The item wasn't done");
            }
        }
        System.out.println(task.get(item).toString());
    }

    public static void deleteTask(String[] words) throws DukeException {
        if (words.length == 1) { throw new DukeException(); }
        int item = Integer.parseInt(words[1])-1;
        String taskDetail = task.get(item).toString();
        task.remove(item);
        taskCount--;
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskDetail);
        System.out.println("Now you have " + taskCount + " tasks in the list." );
    }

    public static void processLine(String line) throws DukeException {
        String[] words = line.split(" ");
        String firstWord = words[0];

        boolean isKeyword = searchKeyword(firstWord);

        if (firstWord.equalsIgnoreCase("list")) {
            printList();
        } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
            try { markTask(words);
            } catch (NullPointerException|NumberFormatException|IndexOutOfBoundsException|DukeException e) {
                System.out.println("Please follow the [mark/unmark] syntax: e.g. \"mark/unmark <#>\"");
                printList();
            }
        } else if (firstWord.equals("delete")) {
            try { deleteTask(words);
            } catch (NullPointerException|NumberFormatException|IndexOutOfBoundsException|DukeException e) {
                System.out.println("Please follow the [delete] syntax: e.g. \"delete <#>\"");
                printList();
            }
        } else if (isKeyword) {
            processTask(words);
        } else {
            // Throw exception when the first word is not in the keywords list used
            throw new DukeException();
        }
    }

    public static void processTask (String[] words) {
        switch (words[0].toLowerCase()) {
            case "todo":
                try { createTask(words,"todo");
                } catch (DukeException e) {
                    System.out.println("Please follow the [todo] syntax: e.g. \"todo <text>\"");
                }
                break;
            case "deadline":
                try { createTask(words,"deadline");
                } catch (DukeException e) {
                    System.out.println("Please follow the [deadline] syntax: e.g. \"deadline <text> /by <text>\"");
                }
                break;
            case "event":
                try { createTask(words,"event");
                } catch (DukeException e) {
                    System.out.println("Please follow the [event] syntax: e.g. \"event <text> /from <text> /to <text>\"");
                }
                break;
        }
    }

    public static void createTask (String[] words, String taskType) throws DukeException {
        if (words.length == 1) { throw new DukeException(); }

        if (taskType.equalsIgnoreCase("todo")) {
            String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            task.add(new Todo(description));

        } else if (taskType.equalsIgnoreCase("deadline")) {
            int position = 0, positionBy = 0;
            for (String word : words){
                if (word.equals("/by")){ positionBy = position; break; }
                position++;
            }
            if (positionBy == 0 || positionBy == words.length-1) { throw new DukeException(); }
            String description = String.join(" ", Arrays.copyOfRange(words,1,positionBy));
            String byText = String.join(" ", Arrays.copyOfRange(words, positionBy+1, words.length));
            task.add(new Deadline(description,byText));

        } else if (taskType.equalsIgnoreCase("event")) {
            int position =0, positionFrom = 0, positionTo = 0;
            for (String word : words){
                if (word.equals("/from")){ positionFrom = position; }
                if (word.equals("/to")){ positionTo = position; }
                position++;
            }
            if (positionFrom == 0 || positionTo == 0 || positionFrom + 1 == positionTo || positionTo == words.length-1) { throw new DukeException(); }
            String description = String.join(" ", Arrays.copyOfRange(words,1,positionFrom));
            String fromText = String.join(" ", Arrays.copyOfRange(words, positionFrom+1, positionTo));
            String toText = String.join(" ", Arrays.copyOfRange(words, positionTo+1, words.length));
            task.add(new Event(description,fromText, toText));

        }
        System.out.println("Got it. I've added this task:");
        System.out.println(task.get(taskCount));
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list." );
    }

    public static void main(String[] args) {
        String logo = " ____            ____ \n"
                    + "|      __   __  |    \n"
                    + "|____ \\  | |  / |____\n"
                    + "|      \\ \\_/ /  |    \n"
                    + "|____   \\___/   |____ \n";
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello I'm Eve!\n" + logo + "\nWhat can I do for you?");

        for (;;){
            line = in.nextLine();
            if (!line.isEmpty()) {
                String[] words = line.split(" ");
                String firstWord = words[0];
                if (firstWord.equals("bye")) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    try {
                        processLine(line);
                    } catch (DukeException e) {
                        System.out.println("[" + line + "] Does not contain a valid command");
                    }
                }
            }

        }

    }

}
