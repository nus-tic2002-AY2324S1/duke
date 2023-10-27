package com.tina.exception;

import java.nio.file.Path;

public class InvalidFilePathException extends DukeException{

    public InvalidFilePathException(Path path) {
        super("Failed to load from path: "+ path.toString() + ", due to invalid path or missing file.");
    }
}
