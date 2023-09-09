import java.util.HashMap;
import java.util.Map;

public enum Keyword {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT;

    public static Keyword getKeyword(String input) {
        String key = input.toUpperCase();
        switch (key) {
        case "BYE":
            return BYE;
        case "LIST":
            return LIST;
        case "MARK":
            return MARK;
        case "UNMARK":
            return UNMARK;
        case "TODO":
            return TODO;
        case "DEADLINE":
            return DEADLINE;
        case "EVENT":
            return EVENT;
        }
        return null;
    }

}
