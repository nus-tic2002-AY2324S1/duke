package seedu.duke.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import seedu.duke.commands.CommandEnum;
import seedu.duke.commands.CommandArgument;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeStorageException;
import seedu.duke.helper.WonkyMode;
import seedu.duke.task.WonkyManager;

/**
 * Handles the storage of user input commands.
 */
public class WonkyStorage {

    private static WonkyStorage wonkyStorage;
    private WonkyLogger wonkyLogger;
    private WonkyManager wonkyManager;
    private WonkyScanner wonkyScanner;

    private final String STORAGE_PATH = "./storage.txt";
    private final File STORAGE_FILE = new File(STORAGE_PATH);
    private final File STASH_FOLDER = new File("./stash/");

    public WonkyStorage() {}

    public static WonkyStorage getInstance() {
        if (wonkyStorage == null) {
            wonkyStorage = new WonkyStorage();
        }
        return wonkyStorage;
    }

    public void setReferences(WonkyLogger wonkyLogger, WonkyManager wonkyManager, WonkyScanner wonkyScanner) {
        this.wonkyLogger = wonkyLogger;
        this.wonkyManager = wonkyManager;
        this.wonkyScanner = wonkyScanner;
    }

    /**
     * Initialises the storage file and loads previous commands if in normal mode.
     *
     * @param mode the current mode of the program.
     * @throws DukeException if there is an error initializing the storage file or loading previous commands.
     */
    public void startUp(WonkyMode mode) throws DukeException {
        if (WonkyMode.NORMAL.equals(mode)) {
            try {
                if (!STORAGE_FILE.isFile()) {
                    STORAGE_FILE.createNewFile();
                    wonkyLogger.initialiseStorage(true);
                } else {
                    wonkyLogger.setIsLoading(true);
                    wonkyLogger.initialiseStorage(false);
                    Scanner fileScanner = new Scanner(STORAGE_FILE);
                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        wonkyScanner.processNextLine(line);
                    }
                    fileScanner.close();
                    wonkyLogger.setIsLoading(false);
                }
            } catch (Exception e) {
                throw new DukeStorageException(e);
            }
        }
    }

    public void save(List<CommandArgument> cmdArgs) throws DukeException {
        save(cmdArgs, STORAGE_FILE);
    }

    /**
     * Saves the given list of command arguments to the storage file.
     *
     * @param cmdArgs the list of command arguments to save.
     * @throws DukeException if there is an error saving the command arguments to the storage file.
     */
    public void save(List<CommandArgument> cmdArgs, File fileToStore) throws DukeException {
        assert cmdArgs != null : "Command arguments list should not be null";
        assert fileToStore != null : "Storage file should not be null";
        if (WonkyMode.NORMAL.equals(wonkyLogger.getMode())) {
            try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileToStore, false));
            ) {
                for (CommandArgument cmdArg : cmdArgs) {
                    if (
                        !CommandEnum.BYE.equals(cmdArg.getCmd())
                            && !CommandEnum.LIST.equals(cmdArg.getCmd())
                            && !CommandEnum.FIND.equals(cmdArg.getCmd())
                            && !CommandEnum.STASH.equals(cmdArg.getCmd())
                            && !CommandEnum.HELP.equals(cmdArg.getCmd())
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

    public List<String> getStashList() {
        if (STASH_FOLDER.exists() && STASH_FOLDER.isDirectory()) {
            return Arrays.asList(STASH_FOLDER.list());
        }
        return Arrays.asList();
    }

    /**
     * Clears the all the stashed files.
     */
    public void clearStash() {
        if (STASH_FOLDER.exists() && STASH_FOLDER.isDirectory()) {
            for (File file : STASH_FOLDER.listFiles()) {
                file.delete();
            }
        }
    }

    /**
     * Pops the given stash file and executes the commands in it.
     *
     * @param stashName
     * @throws DukeException
     */
    public void popStash(String stashName) throws DukeException {
        if (STASH_FOLDER.exists() && STASH_FOLDER.isDirectory()) {
            File stashFile = new File(STASH_FOLDER, stashName);
            if (stashFile.exists() && stashFile.isFile()) {
                try {
                    wonkyLogger.setIsLoading(true);
                    wonkyManager.resetCmdArgs();
                    Scanner fileScanner = new Scanner(stashFile);
                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        wonkyScanner.processNextLine(line);
                    }
                    fileScanner.close();
                    stashFile.delete();
                    wonkyLogger.setIsLoading(false);
                } catch (Exception e) {
                    throw new DukeStorageException(e);
                }
            }
        }
    }

    /**
     * Adds the given list of command arguments to the given stash file.
     *
     * @param stashName
     * @param cmdArgs
     * @throws DukeException
     */
    public void addStash(String stashName, List<CommandArgument> cmdArgs) throws DukeException {
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
