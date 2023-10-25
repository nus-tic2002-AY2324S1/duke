package com.tina.exception;

import com.tina.command.Command;
import com.tina.command.CommandEnum;

public class IncorrectParameterException extends DukeException {
    public IncorrectParameterException(CommandEnum command) {
        super("OOPS!!! Please use correct syntax: " + command.getSyntax());
    }

}
