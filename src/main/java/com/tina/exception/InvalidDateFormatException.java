package com.tina.exception;

/**
 * The type Invalid date format exception.
 * Thrown when date format is invalid.
 */
public class InvalidDateFormatException extends DukeException {

    /**
     * Instantiates a new Invalid date format exception.
     */
    public InvalidDateFormatException() {
        super("OOPS!!! Invalid date format. Please use format: yyyy-mm-dd, e.g., 2023-10-30");
    }
}
