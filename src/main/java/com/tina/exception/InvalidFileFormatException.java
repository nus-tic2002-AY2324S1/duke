package com.tina.exception;

public class InvalidFileFormatException extends DukeException {
    public InvalidFileFormatException() {
        super("Failed to load from file due to invalid file format or corrupted file.");
    }
}
