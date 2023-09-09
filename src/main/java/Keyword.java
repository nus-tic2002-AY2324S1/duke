import java.util.HashMap;
import java.util.Map;

public enum Keyword {
    BYE,
    LIST,
    MARK,
    UNMARK,
    todo,
    deadline,
    event;

    public String getKeyword(Keyword key){
        return key.name();
    }

}
