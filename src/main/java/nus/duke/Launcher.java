package nus.duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    private static final String CLI = "cli";
    private static final String GUI = "gui";

    /**
     * Runs the application.
     *
     * @param args The input arguments passed to the application.
     */
    public static void main(String[] args) {
        String ui = null;
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase(CLI)) {
                ui = CLI;
            } else if (args[0].equalsIgnoreCase(GUI)) {
                ui = GUI;
            }
        }
        if (ui == null) {
            ui = System.console() != null ? CLI : GUI;
        }
        if (ui.equals(CLI)) {
            Duke duke = new Duke();
            duke.run();
        } else {
            Application.launch(Main.class, args);
        }
    }
}
