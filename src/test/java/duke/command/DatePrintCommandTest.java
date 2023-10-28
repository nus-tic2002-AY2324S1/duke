package duke.command;

import duke.data.UserKeywordArgument;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.TestUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

public class DatePrintCommandTest {
    @TempDir
    static Path testFolder;
    private static final PrintStream standardOut = System.out;
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void executeCommand_availableDate_foundDate() throws Exception {
        TaskList tasks = new TaskList(TestUtil.getTestTasks());
        Ui ui = new Ui();
        Storage storage = getTempStorage();
        DatePrintCommand dPrint = new DatePrintCommand();
        dPrint.executeCommand(tasks, ui, storage, new UserKeywordArgument("dprint 02/12/2023"));
        assertEquals(getSuccessString(tasks, ui), outputStreamCaptor.toString());
    }

//    @Test
//    public void executeCommand_notAvailableDate_noDateFound() throws Exception {
//        TaskList tasks = new TaskList(TestUtil.getTestTasks());
//        Ui ui = new Ui();
//        Storage storage = getTempStorage();
//        DatePrintCommand dPrint = new DatePrintCommand();
//        dPrint.executeCommand(tasks, ui, storage, new UserKeywordArgument("dprint 2/6/2023"));
//        assertEquals(getNotSuccessString(tasks, ui), outputStreamCaptor.toString());
//    }

    private String getSuccessString(TaskList taskList, Ui ui) {
        return Ui.getPrintLinePrefixSpace() + Ui.DIVIDER + Ui.NEWLINE +
                Ui.getPrefixSpace() + DatePrintCommand.TASKS_IN_THE_LIST + Ui.NEWLINE +
                Ui.getPrefixSpace() + "1." + taskList.get(1).toString() + Ui.NEWLINE +
                Ui.getPrefixSpace() + "2." + taskList.get(2).toString() + Ui.NEWLINE +
                Ui.getPrintLinePrefixSpace() + Ui.DIVIDER + Ui.NEWLINE;
    }

    private String getNotSuccessString(TaskList taskList, Ui ui) {
        return Ui.getPrintLinePrefixSpace() + Ui.DIVIDER + Ui.NEWLINE +
                Ui.getPrefixSpace() + DatePrintCommand.MESSAGE_LIST_IS_EMPTY + Ui.NEWLINE +
                Ui.getPrintLinePrefixSpace() + Ui.DIVIDER + Ui.NEWLINE;
    }

    @AfterAll
    public static void tearDown() {
        System.setOut(standardOut);
    }

    public static Storage getTempStorage() throws Exception {
        return new Storage(testFolder.resolve("temp.txt").toString());
    }

}
