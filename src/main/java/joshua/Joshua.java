package joshua;
import commands.Command;
import exceptions.IllegalStorageFormat;
import storage.Storage;

import java.io.FileNotFoundException;

/**
 * <h1>Joshua Chatbot</h1>
 * The Joshua Chatbot was created in likeness to the AI computer system in the 1983 movie, WarGames.
 * The AI in the movie was named Joshua after the late son of Professor Stephen Falken, the scientist who created it.
 * Unlike the movie, this program will simply help you keep track of your task list, so you don't
 * have to worry about starting World War III :)
 * <p>
 * With the Joshua Chatbot, you can store todos, deadlines and events in a convenient task list.
 *
 * @author Jacqueleen Heo
 * @version 1.0
 * @since 2023-11-19
 */
public class Joshua {
    private Storage storage;
    private TaskList taskList;
    private JoshuaUi ui;
    private JoshuaParser parser;
    private boolean isExit = false;

    /**
     * Initializes the Joshua chatbot by setting up the user interface, parser, storage,
     * and loading the task list from the storage if available.
     * If loading fails, a new TaskList is created.
     *
     * @see JoshuaUi
     * @see JoshuaParser
     * @see Storage
     * @see TaskList
     * @see Storage#load()
     * @see JoshuaUi#printSuccessfulLoad()
     * @see JoshuaUi#printLoadingError()
     */
    public Joshua(String filepath) {
        ui = new JoshuaUi();
        parser = new JoshuaParser();
        storage = new Storage(filepath);
        try {
            taskList = storage.load();
            ui.printSuccessfulLoad();
            ui.printGreetings();
        } catch (IllegalStorageFormat e) {
            ui.joshuaSays(e.getMessage());
            ui.printLoadingError();
            isExit = true;
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Joshua chatbot, which is a loop of taking in commands from the user
     * and executing the commands until the "bye" command is given.
     */
    public void run() {
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.joshuaSays(e.getMessage());
            }
        }
    }

    /**
     * Runs the Joshua chatbot program.
     *
     * @param args Arguments for the main method (not in use).
     */
    public static void main(String[] args) {
        new Joshua("./data/joshua.txt").run();
    }
}
