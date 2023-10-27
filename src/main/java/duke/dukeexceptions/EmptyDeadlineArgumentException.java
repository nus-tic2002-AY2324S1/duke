package duke.dukeexceptions;

/**
 * The EmptyDeadlineArgumentException class is used to indicate that the user
 * did not provide any details for a deadline task.
 */
public class EmptyDeadlineArgumentException extends DukeException {

  /**
   * Constructs a new EmptyDeadlineArgumentException to informs the user
   * that no details were provided for the deadline task and provides
   * an example format for how the information should be provided.
   */
  public EmptyDeadlineArgumentException() {

    super("You did not provide any details on your deadline task."
        + " Please provide in the format of <deadline + Task Name + /by + by time>.");
  }

}
