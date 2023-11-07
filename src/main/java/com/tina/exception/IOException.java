package com.tina.exception;

public class IOException extends TinaException {
    /**
     * Instantiates a new IO exception.
     * Thrown when IO error occurs.
     */
    public IOException() {
        super("Failed to write into file due to IO errors.");
    }
}
