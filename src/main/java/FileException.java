class FileException extends Exception {
    public FileException(String message) {
        super(message);
    }
}
class FileCorruptedException extends FileException {
    public FileCorruptedException() {
        super("File is corrupted, proceed to start a new session without data loading.");
    }
}
