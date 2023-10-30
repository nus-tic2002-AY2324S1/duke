package seedu.duke.commands;

import java.util.Arrays;
import java.util.List;

public class CommandArgument {

    private Command command;
    private String arguments;

    /**
     * Constructor for CommandArgument class.
     * @param command the command to be executed
     * @param arguments the arguments for the command
     */
    public CommandArgument(Command command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Returns the command to be executed.
     * @return the command to be executed
     */
    public Command getCmd() {
        return command;
    }

    /**
     * Returns the string representation of the command to be executed.
     * @return the string representation of the command to be executed
     */
    public String getCmdLitr() {
        return command.getLitr();
    }

    /**
     * Returns the string representation of the arguments for the command.
     * @return the string representation of the arguments for the command
     */
    public String getArgStr() {
        return arguments;
    }

    /**
     * Returns a list of the arguments for the command.
     * @return a list of the arguments for the command
     */
    public List<String> getArgList() {
        return Arrays.asList(arguments.split("\\|"));
    }

    /**
     * Returns the number of arguments for the command.
     * @return the number of arguments for the command
     */
    public int getArgCount() {
        if (arguments.isEmpty()) {
            return 0;
        }
        int count = Arrays.asList(arguments.split("\\|")).size();
        if (arguments.charAt(arguments.length() - 1) == '|') {
            count += 1;
        }
        return count;
    }

    /**
     * Sets the arguments for the command.
     * @param newArg the new arguments for the command
     */
    public void setArg(String newArg) {
        arguments = newArg;
    }
}
