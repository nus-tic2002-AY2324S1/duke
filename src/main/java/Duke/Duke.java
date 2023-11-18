package Duke;
import Shelf.ShelfManager;
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
    private ShelfManager tasks;
    public Duke() {
        tasks = new ShelfManager();
        String response = Text.showPrompt("Do you wish to load saved file...[y/n]: ");
        if (response.equals("y") || response.equals("Y")) {
            try {
                tasks = Storage.FileParser(Storage.loadFile("data"));
            } catch (DukeException e) {
                Text.showLoadingError();
                tasks = new ShelfManager();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        new Duke().run();
    }
}
