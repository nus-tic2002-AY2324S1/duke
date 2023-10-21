package duke.storage;

import duke.common.Message;
import duke.exception.FileStorageException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TaskDecoder {
    public static ArrayList<Task> decodeTask(List<String> strings) throws FileStorageException{
        ArrayList<Task> tasks = new ArrayList<>();
        for (String string: strings) {
            tasks.add(decodeTaskFromString(string));
        }
        return tasks;
    }

    private static Task decodeTaskFromString(String string) throws FileStorageException{
        char abbreviation = string.charAt(0);
        String[] tokens = getTokens(string);
        switch(abbreviation){
        case 'T':
            if(tokens.length != 3){
                break;
            }
            return new Todo(Parser.parseStringToBoolean(tokens[1]), tokens[2]);
        case 'D':
            if(tokens.length != 4){
                break;
            }
            return new Deadline(Parser.parseStringToBoolean(tokens[1]),tokens[2],tokens[3]);
        case 'E':
            if(tokens.length != 5){
                break;
            }
            return new Event(Parser.parseStringToBoolean(tokens[1]),tokens[2],tokens[3],tokens[4]);
        default:
        }
        String message = Message.concat(Message.MESSAGE_FILE_FORMAT_INVALID,
                Message.MESSAGE_MAKE_NEW_INSTANCE);
        throw new FileStorageException(message);
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
