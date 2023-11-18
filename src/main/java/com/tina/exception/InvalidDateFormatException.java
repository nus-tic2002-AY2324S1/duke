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
        super("OOPS!!! Invalid date or invalid date format. Please use format: dd/mm/yyyy, e.g., 2/12/2019");
    }
}
