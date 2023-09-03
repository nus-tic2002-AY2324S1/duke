import java.util.Scanner;
public class Duke {
    private static Task[] list = new Task[100];
    private static int listCount =0;

    public static void addTask(String msg){
        list[listCount] = new Task(msg);
        listCount++;
        System.out.println("added: " + msg);
    }

    public static void printList(){
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<listCount; i++){
            System.out.println( (i+1) + ". [" + list[i].getStatusIcon() + "] "
                + list[i].getDescription());
        }
    }

    public static void mark(int item, boolean isDone){
        if (item > listCount) {return;}

        list[item - 1].setIsDone(isDone);
        if (isDone){
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println( "[" + list[item - 1].getStatusIcon() + "] "
                + list[item - 1].getDescription());
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
            String[] words = line.split(" ");
            if (words[0].equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if (words[0].equals("list")) { printList();
            } else if (words[0].equals("mark")) { mark(Integer.parseInt(words[1]),true);
            } else if (words[0].equals("unmark")) { mark(Integer.parseInt(words[1]), false);
            } else {
                addTask(line);
            }
        }

    }
}
