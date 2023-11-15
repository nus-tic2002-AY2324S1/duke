package com.tina.exception;

import com.tina.command.CommandEnum;

/**
 * The type Invalid parameter exception.
 */
public class InvalidParameterException extends TinaException {
    /**
     * Instantiates a new Invalid parameter exception.
     * Thrown when command syntax is invalid.
     *
     * @param command the command
     */
    public InvalidParameterException(CommandEnum command) {
        super("OOPS!!! Please use correct syntax: \n" + command.getSyntax());
    }

}
