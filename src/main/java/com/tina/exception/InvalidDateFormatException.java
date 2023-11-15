package com.tina.exception;

/**
 * Represents a Invalid date format exception.
 * Throws if date format is invalid.
 */
public class InvalidDateFormatException extends TinaException {

    /**
     * Instantiates a new Invalid date format exception.
     */
    public InvalidDateFormatException() {
        super("OOPS!!! Invalid date format. Please use format: yyyy-mm-dd, e.g., 2023-10-30");
    }
}
