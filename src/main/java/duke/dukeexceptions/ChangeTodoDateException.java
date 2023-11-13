package duke.dukeexceptions;

/**
 * The ChangeTodoDateException class is a custom exception that is thrown when
 * an attempt is made to change the end date of a "To do" task, which is not allowed.
 */
public class ChangeTodoDateException extends DukeException {

    /**
     * Constructs a message that informs the user change todo task date is not allowed.
     */
    public ChangeTodoDateException() {

        super("You can't change the date of a to-do task!!!");
    }

}
