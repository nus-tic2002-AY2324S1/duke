import java.util.List;

abstract class Command {

    public abstract void execute() throws DukeException;

    public void storeDuke() {
        List<Task> taskList = Duke.taskList;
        Duke.fileStorage.fileStorage(taskList);
    }
}

class ListCommand extends Command {

    @Override
    public void execute() {
        List<Task> taskList = Duke.taskList;
        printList(taskList);
    }

    /**
     * Displays the list of user tasks.
     *
     * @param taskList The array of user tasks.
     */
    public static void printList(List<Task> taskList) {
        if (Task.getTotalTasks() == 0) {
            UserInterface.MessageDisplay.print("There's nothing in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
        System.out.println(UserInterface.MessageDisplay.LINE_BREAK);
    }


}

class DeleteCommand extends Command {
    private final int itemIndex;

    public DeleteCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public void execute() {
        Task.removeTask();
        Task deletedTask = Duke.taskList.remove(itemIndex);
        UserInterface.MessageDisplay.deleteMessage(deletedTask);
    }
}

abstract class AddTaskCommand extends Command {
    protected final String taskName;

    public AddTaskCommand(String taskName) {
        this.taskName = taskName;
    }

    protected void addTask(Task task) {
        Duke.taskList.add(task);
        int itemIndex = Task.getTotalTasks() - 1;
        storeDuke();
        UserInterface.MessageDisplay.addedMessage(Duke.taskList, itemIndex);
    }
}

class AddTodoCommand extends AddTaskCommand {
    public AddTodoCommand(String taskName) {
        super(taskName);
    }

    @Override
    public void execute() {
        Task task = new TodoTask(taskName);
        addTask(task);
    }
}

class AddDeadLineCommand extends AddTaskCommand {
    private final String taskDueDate;

    public AddDeadLineCommand(String taskName, String taskDueDate) {
        super(taskName);
        this.taskDueDate = taskDueDate;
    }

    @Override
    public void execute() {
        Task task = new DeadlineTask(taskName, taskDueDate);
        addTask(task);
    }
}

class AddEventCommand extends AddTaskCommand {

    private final String taskFromDate;
    private final String taskToDate;

    public AddEventCommand(String taskName, String taskFromDate, String taskToDate) {
        super(taskName);
        this.taskFromDate = taskFromDate;
        this.taskToDate = taskToDate;
    }

    @Override
    public void execute() {
        Task task = new EventTask(taskName, taskFromDate, taskToDate);
        addTask(task);
    }

}

class MarkAsCompletedCommand extends Command {
    private final int itemIndex;

    public MarkAsCompletedCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public void execute() {
        Task task = Duke.taskList.get(itemIndex);
        if (task.isCompleted()) {
            UserInterface.MessageDisplay.alreadyMark(task.getTaskName());
        } else {
            task.markAsCompleted();
            UserInterface.MessageDisplay.completeMessage(Duke.taskList, itemIndex);
        }
    }

}

class MarkAsInCompletedCommand extends Command {
    private final int itemIndex;

    public MarkAsInCompletedCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public void execute() {
        Task task = Duke.taskList.get(itemIndex);
        if (!task.isCompleted()) {
            UserInterface.MessageDisplay.notMark(task.getTaskName());
        } else {
            task.markAsNotCompleted();
            UserInterface.MessageDisplay.unCompleteMessage(Duke.taskList, itemIndex);
        }
    }
}


