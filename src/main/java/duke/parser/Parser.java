package duke.parser;

import duke.command.Command;
import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.ui.Ui;


public class Parser {

    public Parser(){}
    public static Command parse(String userInput, UserKeywordArgument keywordArgument) {
        keywordArgument.setKeyword(parseKeyword(userInput));
        keywordArgument.setArguments(parseArgument(userInput));
        try {
            KeywordTypes key = KeywordTypes.valueOf(keywordArgument.getKeyword().toUpperCase());
            return key.createCommand();
        } catch (IllegalArgumentException e) {
            Ui ui = new Ui();
            ui.showResponseToUser(Message.MESSAGE_I_DONT_KNOW);
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
