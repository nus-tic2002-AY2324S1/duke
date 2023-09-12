package command;

public class ByeCommand extends CrabyMessage{
    public void handleByeCommand(String answerName) {
        System.out.println("   Bye " + answerName + " ♡, hope to see you again soon!");
        System.out.println(line + "\n" + crab);
    }
}
