// Joshua.java handles chatbot-related logic

package wargames;

public class Joshua {
    private static MyList myList = new MyList();

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

    public static void printMyList(){
        joshuaSays("Here is your current list:");
        joshuaSays(myList.toString());
    }

    public static void addTaskToList(String task){
        myList.addToList(task);
    }

    public static void addTaskToList(String[] taskArr){
        myList.addToList(taskArr);
    }
}
