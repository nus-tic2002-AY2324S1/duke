package duke.command;

import duke.common.Message;
import duke.data.UserKeywordArgument;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
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
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    public void shouldPrintTheCorrectString() {
        System.out.print("hello");
        assertEquals("hello", outputStreamCaptor.toString().trim());
    }

    @Test
    public void executeCommand_foundDate_success() throws Exception {
        TaskList tasks = new TaskList(TestUtil.getTestTasks());
        Ui ui = new Ui();
        Storage storage = getTempStorage();
        DatePrintCommand dPrint = new DatePrintCommand();
        dPrint.executeCommand(tasks, ui, storage, new UserKeywordArgument("dprint 2/12/2023"));
        assertEquals(getSuccessString(tasks, ui), outputStreamCaptor.toString().trim());
    }

    private String getSuccessString(TaskList taskList, Ui ui) {
        String out = Message.concat(
        Ui.getPrintLinePrefixSpace() + Ui.DIVIDER,
                Ui.getPrefixSpace() + DatePrintCommand.TASKS_IN_THE_LIST,
                Ui.getPrefixSpace() + "1." + taskList.get(1).toString(),
                Ui.getPrefixSpace() + "2." + taskList.get(2).toString(),
                Ui.getPrintLinePrefixSpace() + Ui.DIVIDER);
        return out;
    }

    @AfterAll
    public static void tearDown() {
        System.setOut(standardOut);
    }

    public static Storage getTempStorage() throws Exception {
        return new Storage(testFolder.resolve("temp.txt").toString());
    }

}
