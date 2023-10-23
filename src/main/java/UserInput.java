import java.util.ArrayList;
import java.util.Arrays;

public class UserInput {
    private ArrayList<String> tokens;

    public UserInput(String []tokens) {
        this.tokens = new ArrayList<>(Arrays.asList(tokens));
    }

    public ArrayList<String> handleTokens() throws DukeException {
        String func = tokens.get(0).toLowerCase();
        ArrayList<String> functionStringList = new ArrayList<>();
        functionStringList.add(func);
        switch (func) {
            /*case "bye":
            case "list":
                this.function = func;
                break;*/
            case "mark":
            case "unmark":
                //this.function = func;
                try {
                    Integer.parseInt(tokens.get(1));
                    functionStringList.add(tokens.get(1));
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new DukeException("OOPS!!! Please use correct syntax for " + func + "ing: " + func + " [task number]");
                }
                break;
            case "todo":
                //this.function = func;
                if (tokens.size() == 1) {
                    throw new DukeException("OOPS!!! Please use correct syntax for todo: todo [task name]");
                }
                tokens.remove(0);
                functionStringList.add(String.join(" ", tokens));
                break;
            case "deadline":
                //this.function = func;
                int byIndex = tokens.indexOf("/by");
                if (tokens.size() == 1 || byIndex == -1 || byIndex == tokens.size() - 1) {
                    throw new DukeException("OOPS!!! Please use correct syntax for todo: deadline [task name] /by [date]");
                }
                String deadLine = String.join(" ", tokens.subList(1, byIndex));
                functionStringList.add(deadLine);
                String by = String.join(" ", tokens.subList(byIndex + 1, tokens.size()));
                functionStringList.add(by);
                break;
            case "event":
                //this.function = func;
                int fromIndex = tokens.indexOf("/from");
                int toIndex = tokens.indexOf("/to");
                if (tokens.size() == 1 || fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex - 1 || toIndex == tokens.size() - 1) {
                    throw new DukeException("OOPS!!! Please use correct syntax for todo: event [task name] /from [date] /to [date]");
                }
                String event = String.join(" ", tokens.subList(1, fromIndex));
                functionStringList.add(event);
                String from = String.join(" ", tokens.subList(fromIndex + 1, toIndex));
                functionStringList.add(from);
                String to = String.join(" ", tokens.subList(toIndex + 1, tokens.size()));
                functionStringList.add(to);
                break;
            default:
        }
        return functionStringList;
    }
}
