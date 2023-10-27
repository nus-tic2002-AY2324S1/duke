package duke.filehandler;

/**
 * Represents a `FileException` class for file-related exceptions in Duke.
 */
abstract class FileException extends Exception {

  public FileException(String message) {

    super(message);
  }

}
