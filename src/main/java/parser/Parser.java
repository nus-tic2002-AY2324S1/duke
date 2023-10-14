package parser;

import commandsTask.*;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<args>.*)");
    public static final Pattern MARK_FORMAT = Pattern.compile("(?<index>\\d*)");
    public static final Pattern TODO_FORMAT = Pattern.compile("(?<isdone>(-m-)?)(?<desc>.*)");
    public static final Pattern DEADLINE_FORMAT = Pattern.compile("(?<isdone>(-m-)?)(?<desc>.*)(?<by>/by)(?<date>.*)");
    public static final Pattern EVENT_FORMAT = Pattern.compile("(?<isdone>(-m-)?)(?<desc>.*)(?<from>/from)(?<date1>.*)(?<to>/to)(?<date2>.*)");

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
            case ListCMD.CMD:
                return new ListCMD(tasklist);
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

    private static boolean isDone (String prefix) {return prefix.equals("-m-");}

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

}
