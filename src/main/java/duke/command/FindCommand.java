package duke.command;

import duke.task.Task;
import duke.userinterface.UserInterface.MessageDisplay;
import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a `FindCommand` class that allows users to search for tasks based on a specified keyword.
 */
public class FindCommand extends CheckTaskCommand {

  private final String keyword;

  /**
   * Constructs a `FindCommand` with the specified search keyword.
   *
   * @param keyword The keyword to use for searching tasks.
   */
  public FindCommand(String keyword) {

    this.keyword = keyword;
  }

  /**
   * Prints a list of tasks that match the specified keyword.
   *
   * @param display  The message display interface for showing messages to the user.
   * @param taskList The list of tasks to search within.
   */
  public void printListWithKeyword(MessageDisplay display, List<Task> taskList) {

    if (taskList.isEmpty()) {
      display.print("There's nothing in your list");
      MessageDisplay.printLineBreak();
      return;
    }
    display.print("Here are the matching tasks in your list:");
    for (int i = 0; i < taskList.size(); i++) {
      // Solution below used fuzzy string search from https://central.sonatype.com/artifact/me.xdrop/fuzzywuzzy?smo=true
      if (FuzzySearch.ratio(taskList.get(i).getTaskName(), keyword) >= 55) {
        display.print((i + 1) + "." + taskList.get(i).toString());
      }
    }
    MessageDisplay.printLineBreak();
  }

  @Override
  public void execute(MessageDisplay display, List<Task> taskList, LocalDate checkedDate) {
    // Not implemented for this command.
  }

  /**
   * Executes the `FindCommand` to search for and print tasks that match the specified keyword.
   *
   * @param display  The message display interface for showing messages to the user.
   * @param taskList The list of tasks to search within.
   */
  @Override
  public void execute(MessageDisplay display, List<Task> taskList) {

    printListWithKeyword(display, taskList);
  }

}
