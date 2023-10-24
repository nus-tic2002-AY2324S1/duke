package duke.data;

import duke.parser.Parser;

public class UserKeywordArgument {
    private String keyword;
    private String arguments;

    /**
     * Constructor for the UserKeywordArgument class.
     *
     * @param userInput The user input string containing keyword and arguments.
     */
    public UserKeywordArgument(String userInput) {
        this.keyword = Parser.parseKeyword(userInput);
        this.arguments = Parser.parseArgument(userInput);
    }

    /**
     * Gets the keyword associated with the user input.
     *
     * @return The keyword extracted from the user input.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Gets the arguments associated with the user input.
     *
     * @return The arguments extracted from the user input.
     */
    public String getArguments() {
        return arguments;
    }
}
