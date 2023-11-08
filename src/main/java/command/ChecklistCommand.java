package command;

import io.*;
import java.io.*;
import java.util.*;
import task.*;

public class ChecklistCommand extends CrabyMessage implements CommandInterface {

    /**
     * @inheritDoc Sends the Checklist task message to the user.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        String folderPath = "./data";
        File folder = new File(folderPath);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdir();
        }
        File[] files = folder.listFiles();
        printChecklistMessage(files);
    }
}


