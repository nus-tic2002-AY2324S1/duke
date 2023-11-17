package Duke;
import Shelf.Shelf;
import Task.Text;

import java.io.IOException;
/**
 * <h1>CLARA</h1>
 * The CLARA program is a chatbot that manages your task and seek to increase your productivity.
 * <p>
 * CLARA and many fuctions including:
 * Creating, Deleting, Marking and setting deadline for your Tasks
 *
 * @author  Kai Siang
 * @version 0.8
 * @since   2023-11-14
 */
public class Duke {
    private Shelf tasks;
    public Duke(String fileName) {
        tasks = new Shelf();
        try {
            tasks = Storage.FileParser(Storage.loadFile(fileName));

        } catch (DukeException e) {
            Text.showLoadingError();
            tasks = new Shelf();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() {
        boolean isExit = false;
        try {
            Parser.Parse(tasks);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        new Duke("data").run();
    }
}
