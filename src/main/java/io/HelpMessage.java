package io;

import static io.CrabyMessage.SPACES;
import static io.CrabyMessage.LINE;

public class HelpMessage {
    public static void printHelpMessage() {
        System.out.println(SPACES + "    ❉⊱•  Welcome to Craby's help page! •⊰❉");
        System.out.println(SPACES + "╚════ ▣ Here are the list of commands: ▣ ════╝");

        System.out.println(SPACES + "1.  list - list all the tasks");
        System.out.println(SPACES + "    ╰┈➤e.g. list");

        System.out.println(SPACES + "2.  <task description> - add a todo task");
        System.out.println(SPACES + "    ╰┈➤e.g. read book");

        System.out.println(SPACES + "3.  <task description> /by <dd/mm/yyy hhmm> - add a deadline task");
        System.out.println(SPACES + "    Note: you can use /by Mon -> CrabY will automatically fill the date (next Monday)");
        System.out.println(SPACES + "    ╰┈➤e.g. TIC2002 quiz 8 /by 2/12/2023 1800");

        System.out.println(SPACES + "4.  event <task description> /from <dd/mm/yyy hhmm> /to <dd/mm/yyy hhmm> - add an event task");
        System.out.println(SPACES + "    Note: you can use /from Fri -> CrabY will automatically fill the date (next " + "Friday)");
        System.out.println(SPACES + "    ╰┈➤e.g. project meeting /from 2/12/2023 1800 /to 2/12/2023 2000");

        System.out.println(SPACES + "5.  mark <task number> - mark a task as DONE");
        System.out.println(SPACES + "    ╰┈➤e.g. mark 1");

        System.out.println(SPACES + "6.  unmark <task number> - mark a task as NOT DONE YET");
        System.out.println(SPACES + "    ╰┈➤e.g. unmark 1");

        System.out.println(SPACES + "7.  delete <task number> - delete a task");
        System.out.println(SPACES + "    ╰┈➤e.g. delete 1");

        System.out.println(SPACES + "8.  delete all - delete all the tasks");
        System.out.println(SPACES + "    ╰┈➤e.g. delete all");

        System.out.println(SPACES + "9.  find <keyword> - find a task with the keyword");
        System.out.println(SPACES + "    ╰┈➤e.g. find book");

        System.out.println(SPACES + "10. sort <type/type-r/t/tr/date/d> - sort the list by type or date");
        System.out.println(SPACES + "    Note: type-r or tr will sort the list by type in revert order");
        System.out.println(SPACES + "    ╰┈➤e.g. sort t");

        System.out.println(SPACES + "11. undo - undo your previous command (except: list, find, help, blah commands)");
        System.out.println(SPACES + "    ╰┈➤e.g. undo");

        System.out.println(SPACES + "12. switch - switch to another list");
        System.out.println(SPACES + "    ╰┈➤after that CarbY will ask you to enter the name of the list that you want to switch to");

        System.out.println(SPACES + "13. help - show the list of commands");
        System.out.println(SPACES + "    ╰┈➤e.g. help");

        System.out.println(SPACES + "14. bye - exit the program");
        System.out.println(SPACES + "    ╰┈➤e.g. bye");

        System.out.println();

        System.out.println(SPACES + "*** NOTE: if you use the same [List-name] that used before");
        System.out.println(SPACES + "╰┈➤you can continue to use the previous list of tasks.");

        System.out.println(LINE);

    }
}