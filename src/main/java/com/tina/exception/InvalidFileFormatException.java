package com.tina.exception;

/**
 * Represents a Invalid file format exception.
 * Throws if the file format is invalid.
 */
public class InvalidFileFormatException extends TinaException {
    /**
     * Instantiates a new Invalid file format exception.
     */
    public InvalidFileFormatException() {
        super("Failed to load from file due to invalid file format or corrupted file.");
    }
}
