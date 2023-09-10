import java.util.List;

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

    public void printWithLineBreak(String line) {
        System.out.println(lineBreak);
        System.out.println(line);
        System.out.println(lineBreak);
    }

    public void Hello() {
        printWithLineBreak(logo + "Hello, I'm Sophon:). \nHow can I assist you today?");
    }

    public void Goodbye() {
        printWithLineBreak("Bye! Hope to see you again soon!");
    }

    public void MissingInput() {
        printWithLineBreak("Sorry, did you say something?");
    }

    public void AddedMessage(String message) {
        printWithLineBreak("Added: " + message);
    }

    public void UserInputList(List<String> userInputList) {
        System.out.println(lineBreak);
        for (int i = 0; i < userInputList.size(); i++) {
            System.out.println((i + 1) + ". " + userInputList.get(i));
        }
        System.out.println(lineBreak);
    }
}
