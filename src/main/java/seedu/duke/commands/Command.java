package seedu.duke.commands;

public enum Command {
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public String command() {
    return command;
    }
}
