package tim;
import tim.ui.Display;
import tim.ui.Parser;
import tim.util.Storage;
import tim.util.TaskList;

import java.io.IOException;

public class Tim {
    /**
     * Represents the Main method.
     *
     * @param args Command line arguments.
     * @throws IOException If the file is not found.
     * @throws ClassNotFoundException If the file is corrupted.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TaskList Tasks;
        Display.printLogo();
        Display.greetings();
        Tasks = Storage.loadList();
        Parser.inputParser(Tasks);
    }
}
