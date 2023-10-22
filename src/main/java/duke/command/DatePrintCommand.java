package duke.command;

import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatePrintCommand extends Command{
    LocalDateTime date;
    public static final String COMMAND_WORD = "dprint";
    public static final String EXAMPLE_USAGE = "Example of usage:\n" + COMMAND_WORD + " 22/10/2023\n" +
            DATE_FORMAT_MESSAGE;
    public static final Pattern ARG_FORMAT = Pattern.compile("(?<day>[0-9]+)/" +
            "(?<month>[0-9]+)/(?<year>[0-9]{4})");

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, UserKeywordArgument keywordArgument)
            throws InvalidArgumentException {
        throwExceptionArgIsEmpty(keywordArgument, new DatePrintCommand());

        final Matcher matcher = ARG_FORMAT.matcher(keywordArgument.getArguments());
        throwExceptionDateIsInvalid(matcher, new DatePrintCommand());

        date = Parser.dateTime(matcher);

        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Here are the tasks in your date filter list:");
        for (int i = 0; i <= tasks.size()-1; i++) {
            Task task = tasks.get(i);
            if(task instanceof Deadline){
                Deadline deadline = (Deadline)task;
                if(dateIsEqual(date, deadline.getByDateTime())){
                    messages.add(String.format("%d.%s",i+1, tasks.get(i).toString()));
                }
            }else if(task instanceof Event){
                Event event = (Event) task;
                if(dateIsEqual(date, event.getFromDateTime()) || dateIsEqual(date, event.getToDateTime()) ){
                    messages.add(String.format("%d.%s",i+1, tasks.get(i).toString()));
                }
            }
        }
        ui.showResponseToUser(messages);
    }
    private boolean dateIsEqual(LocalDateTime d1, LocalDateTime d2){
        if(d1.getYear() != d2.getYear()){
            return false;
        }else if(d1.getMonth() != d2.getMonth()){
            return false;
        }else if(d1.getDayOfMonth() != d2.getDayOfMonth()){
            return false;
        }
        return true;
    }
    @Override
    public String getExampleUsage() {
        return EXAMPLE_USAGE;
    }
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
