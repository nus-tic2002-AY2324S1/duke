package io;

import static io.CrabyMessage.blank;
import static io.CrabyMessage.line;

public class HelpMessage {
    public static void printHelpMessage() {
        System.out.println(blank + "     ❉⊱• Welcome to Craby's help page! •⊰❉");
        System.out.println(blank + "╚════ ▣ Here are the list of commands: ▣ ════╝");

        System.out.println(blank + blank + "1. list - list all the tasks");
        System.out.println(blank + blank + "   ╰┈➤e.g. list");

        System.out.println(blank + blank + "2. <task description> - add a todo task");
        System.out.println(blank + blank + "   ╰┈➤e.g. read book");

        System.out.println(blank + blank + "3. <task description> /by <dd/mm/yyy hhmm> - add a deadline task");
        System.out.println(blank + blank + "   Note: you can use /by Mon -> CrabY will automatically fill the date (next Monday) for you");
        System.out.println(blank + blank + "   ╰┈➤e.g. TIC2002 quiz 8 /by 2/12/2023 1800");

        System.out.println(blank + blank + "4. event <task description> /from <dd/mm/yyy hhmm> /to <dd/mm/yyy hhmm> - add an event task");
        System.out.println(blank + blank + "   Note: you can use /from Fri -> CrabY will automatically fill the date (next Friday) for you");
        System.out.println(blank + blank + "   ╰┈➤e.g. project meeting /from 2/12/2023 1800 /to 2/12/2023 2000");

        System.out.println(blank + blank + "5. mark <task number> - mark a task as DONE");
        System.out.println(blank + blank + "   ╰┈➤e.g. mark 1");

        System.out.println(blank + blank + "7. unmark <task number> - mark a task as UNDONE");
        System.out.println(blank + blank + "   ╰┈➤e.g. unmark 1");

        System.out.println(blank + blank + "8. delete <task number> - delete a task");
        System.out.println(blank + blank + "   ╰┈➤e.g. delete 1");

        System.out.println(blank + blank + "9. deleteall - delete all the tasks");
        System.out.println(blank + blank + "   ╰┈➤e.g. deleteall");

        System.out.println(blank + blank + "10. find <keyword> - find a task with the keyword");
        System.out.println(blank + blank + "   ╰┈➤e.g. find book");

        System.out.println(blank + blank + "11. sort <type/type1/t/t1/date/d> - sort the list by type or date");
        System.out.println(blank + blank + "    Note: type1 or t1 will sort the list by type in revers");
        System.out.println(blank + blank + "   ╰┈➤e.g. sort t");

        System.out.println(blank + blank + "12. help - show the list of commands");
        System.out.println(blank + blank + "   ╰┈➤e.g. help");

        System.out.println(blank + blank + "13. bye - exit the program");
        System.out.println(blank + blank + "   ╰┈➤e.g. bye");
        System.out.println(line);
    }
}
