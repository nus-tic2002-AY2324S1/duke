package duke.dukeexceptions;

public class DeadlineDateException extends DukeException {

  public DeadlineDateException() {

    super("End Date should be after today!!!");
  }

}
