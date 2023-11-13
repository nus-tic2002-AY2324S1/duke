// JoshuaUi.java handles chatbot-related logic

package joshua;

import java.util.Scanner;

public class JoshuaUi {
    private final Scanner scanner;
    public JoshuaUi() {
        this.scanner = new Scanner(System.in);
    }

    public void joshuaSays(String str) {
        str = str.toUpperCase();
        System.out.println(str + "\n");
    }

    public void printGreetings() {
        joshuaSays("GREETINGS PROFESSOR FALKEN.\n\n"
                     + "SHALL WE PLAY A GAME?\n\n"
                     + "\tLIST\n"
                     + "\tMARK #\n"
                     + "\tUNMARK #\n"
                     + "\tTODO\n"
                     + "\tDEADLINE /BY\n"
                     + "\tEVENT /FROM /TO\n"
                     + "\tFIND\n"
                     + "\tDELETE #\n"
                     + "\tBYE\n"
                     + "\tGLOBAL THERMONUCLEAR WAR");
    }

    public void printSuccessfulLoad() {
        joshuaSays("YOUR TASK LIST HAS BEEN LOADED FROM JOSHUA.TXT\n___");
    }

    public void printExitMessage() {
        joshuaSays("GOODBYE.");
    }

    public void printSaveMessage() {
        joshuaSays("SAVING TASK LIST IN JOSHUA.TXT...");
    }

    public void printLoadingError() {
        joshuaSays("UNABLE TO LOAD TASK LIST FROM STORAGE.");
        joshuaSays("I HAVE CREATED AN EMPTY TASK LIST FOR YOU INSTEAD, PROFESSOR FALKEN.");
    }

    public String readCommand() {
        System.out.print(">> ");
        String input = scanner.nextLine();
        input = input.toLowerCase();
        return input;
    }

}
