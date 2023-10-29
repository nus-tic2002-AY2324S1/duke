import java.util.Scanner;

/**
 * Handles UI printing.
 */

public class UI {

    private static String logo = "  _____ _           _        \n" +
            " / ____| |         | |       \n" +
            "| |    | |__   __ _| |_  \n" +
            "| |    | '_ \\ / _` | __/ \n" +
            "| |____| | | | (_| | |  \n" +
            " \\_____|_| |_|\\__,_|\\__\\\n";

    private static String horizontalLine = "_______________________________________________\n";


    private static String acceptedCommands = "Accepted commands are:\n"
            + "\tlist = List all the tasks that are stored\n"
            + "\ttodo <Task to be added in> = Adds a task to the list\n"
            + "\tdeadline <Task to be added in> /by <Deadline> = Adds a deadline task to the list\n"
            + "\tevent <Task to be added in> /from <Start date or time> /to <End date or time> = Adds a deadline task to the list\n"
            + "\tmark <Task number to be marked> = marks the task as completed\n"
            + "\tunmark <Task number to be unmarked> = mark the task as not completed\n"
            + "\tdelete <Task number to be deleted> = deletes the task\n"
            + "\tfind <search phrase> = finds all tasks with the search phrase\n"
            + "\tview <date> = View all tasks for the specific date\n"
            + "\tbye = Say goodbye to Chat";

    /**
     * Display the welcome message with the logo.
     */
    public void showWelcomeMessage() {
        System.out.println(logo + "Hello! My name is Chat.");
        System.out.print(horizontalLine);
    }

    /**
     * Display a message to confirm storing a task.
     *
     * @param taskDescription
     * @param taskType
     */
    public void showStoreOutput(String taskDescription, String taskType){
        System.out.println("[+] Got it. Storing '" + taskDescription + "' as a " + taskType + ".");
    }

    /**
     * Display a message to confirm marking or unmarking a task.
     *
     * @param taskDescription
     * @param markUnmark
     */

    public void showMarkUnMarkOutput(int taskDescription, String markUnmark){
        System.out.println("[+] " + markUnmark + " task " + taskDescription + "!");
    }

    /**
     * Display a message to find task(s) with a specific search phrase.
     *
     * @param taskDescription
     * @param markUnmark
     */

    public void findOutput(String taskDescription){
        System.out.println("[+] Finding tasks with: " + taskDescription + "!");
    }

    /**
     * Get user input from the console.
     *
     * @return The user's input as a string.
     */
    public String getUserInput() {
        System.out.print("Enter a command > ");
        Scanner scanner = new Scanner(System.in);

        // Checks for Control-C so that the exception message doesn't appear
        try {
            return scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Control-C detected.");
            return "";
        }
    }

    /**
     * Display a horizontal line
     */
    public void showHorizontalLine() {
        System.out.print(horizontalLine);
    }

    /**
     * Display a help message
     */
    public void showHelp(){
        System.out.println(acceptedCommands +"\n");
    }

    /**
     * Prompt the user for input.
     */
    public void askCommands(){
        System.out.print("What do you wish to do? - Enter 'help' for a list of accepted commands\n");
    }

    /**
     * Display a goodbye message.
     */
    public void showGoodBye(){
        System.out.println("Goodbye. See you soon");
    }

}
