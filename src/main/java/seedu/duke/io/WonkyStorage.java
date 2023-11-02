package seedu.duke.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandArgument;
import seedu.duke.commands.WonkyMode;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeStorageException;

/**
 * Handles the storage of user input commands.
 */
public class WonkyStorage {

    private static final String STORAGE_PATH = "./storage.txt";
    private static final File STORAGE_FILE = new File(STORAGE_PATH);

    /**
     * Initializes the storage file and loads previous commands if in normal mode.
     *
     * @param mode the current mode of the program.
     * @throws DukeException if there is an error initializing the storage file or loading previous commands.
     */
    public static void startUp(WonkyMode mode) throws DukeException {
        if (WonkyMode.NORMAL.equals(mode)) {
            try {
                if (!STORAGE_FILE.isFile()) {
                    STORAGE_FILE.createNewFile();
                    WonkyLogger.initialiseStorage(true);
                } else {
                    WonkyLogger.setIsLoading(true);
                    WonkyLogger.initialiseStorage(false);
                    Scanner fileScanner = new Scanner(STORAGE_FILE);
                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        WonkyScanner.processNextLine(line);
                    }
                    fileScanner.close();
                    WonkyLogger.setIsLoading(false);
                }
            } catch (Exception e) {
                throw new DukeStorageException(e);
            }
        }
    }

    /**
     * Saves the given list of command arguments to the storage file.
     *
     * @param cmdArgs the list of command arguments to save.
     * @throws DukeException if there is an error saving the command arguments to the storage file.
     */
    public static void save(List<CommandArgument> cmdArgs) throws DukeException {
        if (WonkyMode.NORMAL.equals(WonkyLogger.getMode())) {
            try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(STORAGE_FILE, false));
            ) {
                for (CommandArgument cmdArg : cmdArgs) {
                    if (
                        !Command.BYE.equals(cmdArg.getCmd())
                        && !Command.LIST.equals(cmdArg.getCmd())
                        && !Command.FIND.equals(cmdArg.getCmd())
                    ) {
                        writer.write(cmdArg.getCmdLitr() + " " + cmdArg.getArgStr());
                        writer.newLine();
                    }
                }
            } catch (Exception e) {
                throw new DukeStorageException(e);
            }
        }
    }
}
