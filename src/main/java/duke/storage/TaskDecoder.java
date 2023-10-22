package duke.storage;

import duke.command.Command;
import duke.common.Message;
import duke.exception.FileStorageException;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

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
            LocalDateTime dateTime = getDateTime(tokens[3]);
            if(dateTime == null){
                break;
            }
            return new Deadline(Parser.parseStringToBoolean(tokens[1]),tokens[2],dateTime);
        case 'E':
            if(tokens.length != 5){
                break;
            }
            LocalDateTime fromDateTime = getDateTime(tokens[3]);
            LocalDateTime toDateTime = getDateTime(tokens[4]);
            if(fromDateTime == null || toDateTime == null){
                break;
            }
            return new Event(Parser.parseStringToBoolean(tokens[1]),tokens[2],fromDateTime,toDateTime);
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

    public static LocalDateTime getDateTime(String byArgument){
        final Matcher dateMatcher = Command.DATE_ARG_FORMAT.matcher(byArgument);
        if(!dateMatcher.matches()){
            return null;
        }
        final String timeArgument = dateMatcher.group("timeArgument");
        final Matcher timeMatcher = Command.TIME_ARG_FORMAT.matcher(timeArgument);
        if(!timeMatcher.matches()){
            return null;
        }
        return Parser.dateTime(dateMatcher, timeMatcher);
    }
}
