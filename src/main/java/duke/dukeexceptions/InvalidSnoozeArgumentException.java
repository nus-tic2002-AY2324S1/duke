package duke.dukeexceptions;

public class InvalidSnoozeArgumentException extends DukeException{

  public InvalidSnoozeArgumentException(){
    super("Invalid format for rescheduling. Please provide in the format of "
        + "<reschedule + Task index + yyyy-MM-dd HH:mm>");
  }
}
