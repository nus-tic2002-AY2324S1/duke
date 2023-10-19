package duke.parser;

import duke.commandsTask.Task;
import duke.commandsTask.Todo;
import duke.commandsTask.IncorrectTaskHandler;
import duke.commandsTask.Deadline;
import duke.commandsTask.Delete;
import duke.commandsTask.Event;
import duke.commandsTask.Find;
import duke.commandsTask.Help;
import duke.commandsTask.ListCMD;
import duke.commandsTask.Mark;
import duke.commandsTask.Unmark;
import duke.commandsTask.Bye;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<args>.*)");
    public static final Pattern MARK_FORMAT = Pattern.compile("(?<index>\\d*)");
    public static final Pattern TODO_FORMAT = Pattern.compile("(?<isdone>(-m-)?)(?<desc>.*)");
    public static final Pattern DEADLINE_FORMAT = Pattern.compile("(?<isdone>(-m-)?)(?<desc>.*)(?<by>/by)(?<date>.*)");
    public static final Pattern EVENT_FORMAT = Pattern.compile("(?<isdone>(-m-)?)(?<desc>.*)(?<from>/from)(?<date1>.*)(?<to>/to)(?<date2>.*)");
    public static final Pattern FIND_FORMAT = Pattern.compile("(?<desc>\\S+?)");


    /**
     * Returns a Task command given a line input
     * If the line input does not match the format of implemented Task command, returns the Help command
     *
     * @param input command line entered by user
     * @param tasklist User's list of tasks
     * @return a Task command
     */
    public static Task parseCommand(String input, List<Task> tasklist){
        final Matcher match = COMMAND_FORMAT.matcher(input.trim());
        if (!match.matches()){
            return new IncorrectTaskHandler(new Help().toString());
        }
        final String command = match.group("command");
        final String args = match.group("args");

        switch (command){
            case Todo.CMD:
                return prepTodo(args);
            case Deadline.CMD:
                return prepDeadline(args);
            case Event.CMD:
                return prepEvent(args);
            case Bye.CMD:
                return new Bye();
            case ListCMD.CMD:
                return new ListCMD(tasklist);
            case Find.CMD:
                return prepFind(args, tasklist);
            case Mark.CMD:
                return prepMark(args,tasklist, true);
            case Unmark.CMD:
                return prepMark(args,tasklist, false);
            case Delete.CMD:
                return prepDelete(args, tasklist);
            default:
                return new Help();
        }

    }

    /**
     * Prepares arguments provided to match MARK_FORMAT to pass into Mark or Unmark command
     *
     * @param args string of arguments
     * @param tasklist User's list of tasks
     * @param isMark boolean indicator, TRUE for Mark, FALSE for Unmark
     * @return Mark / Unmark task
     */
    private static Task prepMark (String args, List <Task> tasklist,boolean isMark){
        final Matcher match = MARK_FORMAT.matcher(args.trim());
        if (!match.matches() || args.isEmpty()){
            if (isMark){
                return new IncorrectTaskHandler(Mark.CMD_HELP);
            } else {
                return new IncorrectTaskHandler(Unmark.CMD_HELP);
            }

        }
        if (isMark){
            return new Mark(
                    Integer.parseInt(match.group("index")),
                    tasklist
            );
        } else {
            return new Unmark(
                    Integer.parseInt(match.group("index")),
                    tasklist
            );
        }

    }

    /**
     * Prepares arguments provided to match MARK_FORMAT to pass into Delete command
     *
     * @param args string of arguments
     * @param tasklist User's list of tasks
     * @return Delete task
     */
    private static Task prepDelete (String args, List <Task> tasklist){
        final Matcher match = MARK_FORMAT.matcher(args.trim());
        if (!match.matches() || args.isEmpty()){
                return new IncorrectTaskHandler(Delete.CMD_HELP);
        }
        return new Delete(
                Integer.parseInt(match.group("index")),
                tasklist
        );
    }

    /**
     * Returns boolean if the prefix of the input line equals "-m-"
     *
     * @param prefix string line
     * @return boolean
     */
    private static boolean isDone (String prefix) {return prefix.equals("-m-");}

    /**
     * Prepares arguments provided to match TODO_FORMAT to pass into Todo command
     *
     * @param args string of arguments
     * @return Todo task
     */
    private static Task prepTodo (String args){
        final Matcher match = TODO_FORMAT.matcher(args.trim());
        if (!match.matches() || args.isEmpty()){
            return new IncorrectTaskHandler(Todo.CMD_HELP);
        }
        return new Todo(
                isDone(match.group("isdone")),
                match.group("desc").trim()
        );
    }

    /**
     * Prepares arguments provided to match DEADLINE_FORMAT to pass into Deadline command
     *
     * @param args string of arguments
     * @return Deadline task
     */
    private static Task prepDeadline (String args){
        final Matcher match = DEADLINE_FORMAT.matcher(args.trim());
        if (!match.matches()){
            return new IncorrectTaskHandler(Deadline.CMD_HELP);
        }
        return new Deadline(
                isDone(match.group("isdone")),
                match.group("desc").trim(),
                match.group("date").trim()
        );
    }

    /**
     * Prepares arguments provided to match the EVENT_FORMAT to pass into Event command
     *
     * @param args string of arguments
     * @return Event task
     */
    private static Task prepEvent (String args){
        final Matcher match = EVENT_FORMAT.matcher(args.trim());
        if (!match.matches()){
            return new IncorrectTaskHandler(Event.CMD_HELP);
        }
        return new Event(
                isDone(match.group("isdone")),
                match.group("desc").trim(),
                match.group("date1").trim(),
                match.group("date2").trim()
        );
    }

    /**
     * Prepares arguments provided to match the FIND_FORMAT to pass into Find command
     *
     * @param args string of arguments
     * @param taskList current list of tasks
     * @return Find task
     */
    private static Task prepFind (String args, List <Task> taskList){
        final Matcher match = FIND_FORMAT.matcher(args.trim());
        if (!match.matches()){
            return new IncorrectTaskHandler(Find.CMD_HELP);
        }
        return new Find(
                match.group("desc").trim(),
                taskList
        );
    }

}
