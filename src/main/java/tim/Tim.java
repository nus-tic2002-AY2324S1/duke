package tim;
import tim.tasks.*;
import java.io.IOException;
import java.util.ArrayList;

public class Tim {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Task> list;
        Messenger.printLogo();
        Messenger.greetings();
        list = FileManager.loadList();
        Parser.inputParser(list);
    }
}
