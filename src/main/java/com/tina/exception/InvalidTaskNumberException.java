package com.tina.exception;

/**
 * Represents an Invalid task number exception.
 * Throws if task number is invalid.
 */
public class InvalidTaskNumberException extends TinaException {
    /**
     * Instantiates a new Invalid task number exception.
     */
    public InvalidTaskNumberException() {
        super("OOPS!!! Invalid task number");
    }
}
