package com.tina.exception;

/**
 * The parent Duke exception.
 */
public abstract class DukeException extends Exception {
    /**
     * Instantiates a new Duke exception.
     *
     * @param message the message
     */
    public DukeException(String message) {
        super(message);
    }
}
