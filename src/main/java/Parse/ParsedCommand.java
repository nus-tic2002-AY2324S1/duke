package Parse;

import java.time.temporal.Temporal;

/**
 * Represents a parsed user command
 */

public class ParsedCommand {
    private String commandType;
    private String taskDescription;
    private Temporal findDate;
    private Temporal deadline;
    private Temporal startDate;
    private Temporal endDate;
    private int taskIndex;

    /**
     * Constructs commands like "list", "bye", "help".
     *
     * @param commandType
     */

    public ParsedCommand(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Constructs commands for the "todo", "find", and "save" command.
     *
     * @param commandType
     * @param taskDescription
     */
    public ParsedCommand(String commandType, String taskDescription) {
        this.commandType = commandType;
        this.taskDescription = taskDescription;
    }

    /**
     * Constructs commands for the "finddate" command.
     *
     * @param commandType
     * @param findDate
     */
    public ParsedCommand(String commandType, Temporal findDate) {
        this.commandType = commandType;
        this.findDate = findDate;
    }

    /**
     * Constructs commands for the "mark/unmark" or "delete" command.
     *
     * @param commandType
     * @param taskIndex
     */
    public ParsedCommand(String commandType, int taskIndex) {
        this.commandType = commandType;
        this.taskIndex = taskIndex;
    }

    /**
     * Constructs commands for the "deadline" command.
     *
     * @param commandType
     * @param taskDescription
     * @param deadline
     */
    public ParsedCommand(String commandType, String taskDescription, Temporal deadline) {
        this.commandType = commandType;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }

    /**
     * Constructs commands for the "event" command.
     *
     * @param commandType
     * @param taskDescription
     * @param startDate
     * @param endDate
     */
    public ParsedCommand(String commandType, String taskDescription, Temporal startDate, Temporal endDate) {
        this.commandType = commandType;
        this.taskDescription = taskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Gets the type of the parsed command.
     *
     * @return The type of the command.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Gets the description of the task associated with the command.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Gets the deadline associated with the command.
     *
     * @return The deadline as a Temporal object.
     */
    public Temporal getDeadline() {
        return deadline;
    }

    /**
     * Gets the start date or time associated with the command.
     *
     * @return The start date or time as a Temporal object.
     */
    public Temporal getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date or time associated with the command.
     *
     * @return The end date or time as a Temporal object.
     */
    public Temporal getEndDate() {
        return endDate;
    }

    /**
     * Gets the index of the task associated with mark/unmark or delete commands.
     *
     * @return The task index.
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Gets the date associated with the find command.
     *
     * @return The date as a Temporal object.
     */
    public Temporal getFindDate() {
        return findDate;
    }
}
