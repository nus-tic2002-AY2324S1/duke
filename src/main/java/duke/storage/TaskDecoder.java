package duke.storage;

import duke.command.Command;
import duke.command.TodoCommand;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class TaskDecoder {
    public static ArrayList<Task> decodeTask(List<String> strings) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String string: strings) {
            tasks.add(decodeTaskFromString(string));
        }
        return tasks;
    }

    private static Task decodeTaskFromString(String string) {
        char abbreviation = string.charAt(0);
        String[] datas = string.split("|");
        switch(abbreviation){
        case 'T':
            return new Todo(Parser.parseStringToBoolean(datas[1]), datas[2]);
        case 'D':
            return new Deadline(Parser.parseStringToBoolean(datas[1]),datas[2],datas[3]);
        case 'E':
            return new Event(Parser.parseStringToBoolean(datas[1]),datas[2],datas[3],datas[4]);
        default:
        }
        return new Todo();
    }

    private static ArrayList<String> splitString(String string) {
        ArrayList<String> strings = new ArrayList<>();
        for(int i=0; i<string.length(); i++){
            char c = string.charAt(i);
            if(c == '|'){
                continue;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
        }
        return strings;
    }
}
