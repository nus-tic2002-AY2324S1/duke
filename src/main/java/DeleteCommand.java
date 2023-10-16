public class DeleteCommand extends Command {
    public DeleteCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The task number to be deleted cannot be empty.");
        }

        Integer taskNumber = tryParseInt(args);
        if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidCommandArgsDukeException("Invalid task number to be deleted.");
        }

        Task task = tasks.getTask(taskNumber - 1);
        tasks.removeTask(task);
        ui.showMessages(new String[]{
                "Noted. I've removed this task:",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", tasks.size())
        });
    }
}
