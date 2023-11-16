package io;

/**
 * HelpMessage class is a class that handles the help message.
 * It has a method to print out the list of commands that what CrabY can do.
 */
public class HelpMessage extends CrabyMessage {
    /**
     * Sends the help message to the user.
     */
    public static void printHelpMessage() {
        System.out.println(SPACES + "    ❉⊱•  Welcome to Craby's help page! •⊰❉");
        System.out.println(SPACES + "╚════ ▣ Here are the list of commands: ▣ ════╝");

        System.out.println(SPACES + "1.  list - list all the tasks");
        System.out.println(SPACES + "    ╰┈➤e.g. list");

        System.out.println(SPACES + "2.  <task description> - add a todo task");
        System.out.println(SPACES + "    ╰┈➤e.g. read book");

        System.out.println(SPACES + "3.  <task description> /by <dd/mm/yyyy hhmm> - add a deadline task");
        System.out.println(SPACES + "    ╰┈➤e.g. TIC2002 quiz 8 /by 2/12/2023 1800");
        System.out.println(SPACES + "    Note: you can type 'dates' to see useful keywords that help you enter dates " +
                                   "faster");

        System.out.println(SPACES +
                "4.  event <task description> /from <dd/mm/yyyy hhmm> /to <dd/mm/yyy hhmm> - add an event task");
        System.out.println(SPACES + "    ╰┈➤e.g. project meeting /from 02/12/2023 1800 /to 02/12/2023 2000");
        System.out.println(SPACES + "    Note: you can type 'dates' to see useful keywords that help you enter dates " +
                                   "faster");

        System.out.println(SPACES + "5.  mark <task number> - mark a task as DONE");
        System.out.println(SPACES + "    ╰┈➤e.g. mark 1");
        System.out.println(SPACES + "    Note: mark all -> CrabY will mark ALL the tasks as DONE");

        System.out.println(SPACES + "6.  unmark <task number> - mark a task as NOT DONE YET");
        System.out.println(SPACES + "    ╰┈➤e.g. unmark 1");
        System.out.println(SPACES + "    Note: unmark all -> CrabY will mark ALL the tasks as NOT DONE YET");

        System.out.println(SPACES + "7.  delete <task number> - delete a task");
        System.out.println(SPACES + "    ╰┈➤e.g. delete 1");
        System.out.println(SPACES + "    Note: delete all -> CrabY will delete ALL the tasks");
    }

    /**
     * Sends the help message to the user.
     */
    public static void printHelpMessage1() {
        System.out.println(SPACES + "8.  find <keyword> - find a task with the keyword");
        System.out.println(SPACES + "    ╰┈➤e.g. find book");

        System.out.println(SPACES + "9.  sort <type/type-r/t/tr/date/d> - sort the list by type or date");
        System.out.println(SPACES + "    Note: type-r or tr will sort the list by type in revert order");
        System.out.println(SPACES + "    ╰┈➤e.g. sort t");

        System.out.println(SPACES + "10. undo - undo your previous command (except: list, find, help, blah commands)");
        System.out.println(SPACES + "    ╰┈➤e.g. undo");

        System.out.println(SPACES + "11. switch - switch to another list");
        System.out.println(SPACES +
                "    ╰┈➤after that CrabY will ask you to enter the name of the list that you want to switch to");

        System.out.println(SPACES + "12. help - show the list of commands");
        System.out.println(SPACES + "    ╰┈➤e.g. help");

        System.out.println(SPACES + "13. bye - exit the program");
        System.out.println(SPACES + "    ╰┈➤e.g. bye");

        System.out.println();

        System.out.println(SPACES + "*** NOTE: if you use the same [List-name] that used before");
        System.out.println(SPACES + "╰┈➤you can continue to use the previous list of tasks.");

        System.out.println(LINE);

    }
}