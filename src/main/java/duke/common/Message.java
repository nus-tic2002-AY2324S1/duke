package duke.common;

public class Message {
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_I_DONT_KNOW = "OOPS!!! I'm sorry, but I'm not sure what you're asking :-(";
    public static final String MESSAGE_FILE_FORMAT_INVALID = "Oops, looks like there's a problem with the data file duke.txt. \n" +
            "It seems that some of the content is not in the right format.";
    public static final String MESSAGE_MAKE_NEW_INSTANCE = "No worries, I'll just make a new one for you right now.";
    public static final String MESSAGE_FILE_NOT_EXIST = "Oops, there's no duke.txt in the data folder.";
    public static String concat (String... messages){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messages[0]);
        for (int i=1; i<messages.length; i++) {
            stringBuilder.append("\n" + messages[i]);
        }
        return stringBuilder.toString();
    }

}
