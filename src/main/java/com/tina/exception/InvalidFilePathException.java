package com.tina.exception;

import java.nio.file.Path;

/**
 * Represents an Invalid file path exception.
 * Throws if the file path is invalid or file is not found.
 */
public class InvalidFilePathException extends TinaException {

    /**
     * Instantiates a new Invalid file path exception.
     *
     * @param path the path.
     */
    public InvalidFilePathException(Path path) {
        super("Failed to load from path: " + path.toString() + ", due to invalid path or missing file.");
    }

    /**
     * Instantiates a new Invalid file path exception.
     *
     * @param fileName the file name.
     */
    public InvalidFilePathException(String fileName) {
        super("Failed to archie due to invalid path file name: " + fileName);
    }
}
