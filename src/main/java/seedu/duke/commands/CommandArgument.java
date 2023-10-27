package seedu.duke.commands;

import java.util.Arrays;
import java.util.List;

public class CommandArgument {

    private Command command;
    private String arguments;

    public CommandArgument(Command command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public Command getCmd() {
        return command;
    }

    public String getCmdLitr() {
        return command.getLitr();
    }

    public String getArgStr() {
        return arguments;
    }

    public List<String> getArgList() {
        return Arrays.asList(arguments.split("\\|"));
    }

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

    public void setArg(String newArg) {
        arguments = newArg;
    }
}
