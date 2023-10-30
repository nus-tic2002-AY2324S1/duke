package com.tina.exception;

/**
 * The type Invalid file format exception.
 * Thrown when the file format is invalid.
 */
public class InvalidFileFormatException extends DukeException {
    /**
     * Instantiates a new Invalid file format exception.
     */
    public InvalidFileFormatException() {
        super("Failed to load from file due to invalid file format or corrupted file.");
    }
}
