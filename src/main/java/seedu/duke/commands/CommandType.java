package seedu.duke.commands;

import java.util.Arrays;
import java.util.List;

import seedu.duke.exceptions.DukeException;
import seedu.duke.helper.utils.WonkyUtils;
import seedu.duke.io.WonkyLogger;
import seedu.duke.task.WonkyManager;

/**
 * Represents a command along with its arguments.
 * It contains methods to get the command, its string representation, the arguments as a string,
 * the arguments as a list, and the number of arguments.
 * It also contains a method to set the arguments for the command.
 */
public abstract class CommandType {

    private final String EMPTY_LITR = "";
    private final int ZERO_ARGS = 0;

    private CommandEnum command;
    private String arguments;

    /**
     * Constructs the CommandArgument class.
     *
     * @param command the command to be executed.
     * @param arguments the arguments for the command.
     */
    public CommandType(CommandEnum command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Returns the command to be executed.
     *
     * @return the command to be executed
     */
    public CommandEnum getCmd() {
        return command;
    }

    /**
     * Returns the string representation of the command to be executed.
     *
     * @return the string representation of the command to be executed.
     */
    public String getCmdLitr() {
        return command.getLitr();
    }

    /**
     * Returns the string representation of the arguments for the command.
     *
     * @return the string representation of the arguments for the command.
     */
    public String getArgStr() {
        return arguments;
    }

    /**
     * Returns a list of the arguments for the command.
     *
     * @return a list of the arguments for the command.
     */
    public List<String> getArgList() {
        return Arrays.asList(arguments.split("\\|"));
    }

    /**
     * Returns the number of arguments for the command.
     *
     * @return the number of arguments for the command.
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
     *
     * @param newArg the new arguments for the command.
     */
    public void setArg(String newArg) {
        arguments = newArg;
    }

        /**
     * Validates that the given command argument has the expected number of arguments.
     *
     * @param commandType the command argument to validate.
     * @param expectedSize the expected number of arguments.
     * @return true if the command argument is valid, false otherwise.
     * @throws DukeException if there is an error validating the command argument.
     */
    protected boolean validateArgs(CommandType commandType, int expectedSize) throws DukeException {
        return validateArgs(commandType, expectedSize, true);
    }

    /**
     * Validates that the given command argument has the expected number of arguments.
     *
     * @param commandType the command argument to validate.
     * @param expectedSize the expected number of arguments.
     * @param isError whether to log an error if the command argument is invalid.
     * @return true if the command argument is valid, false otherwise.
     * @throws DukeException if there is an error validating the command argument.
     */
    protected boolean validateArgs(
        CommandType commandType, int expectedSize, boolean isError
    ) throws DukeException {
        WonkyLogger wonkyLogger = WonkyLogger.getInstance();
        WonkyManager wonkyManager = WonkyManager.getInstance();
        List<String> argList = commandType.getArgList();
        int argCount = commandType.getArgCount();
        if (wonkyLogger.getLoading()) {
            wonkyManager.addCmdType(commandType);
        }
        if (argCount == expectedSize && expectedSize == ZERO_ARGS) {
            wonkyManager.addCmdType(commandType);
            return true;
        }
        if (argCount != expectedSize || (argList.get(0).trim().isEmpty())) {
            if (isError) {
                wonkyLogger.mismatchArgs(commandType.getCmdLitr(), expectedSize);
            }
            return false;
        }
        for (String arg : argList) {
            if (EMPTY_LITR.equals(arg)) {
                return false;
            }
        }
        if (CommandEnum.EVENT.equals(commandType.getCmd()) || CommandEnum.DEADLINE.equals(commandType.getCmd())) {
            String newCmdTypeStr = commandType.getArgList().get(0);
            for (int i = 1; i < commandType.getArgCount(); i += 1) {
                String currArg = commandType.getArgList().get(i);
                if (!WonkyUtils.isValidDateTime(currArg)) {
                    return false;
                } else {
                    newCmdTypeStr += "|" + WonkyUtils.parseToDate(currArg).getDateTimeStr();
                }
            }
            commandType.setArg(newCmdTypeStr);
        }
        if (!wonkyLogger.getLoading()) {
            // Save the command argument to storage if it is not being loaded from storage
            wonkyManager.addCmdType(commandType);
        }
        return true;
    }

    public abstract void execute() throws DukeException;
}
