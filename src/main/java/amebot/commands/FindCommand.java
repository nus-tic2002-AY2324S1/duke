package amebot.commands;

import amebot.common.Messages;
import amebot.enumerations.Keyword;
import amebot.tasks.Task;
import amebot.tasks.Event;
import amebot.tasks.Deadline;

import java.util.ArrayList;

/**
 * Represents a command that finds matching tasks from the task list.
 */
public class FindCommand extends Command {
    protected String keyword;
    protected ArrayList<String> logs = new ArrayList<>();

    public FindCommand(String keyword) {
        this.keyword = keyword.toUpperCase();
    }

    /**
     * Returns logs for output.
     *
     * @return Logs for output.
     */
    @Override
    public ArrayList<String> executeCommand() {
        if (tasks.isEmpty()) {
            logs.add(Messages.EMPTY_LIST);
        }

        int itemNum = 1;

        logs.add(Messages.MATCHING_ITEMS);
        for (Task task : tasks) {
            storeMatchingTask(task, itemNum);
            itemNum++;
        }

        if (logs.size() == 1) {
            logs.set(0, Messages.NO_MATCHING_ITEMS);
        }

        return logs;
    }

    /**
     * Stores matching task to the logs.
     *
     * @param task    Task to be checked.
     * @param itemNum Item number of the task.
     */
    public void storeMatchingTask(Task task, int itemNum) {
        String taskType = task.getType().replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ", "");

        String taskDescription = task.getDescription();
        boolean isDescriptionMatched = taskDescription.contains(this.keyword);

        boolean isFromDateTimeMatched = false;
        boolean isToDateTimeMatched = false;
        boolean isDueDateTimeMatched = false;

        if (taskType.matches(Keyword.EVENT.toString())) {
            String taskFromDateTime = ((Event) task).getFromDateTime();
            isFromDateTimeMatched = taskFromDateTime.contains(this.keyword);

            String taskToDateTime = ((Event) task).getToDateTime();
            isToDateTimeMatched = taskToDateTime.contains(this.keyword);
        } else if (taskType.matches(Keyword.DEADLINE.toString())) {
            String taskDueDateTime = ((Deadline) task).getDueDateTime();
            isDueDateTimeMatched = taskDueDateTime.contains(this.keyword);
        }

        boolean isKeywordMatched = isDescriptionMatched || isFromDateTimeMatched || isToDateTimeMatched || isDueDateTimeMatched;

        if (isKeywordMatched) {
            logs.add(itemNum + ". " + task.getTask());
        }
    }
}
