package duke.commands;

/**
 * This handles incorrect commands
 */
public class IncorrectTaskHandler extends Task {

    /**
     * IncorrectTaskHandler constructor
     * @param desc description
     */
    public IncorrectTaskHandler(String desc) {
        this.description = desc;
        this.toRecord = false;
        this.toUpdateList = false;
    }

    /**
     * String to return to user
     * @return
     */
    @Override
    public String toString() {
        return description;
    }
}
