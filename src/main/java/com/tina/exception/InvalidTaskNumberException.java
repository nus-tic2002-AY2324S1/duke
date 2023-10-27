package com.tina.exception;

public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException () {
        super("OOPS!!! Invalid task number");
    }
}
