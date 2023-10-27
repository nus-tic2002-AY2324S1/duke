package duke.filehandler;

/**
 * Represents an `FileCorruptedException` for a corrupted Duke data file.
 */
class FileCorruptedException extends duke.filehandler.FileException {

  public FileCorruptedException() {

    super("File is corrupted, proceed to start a new session without data loading.");
  }

}
