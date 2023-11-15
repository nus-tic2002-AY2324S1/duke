package com.tina.exception;


/**
 * Represents a Existed file exception.
 */
public class ExistedFileException extends TinaException {
    /**
     * Instantiates a new Existed File exception.
     * Passes message if the file is already existed.
     *
     * @param fileName the file name.
     */
    public ExistedFileException(String fileName) {
        super("Failed to archive, a file with name (" + fileName + ") is already existed.");
    }
}
