package seedu.duke.commands;

public enum Command {
    BYE("bye"),
    DEADLINE("deadline"),
    DELETE("delete"),
    EVENT("event"),
    LIST("list"),
    TODO("todo"),
    MARK("mark"),
    UNMARK("unmark");

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
