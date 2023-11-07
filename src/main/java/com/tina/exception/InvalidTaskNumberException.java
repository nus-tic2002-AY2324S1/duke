package com.tina.exception;

/**
 * The type Invalid task number exception.
 * Thrown when task number is invalid.
 */
public class InvalidTaskNumberException extends TinaException {
    /**
     * Instantiates a new Invalid task number exception.
     */
    public InvalidTaskNumberException () {
        super("OOPS!!! Invalid task number");
    }
}
