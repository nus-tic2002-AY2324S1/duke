package com.tina.service;

import com.tina.command.CommandEnum;
import com.tina.exception.InvalidDateFormatException;
import com.tina.exception.InvalidParameterException;

import java.util.ArrayList;

/**
 * The type Validator.
 */
public class Validator {

    /**
     * Validate command.
     *
     * @param tokens  the tokens
     * @param command the command
     * @throws InvalidParameterException  the invalid parameter exception
     * @throws InvalidDateFormatException the invalid date format exception
     */
    public static void validateCommand(ArrayList<String> tokens, CommandEnum command)
            throws InvalidParameterException, InvalidDateFormatException {
        ArrayList<String> keywords = new ArrayList<>();
        switch (command) {
        case BYE:
        case LIST:
        case HELP:
            validateCommandParameter(tokens, command, 1, true);
            break;
        case MARK:
        case UNMARK:
        case DELETE:
            validateCommandParameter(tokens, command, 2, true);
            validateTaskNumber(tokens, command);
            break;
        case TODO:
        case SCHEDULE:
        case FIND:
            validateCommandParameter(tokens, command, 2, false);
            break;
        case DEADLINE:
            keywords.add("/by");
            validateCommandParameter(tokens, command, keywords);
            break;
        case EVENT:
            keywords.add("/from");
            keywords.add("/to");
            validateCommandParameter(tokens, command, keywords);
            break;
        case ARCHIVE:
            validateCommandParameter(tokens, command, 2, true);
            String fileName = tokens.get(1);
            if (!fileName.toLowerCase().endsWith(".txt")) {
                throw new InvalidParameterException(CommandEnum.ARCHIVE);
            }
            break;
        default:
        }
    }

    /**
     * Validate task number.
     * Throws exception if task number is missing or task number is not in integer format.
     *
     * @param tokens  the parameters from user input
     * @param command the command type
     * @throws InvalidParameterException if task number is missing or invalid
     */
    public static void validateTaskNumber(ArrayList<String> tokens, CommandEnum command)
            throws InvalidParameterException {
        try {
            Integer.parseInt(tokens.get(1));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidParameterException(command);
        }
    }

    /**
     * Validate command length by minimum length or fixed length.
     * Throw exception if parameters are not given in correct syntax.
     *
     * @param tokens        the parameters from user input
     * @param command       the command type
     * @param minLength     the minimum length
     * @param isFixedLength the flag to indicate it is a fixed length
     * @throws InvalidParameterException if parameters are not given in correct syntax
     */
    public static void validateCommandParameter(ArrayList<String> tokens, CommandEnum command, int minLength, boolean isFixedLength)
            throws InvalidParameterException {
        int size = tokens.size();
        if (isFixedLength && size != minLength) {
            throw new InvalidParameterException(command);
        } else if (size < minLength) {
            throw new InvalidParameterException(command);

        }
    }

    /**
     * Validate command parameter by checking keywords.
     * Throws exception if keywords are missing or not in correct position
     *
     * @param tokens   the tokens
     * @param command  the command
     * @param keywords the keywords list
     * @throws InvalidParameterException the invalid parameter exception
     */
    public static void validateCommandParameter(ArrayList<String> tokens, CommandEnum command, ArrayList<String> keywords)
            throws InvalidParameterException {
        int size = tokens.size();
        int index = -1;
        int indexOfLastKeyword = 0;

        for (String keyword : keywords) {
            index = tokens.indexOf(keyword);

            // can not find keyword || keyword is the last element
            if (index == -1 || index == size - 1) {
                throw new InvalidParameterException(command);
            }
            // the last keyword does not have any parameters followed before the next keyword
            if (index - 1 == indexOfLastKeyword) {
                throw new InvalidParameterException(command);
            }
            indexOfLastKeyword = index;
        }
    }

}
