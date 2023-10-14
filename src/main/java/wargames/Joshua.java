// Joshua.java handles chatbot-related logic

package wargames;

public class Joshua {

    public Joshua(){

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
