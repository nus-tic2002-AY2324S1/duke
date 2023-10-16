package io;

import static io.CrabyMessage.blank;
import static io.CrabyMessage.line;

public class HelpMessage {
    public static void printHelpMessage() {
        System.out.println(blank + "     ❉⊱• Welcome to Craby's help page! •⊰❉");
        System.out.println(blank + "╚════ ▣ Here are the list of commands: ▣ ════╝");

        System.out.println(blank + "1.  list - list all the tasks");
        System.out.println(blank + "    ╰┈➤e.g. list");

        System.out.println(blank + "2.  <task description> - add a todo task");
        System.out.println(blank + "    ╰┈➤e.g. read book");

        System.out.println(blank + "3.  <task description> /by <dd/mm/yyy hhmm> - add a deadline task");
        System.out.println(blank + "    Note: you can use /by Mon -> CrabY will automatically fill the date (next Monday) for you");
        System.out.println(blank + "    ╰┈➤e.g. TIC2002 quiz 8 /by 2/12/2023 1800");

        System.out.println(blank + "4.  event <task description> /from <dd/mm/yyy hhmm> /to <dd/mm/yyy hhmm> - add an event task");
        System.out.println(blank + "    Note: you can use /from Fri -> CrabY will automatically fill the date (next Friday) for you");
        System.out.println(blank + "    ╰┈➤e.g. project meeting /from 2/12/2023 1800 /to 2/12/2023 2000");

        System.out.println(blank + "5.  mark <task number> - mark a task as DONE");
        System.out.println(blank + "    ╰┈➤e.g. mark 1");

        System.out.println(blank + "6.  unmark <task number> - mark a task as UNDONE");
        System.out.println(blank + "    ╰┈➤e.g. unmark 1");

        System.out.println(blank + "7.  delete <task number> - delete a task");
        System.out.println(blank + "    ╰┈➤e.g. delete 1");

        System.out.println(blank + "8.  deleteall - delete all the tasks");
        System.out.println(blank + "    ╰┈➤e.g. deleteall");

        System.out.println(blank + "9.  find <keyword> - find a task with the keyword");
        System.out.println(blank + "    ╰┈➤e.g. find book");

        System.out.println(blank + "10. sort <type/typerevert/t/tr/date/d> - sort the list by type or date");
        System.out.println(blank + "    Note: type1 or t1 will sort the list by type in revert order");
        System.out.println(blank + "    ╰┈➤e.g. sort t");

        System.out.println(blank + "11. undo - undo your previous command (except: list, find, help, blah commands)");
        System.out.println(blank + "    ╰┈➤e.g. undo");

        System.out.println(blank + "12. help - show the list of commands");
        System.out.println(blank + "    ╰┈➤e.g. help");

        System.out.println(blank + "13. bye - exit the program");
        System.out.println(blank + "    ╰┈➤e.g. bye");
        System.out.println(line);
    }
}
