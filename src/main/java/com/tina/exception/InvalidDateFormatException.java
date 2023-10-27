package com.tina.exception;

public class InvalidDateFormatException extends DukeException {

    public InvalidDateFormatException() {
        super("OOPS!!! Invalid date format. Please use format: yyyy-mm-dd, e.g., 2023-10-30");
    }
}
