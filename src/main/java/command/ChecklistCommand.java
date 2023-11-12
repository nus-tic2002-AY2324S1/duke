package command;

import io.CrabyMessage;
import java.io.File;
import java.util.List;
import task.Task;

public class ChecklistCommand extends CrabyMessage implements CommandInterface {

    /**
     * @inheritDoc Sends the Checklist task message to the user.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        assert input != null;
        String folderPath = "./data";
        File folder = new File(folderPath);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdir();
        }
        File[] files = folder.listFiles();
        printChecklistMessage(files);
    }
}


