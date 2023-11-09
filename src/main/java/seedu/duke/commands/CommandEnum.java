package seedu.duke.commands;

/**
 * Represents the list of available commands in Duke.
 */
public enum CommandEnum {
    BYE("bye"),
    DEADLINE("deadline"),
    DELETE("delete"),
    EVENT("event"),
    LIST("list"),
    TODO("todo"),
    MARK("mark"),
    UNMARK("unmark"),
    FIND("find"),
    STASH("stash"),
    HELP("help");

    private String command;

    /**
     * Constructs the Command enum.
     *
     * @param command the string representation of the command.
     */
    CommandEnum(String command) {
        this.command = command;
    }

    /**
     * Returns the string representation of the command.
     *
     * @return the string representation of the command.
     */
    public String getLitr() {
        return command;
    }

    /**
     * Returns the Command enum value of the given string.
     *
     * @param cmd the string representation of the command.
     * @return the Command enum value of the given string.
     */
    public static CommandEnum getEnum(String cmd) {
        return CommandEnum.valueOf(cmd.toUpperCase());
    }
}
