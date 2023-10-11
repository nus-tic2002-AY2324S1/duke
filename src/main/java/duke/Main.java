package duke;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.command.Command;
import duke.parser.Parser;
import duke.exception.DukeException;

public class Main {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Main(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run(){
        ui.showeWelcome();
        boolean isExit = false;
        while(!isExit){
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
//        new Main("data/tasks.txt").run();
        Main m = new Main("data/tasks.txt");
        m.ui = new Ui();
        m.ui.showeWelcome();
    }
}
