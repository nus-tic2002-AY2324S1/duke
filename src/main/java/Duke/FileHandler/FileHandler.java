package Duke.FileHandler;

/**
 * Represents a `FileHandler` class for handling files in Duke.
 */
abstract class FileHandler {
    // The default file path for Duke data
    String filePath = "./data/duke.txt";
}

/**
 * Represents a `FileException` class for file-related exceptions in Duke.
 */
abstract class FileException extends Exception {
    public FileException(String message) {
        super(message);
    }
}

/**
 * Represents an `FileCorruptedException` for a corrupted Duke data file.
 */
class FileCorruptedException extends FileException {
    public FileCorruptedException() {
        super("File is corrupted, proceed to start a new session without data loading.");
    }
}