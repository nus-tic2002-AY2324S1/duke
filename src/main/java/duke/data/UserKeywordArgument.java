package duke.data;

import duke.parser.Parser;

/**
 * Responsible for managing user-provided keyword arguments.
 */
public class UserKeywordArgument {
    private String keyword;
    private String arguments;

    /**
     * Constructs a new UserKeywordArgument object by parsing the user input to extract a keyword and its arguments.
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
