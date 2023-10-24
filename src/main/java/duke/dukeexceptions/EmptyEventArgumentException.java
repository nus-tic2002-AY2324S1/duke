package duke.dukeexceptions;

/**
 * The EmptyEventArgumentException class is used to indicate that the user
 * did not provide any details for an event task.
 */
public class EmptyEventArgumentException extends DukeException {

  /**
   * Constructs a new EmptyEventArgumentException to informs the user
   * that no details were provided for the event task and provides
   * an example format for how the information should be provided.
   */
  public EmptyEventArgumentException() {

    super("You did not provide any details on your event task. "
        + "Please provide in the format of "
        + "<event + Task Name + /from + From time + /to + To time>.");
  }

}
