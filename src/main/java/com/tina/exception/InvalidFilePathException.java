package com.tina.exception;

import java.nio.file.Path;

/**
 * The type Invalid file path exception.
 * Thrown when the file path is invalid or file is not found.
 */
public class InvalidFilePathException extends TinaException {

    /**
     * Instantiates a new Invalid file path exception.
     *
     * @param path the path
     */
    public InvalidFilePathException(Path path) {
        super("Failed to load from path: " + path.toString() + ", due to invalid path or missing file.");
    }

    public InvalidFilePathException(String fileName) {
        super("Failed to archie due to invalid path file name: " + fileName);
    }
}
