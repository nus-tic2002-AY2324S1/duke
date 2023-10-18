package duke.storage;

import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
        String[] tokens = getTokens(string);
        switch(abbreviation){
        case 'T':
            return new Todo(Parser.parseStringToBoolean(tokens[1]), tokens[2]);
        case 'D':
            return new Deadline(Parser.parseStringToBoolean(tokens[1]),tokens[2],tokens[3]);
        case 'E':
            return new Event(Parser.parseStringToBoolean(tokens[1]),tokens[2],tokens[3],tokens[4]);
        default:
        }
        return new Todo();
    }

    private static String[] getTokens (String string) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(string, "|");
        while(stringTokenizer.hasMoreTokens()){
            tokens.add(stringTokenizer.nextToken());
        }
        String[] arr = new String[tokens.size()];
        return tokens.toArray(arr);
    }
}
