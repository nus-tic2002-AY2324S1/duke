package com.tina.exception;

/**
 * Represents a parent Duke exception.
 */
public abstract class TinaException extends Exception {
    /**
     * Instantiates a new Tina exception.
     *
     * @param message the message.
     */
    public TinaException(String message) {
        super(message);
    }
}
