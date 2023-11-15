package com.tina.exception;

/**
 * Represents an IO exception.
 * Throws if any IO error occurs.
 */
public class IOException extends TinaException {
    /**
     * Instantiates a new IO exception.
     * Throws error message if IO error occurs.
     */
    public IOException() {
        super("Failed to write into file due to IO errors.");
    }
}
