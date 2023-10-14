package command;

import io.CrabyMessage;
import task.Task;

import java.util.List;

import static task.Craby.stack;

public class UndoCommand extends CrabyMessage implements CommandInterface {
    @Override
    public short handleCommand(String input, List<Task> tasks) {
        stack.pop();
        if (stack.isEmpty()) {
            printUndoError();
            return 0;
        }
        String lastInput = stack.pop();
        String[] lastInputArr = lastInput.split(" ");
        String lastKeyword = lastInputArr[0].trim().toLowerCase();
        if (isAddTask(lastKeyword)) {
            printUndoMessage(tasks.get(tasks.size() - 1).toString());
            tasks.remove(tasks.size() - 1);
        } else {
            printUndoError();
        }
        return 0;
    }
    private static boolean isAddTask(String lastKeyword) {
        return  !lastKeyword.equals("list") && !lastKeyword.equals("bye")
                && !lastKeyword.equals("help") && !lastKeyword.equals("find")
                && !lastKeyword.equals("deleteall") && !lastKeyword.equals("delete")
                && !lastKeyword.equals("sortby") && !lastKeyword.equals("blah")
                && !lastKeyword.equals("mark") && !lastKeyword.equals("unmark")
                && !lastKeyword.equals("undo") ;
    }
}

