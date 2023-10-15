package tim;

import java.io.IOException;
import java.util.ArrayList;

public class Tim {
//    private static tim.Task[] list = {};

    private static ArrayList<Task> list = new ArrayList<Task>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Messenger.printLogo();
        Messenger.greetings();
        list = FileManager.loadList();
        Parser.inputParser(list);
    }
}
