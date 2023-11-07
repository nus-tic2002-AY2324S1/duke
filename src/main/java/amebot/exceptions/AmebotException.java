package amebot.exceptions;

/**
 * Represents an exception that is thrown when an error occurs in Amebot.
 */
public class AmebotException extends Exception {
    public AmebotException(String err) {
        super(err);
    }
}
