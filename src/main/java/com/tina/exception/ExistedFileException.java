package com.tina.exception;


public class ExistedFileException extends TinaException {
    /**
     * Instantiates a new Existed File exception.
     * Thrown when the file is already existed.
     */
    public ExistedFileException(String fileName) {
        super("Failed to archive, a file with name (" + fileName + ") is already existed.");
    }
}
