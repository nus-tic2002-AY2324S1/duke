package duke.storage;

import duke.exception.DukeException;

public class Storage {

    private String filename;
    public Storage(String filePath) {
        filename = filePath;
    }

    public int load() throws DukeException {
        return 0;
    }
}
