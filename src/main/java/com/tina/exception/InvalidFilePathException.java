package com.tina.exception;

import java.nio.file.Path;

/**
 * The type Invalid file path exception.
 * Thrown when the file path is invalid or file is not found.
 */
public class InvalidFilePathException extends DukeException{

    /**
     * Instantiates a new Invalid file path exception.
     *
     * @param path the path
     */
    public InvalidFilePathException(Path path) {
        super("Failed to load from path: "+ path.toString() + ", due to invalid path or missing file.");
    }
}
