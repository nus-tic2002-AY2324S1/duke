package com.tina;

import com.tina.command.Command;
import com.tina.exception.DukeException;
import com.tina.task.*;

public class Tina {

    private final Ui ui;
    private final TaskList taskList;

    public Tina() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    public static void main(String[] args) throws DukeException {
        new Tina().run();
    }

    public void run() {
        ui.showWelcome();
        boolean isBye = false;

        while (!isBye) {
            // format user input
            try {
                Command command = Parser.parseInputToCommand(ui.readInput());
                // start line
                ui.printDividerLine();
                command.execute(taskList, ui);
                isBye = command.getIsBye();
            } catch (DukeException e) {
                // end line
                ui.printDividerLine();
                ui.printLine(e.getMessage());
            }

            // end line
            ui.printDividerLine();
        }
    }

}



