package duke.data;

public class UserKeywordArgument {
    private static String keyword;
    private static String arguments;

    public static String getKeyword(){
        return keyword;
    }
    public static String getArguments(){
        return arguments;
    }
    public static void setKeyword(String value){
        keyword = value;
    }
    public static void setArguments(String value){
        arguments = value;
    }
}
