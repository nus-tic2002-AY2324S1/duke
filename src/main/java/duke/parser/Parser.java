package duke.parser;

import duke.command.ICommand;
import duke.common.Converse;
import duke.data.UserKeywordArgument;
import duke.ui.Ui;


public class Parser {

    public Parser(){}
    public static ICommand parse(String userInput, UserKeywordArgument keywordArgument) {
        UserKeywordArgument.setKeyword(parseKeyword(userInput));
        UserKeywordArgument.setArguments(parseArgument(userInput));
        try {
            KeywordTypes key = KeywordTypes.valueOf(UserKeywordArgument.getKeyword().toUpperCase());
            return key.createCommand();
        } catch (IllegalArgumentException e) {
            Ui ui = new Ui();
            ui.showResponseToUser(Converse.MESSAGE_I_DONT_KNOW);
            return null;
        }
    }

    private static String parseKeyword(String userInput){
        String[] inputs = userInput.split(" ");
        return inputs[0];
    }

    private static String parseArgument(String userInput){
        String[] inputs = userInput.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < inputs.length; i++) {
            sb.append(inputs[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

}
