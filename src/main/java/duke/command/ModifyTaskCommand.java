package duke.command;

import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;
import duke.filehandler.FileStorage;
import java.util.List;

abstract class ModifyTaskCommand extends Command {

  protected final int itemIndex;

  public ModifyTaskCommand(int itemIndex) {

    this.itemIndex = itemIndex;
  }

  public void execute(FileStorage fileStorage, MessageDisplay display, List<Task> taskList) {
  }

}
