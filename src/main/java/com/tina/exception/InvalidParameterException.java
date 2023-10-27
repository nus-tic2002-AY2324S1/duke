package com.tina.exception;

import com.tina.command.CommandEnum;

public class InvalidParameterException extends DukeException {
    public InvalidParameterException(CommandEnum command) {
        super("OOPS!!! Please use correct syntax: " + command.getSyntax());
    }

}
