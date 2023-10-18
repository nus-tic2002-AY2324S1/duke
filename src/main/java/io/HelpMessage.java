package io;

import static io.CrabyMessage.SPACES;
import static io.CrabyMessage.LINE;

public class HelpMessage {
    public static void printHelpMessage() {
        System.out.println(
                SPACES + "    ❉⊱•  Welcome to Craby's help page! •⊰❉" + System.lineSeparator() +
                SPACES + "╚════ ▣ Here are the list of commands: ▣ ════╝" + System.lineSeparator() +

                SPACES + "1.  list - list all the tasks" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. list" + System.lineSeparator() +

                SPACES + "2.  <task description> - add a todo task" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. read book" + System.lineSeparator() +

                SPACES + "3.  <task description> /by <dd/mm/yyy hhmm> - add a deadline task" + System.lineSeparator() +
                SPACES + "    Note: you can use /by Mon -> CrabY will automatically fill the date (next Monday) for you" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. TIC2002 quiz 8 /by 2/12/2023 1800" + System.lineSeparator() +

                SPACES + "4.  event <task description> /from <dd/mm/yyy hhmm> /to <dd/mm/yyy hhmm> - add an event task" + System.lineSeparator() +
                SPACES + "    Note: you can use /from Fri -> CrabY will automatically fill the date (next Friday) for you" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. project meeting /from 2/12/2023 1800 /to 2/12/2023 2000" + System.lineSeparator() +

                SPACES + "5.  mark <task number> - mark a task as DONE" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. mark 1" + System.lineSeparator() +

                SPACES + "6.  unmark <task number> - mark a task as UNDONE" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. unmark 1" + System.lineSeparator() +

                SPACES + "7.  delete <task number> - delete a task" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. delete 1" + System.lineSeparator() +

                SPACES + "8.  delete all - delete all the tasks" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. delete all" + System.lineSeparator() +

                SPACES + "9.  find <keyword> - find a task with the keyword" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. find book" + System.lineSeparator() +

                SPACES + "10. sort <type/typerevert/t/tr/date/d> - sort the list by type or date" + System.lineSeparator() +
                SPACES + "    Note: type1 or t1 will sort the list by type in revert order" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. sort t" + System.lineSeparator() +

                SPACES + "11. undo - undo your previous command (except: list, find, help, blah commands)" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. undo" + System.lineSeparator() +

                SPACES + "12. help - show the list of commands" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. help" + System.lineSeparator() +

                SPACES + "13. bye - exit the program" + System.lineSeparator() +
                SPACES + "    ╰┈➤e.g. bye" + System.lineSeparator() + System.lineSeparator() +

                SPACES + "*** NOTE: if you use the same name that used before" + System.lineSeparator() +
                SPACES + "╰┈➤CrabY will load the data and you can continue to use the previous list of tasks." + System.lineSeparator() +
                LINE);
    }
}