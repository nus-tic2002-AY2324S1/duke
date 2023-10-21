// JoshuaUi.java handles chatbot-related logic

package wargames;

public class JoshuaUi {

    public JoshuaUi(){

    }

    public static void joshuaSays(String str){
        str = str.toUpperCase();
        System.out.println(str + "\n");
    }

    public static void printGreetings(){
        joshuaSays("GREETINGS PROFESSOR FALKEN.\n\n"
                     + "SHALL WE PLAY A GAME?");
    }

}
