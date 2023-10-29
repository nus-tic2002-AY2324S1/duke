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
     * Constructor for commands like "list", "bye", "help".
     *
     * @param commandType
     */

    public ParsedCommand(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Constructor for the "todo" and "findphrase" command.
     *
     * @param commandType
     * @param taskDescription
     */
    public ParsedCommand(String commandType, String taskDescription) {
        this.commandType = commandType;
        this.taskDescription = taskDescription;
    }

    /**
     * Constructor for the "finddate" command.
     *
     * @param commandType
     * @param deadline
     */
    public ParsedCommand(String commandType, Temporal findDate) {
        this.commandType = commandType;
        this.findDate = findDate;
    }

    /**
     * Constructor for the "mark/unmark" or "delete" command.
     *
     * @param commandType
     * @param taskIndex
     */
    public ParsedCommand(String commandType, int taskIndex) {
        this.commandType = commandType;
        this.taskIndex = taskIndex;
    }

    /**
     * Constructor for the "deadline" command.
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
     * Constructor for the "event" command.
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
     * Get the type of the parsed command.
     *
     * @return The type of the command.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Get the description of the task associated with the command.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Get the deadline associated with the command.
     *
     * @return The deadline as a Temporal object.
     */
    public Temporal getDeadline() {
        return deadline;
    }

    /**
     * Get the start date or time associated with the command.
     *
     * @return The start date or time as a Temporal object.
     */
    public Temporal getStartDate() {
        return startDate;
    }

    /**
     * Get the end date or time associated with the command.
     *
     * @return The end date or time as a Temporal object.
     */
    public Temporal getEndDate() {
        return endDate;
    }

    /**
     * Get the index of the task associated with mark/unmark or delete commands.
     *
     * @return The task index.
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Get the date associated with the find command.
     *
     * @return The date as a Temporal object.
     */
    public Temporal getFindDate() {
        return findDate;
    }
}
