package seedu.duke.commands;

public enum Command {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public String getLitr() {
        return command;
    }

    public static Command getEnum(String cmd) {
        return Command.valueOf(cmd.toUpperCase());
    }
}
