public class TodoCommand extends TaskCommand {
    public TodoCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of a todo cannot be empty.");
        }

        Todo toto = new Todo(args);
        tasks.addTask(toto);
        ui.showMessages(getTaskAddedMessages(tasks));
    }
}
