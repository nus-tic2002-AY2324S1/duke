package duke.command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleOutputRedirector {
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    public static void redirect() {
        System.setOut(new PrintStream(outputStream));
    }

    public static String getOutput() {
        return outputStream.toString();
    }

    public static void reset() {
        System.setOut(originalOut);
        outputStream.reset();
    }
}
