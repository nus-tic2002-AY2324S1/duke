package duke.dukeexceptions;

public class EventDateException extends DukeException {

  public EventDateException() {

    super("Start date should be before end date!!!");
  }

}
