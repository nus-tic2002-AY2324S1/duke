package com.tina.exception;

public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException () {
        super("OOPS!!! You're giving invalid task number");
    }
}
