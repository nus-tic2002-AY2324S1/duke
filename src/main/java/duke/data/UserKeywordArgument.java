package duke.data;

import duke.parser.Parser;

public class UserKeywordArgument {
    private String keyword;
    private String arguments;

    public UserKeywordArgument(String userInput){
        this.keyword = Parser.parseKeyword(userInput);
        this.arguments = Parser.parseArgument(userInput);
    }
    public String getKeyword(){
        return keyword;
    }
    public String getArguments(){
        return arguments;
    }
    public void setKeyword(String value){
        keyword = value;
    }
    public void setArguments(String value){
        arguments = value;
    }
}
