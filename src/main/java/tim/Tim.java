package tim;
import tim.body.Parser;
import tim.body.Storage;
import tim.body.TaskList;
import tim.body.UI;
import tim.tasks.Task;
import java.io.IOException;
import java.util.ArrayList;

public class Tim {
    /**
     * Main method.
     *
     * @param args Command line arguments.
     * @throws IOException If the file is not found.
     * @throws ClassNotFoundException If the file is corrupted.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TaskList list;
        UI.printLogo();
        UI.greetings();
        list = Storage.loadList();
        Parser.inputParser(list);
    }
}
