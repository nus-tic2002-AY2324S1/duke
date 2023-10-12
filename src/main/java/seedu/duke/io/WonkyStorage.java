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

public class WonkyStorage {

    final static String STORAGE_PATH = "./storage.txt";
    final static File STORAGE_FILE = new File(STORAGE_PATH);
    
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
                throw new DukeStorageException(e.getMessage());
            }
        }
    }

    public static void save(List<CommandArgument> cmdArgs) throws DukeException {
        try (
            BufferedWriter writer = new BufferedWriter(new FileWriter(STORAGE_FILE, false));
        ) {
            for (CommandArgument cmdArg : cmdArgs) {
                if (
                    !Command.BYE.equals(cmdArg.getCmd()) &&
                    !Command.LIST.equals(cmdArg.getCmd())
                ) {
                    writer.write(cmdArg.getCmdLitr() + " " + cmdArg.getArgStr());
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            throw new DukeStorageException(e.getMessage());
        }
    }
}
