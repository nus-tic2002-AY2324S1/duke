package seedu.duke.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandArgument;
import seedu.duke.commands.WonkyMode;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeStorageException;
import seedu.duke.task.WonkyManager;

/**
 * Handles the storage of user input commands.
 */
public class WonkyStorage {

    private static final String STORAGE_PATH = "./storage.txt";
    private static final File STORAGE_FILE = new File(STORAGE_PATH);
    private static final File STASH_FOLDER = new File("./stash/");

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

    public static void save(List<CommandArgument> cmdArgs) throws DukeException {
        save(cmdArgs, STORAGE_FILE);
    }

    /**
     * Saves the given list of command arguments to the storage file.
     *
     * @param cmdArgs the list of command arguments to save.
     * @throws DukeException if there is an error saving the command arguments to the storage file.
     */
    public static void save(List<CommandArgument> cmdArgs, File fileToStore) throws DukeException {
        assert cmdArgs != null : "Command arguments list should not be null";
        assert fileToStore != null : "Storage file should not be null";
        if (WonkyMode.NORMAL.equals(WonkyLogger.getMode())) {
            try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileToStore, false));
            ) {
                for (CommandArgument cmdArg : cmdArgs) {
                    if (
                        !Command.BYE.equals(cmdArg.getCmd())
                        && !Command.LIST.equals(cmdArg.getCmd())
                        && !Command.FIND.equals(cmdArg.getCmd())
                        && !Command.STASH.equals(cmdArg.getCmd())
                        && !Command.HELP.equals(cmdArg.getCmd())
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

    public static List<String> getStashList() {
        if (STASH_FOLDER.exists() && STASH_FOLDER.isDirectory()) {
            return Arrays.asList(STASH_FOLDER.list());
        }
        return Arrays.asList();
    }

    public static void clearStash() {
        if (STASH_FOLDER.exists() && STASH_FOLDER.isDirectory()) {
            for (File file : STASH_FOLDER.listFiles()) {
                file.delete();
            }
        }
    }

    public static void popStash(String stashName) throws DukeException {
        if (STASH_FOLDER.exists() && STASH_FOLDER.isDirectory()) {
            File stashFile = new File(STASH_FOLDER, stashName);
            if (stashFile.exists() && stashFile.isFile()) {
                try {
                    WonkyLogger.setIsLoading(true);
                    WonkyManager.resetCmdArgs();
                    Scanner fileScanner = new Scanner(stashFile);
                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        WonkyScanner.processNextLine(line);
                    }
                    fileScanner.close();
                    stashFile.delete();
                    WonkyLogger.setIsLoading(false);
                } catch (Exception e) {
                    throw new DukeStorageException(e);
                }
            }
        }
    }

    public static void addStash(String stashName, List<CommandArgument> cmdArgs) throws DukeException {
        if (!STASH_FOLDER.exists()) {
            STASH_FOLDER.mkdir();
        }
        File stashFile = new File(STASH_FOLDER, stashName);
        if (!stashFile.isFile()) {
            try {
                stashFile.createNewFile();
            } catch (IOException e) {
                throw new DukeStorageException(e);
            }
        }
        save(cmdArgs, stashFile);
    }
}
