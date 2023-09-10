public class MessageDisplay {
    private final String lineBreak = "****************************************";

    private final String logo =
            "╭━━━╮╱╱╱╱╱╭╮\n" +
            "┃╭━╮┃╱╱╱╱╱┃┃\n" +
            "┃╰━━┳━━┳━━┫╰━┳━━┳━╮\n" +
            "╰━━╮┃╭╮┃╭╮┃╭╮┃╭╮┃╭╮╮\n" +
            "┃╰━╯┃╰╯┃╰╯┃┃┃┃╰╯┃┃┃┃\n" +
            "╰━━━┻━━┫╭━┻╯╰┻━━┻╯╰╯\n" +
            "╱╱╱╱╱╱╱┃┃\n" +
            "╱╱╱╱╱╱╱╰╯\n";

    public void print(String line) {
        System.out.println(lineBreak);
        System.out.println(line);
        System.out.println(lineBreak);
    }

    public void Hello() {
        print(logo + "Hello, I'm Sophon:). \nHow can I assist you today?");
    }

    public void Goodbye() {
        print("Bye! Hope to see you again soon!");
    }

    public void MissingInput() {
        print("Sorry, did you say something?");
    }

    public void AddedMessage(String message) {
        print("Added: " + message);
    }

    public void alreadyMark(String taskName) {
        print(taskName + " is already marked!");
    }

    public void notMark(String taskName) {
        print("You did not complete " + taskName + " before!");
    }

    public void notDeclared() {
        print("I can't find this task!");
    }

    public void completeMessage(Task[] userInputArray, int itemNumber) {
        System.out.println(lineBreak);
        System.out.println("That's some progress! I've mark this task as done:");
        System.out.println(" ".repeat(3) + "[X] " + userInputArray[itemNumber].getTaskName());
        System.out.println(lineBreak);
    }

    public void unCompleteMessage(Task[] userInputArray, int itemNumber) {
        System.out.println(lineBreak);
        System.out.println("Okay, you can do it at a later time:");
        System.out.println(" ".repeat(3) + "[ ] " + userInputArray[itemNumber].getTaskName());
        System.out.println(lineBreak);
    }

    public void invalidNumberFormat() {
        print("That doesn't seems like a item number.");
    }

    public void UserInputList(Task[] userInputList) {
        if (Task.getTotalTasks() == 0) {
            print("There's nothing in your list");
            return;
        }
        System.out.println(lineBreak);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            String Completed = "";
            if (userInputList[i].isCompleted()) {
                Completed = "[X]";
            } else {
                Completed = "[ ]";
            }
            System.out.println((i + 1) + "." + Completed + " " + userInputList[i].getTaskName());
        }
        System.out.println(lineBreak);
    }
}
