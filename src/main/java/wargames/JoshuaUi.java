// JoshuaUi.java handles chatbot-related logic

package wargames;

public class JoshuaUi {

    public JoshuaUi(){

    }

    public static void joshuaSays(String str) {
        str = str.toUpperCase();
        System.out.println(str + "\n");
    }

    public static void printGreetings() {
        joshuaSays("GREETINGS PROFESSOR FALKEN.\n\n"
                     + "SHALL WE PLAY A GAME?");
    }

    public static void printExitMessage() {
        joshuaSays("GOODBYE.");
    }

    public static void printSaveMessage() {
        joshuaSays("SAVING TASK LIST IN JOSHUA.TXT...");
    }

    public static void printLoadingError() {
        joshuaSays("UNABLE TO LOAD TASK LIST FROM STORAGE.");
        joshuaSays("I HAVE CREATED AN EMPTY TASK LIST FOR YOU INSTEAD, PROFESSOR FALKEN.");
    }

}
