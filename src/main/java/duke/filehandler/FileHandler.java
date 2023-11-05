package duke.filehandler;

/**
 * Represents a `FileHandler` class for handling files in Duke.
 */
abstract class FileHandler {

    // The default file path for Duke data
    private String filePath = "./data/duke.txt";

    public String getFilePath() {
        return filePath;
    }
}

