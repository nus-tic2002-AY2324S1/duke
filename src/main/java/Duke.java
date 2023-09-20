import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String echo;
        Shelf tasklist = new Shelf();
        Scanner in = new Scanner(System.in);

        Text.printMessage(Text.Message.LOGO);
        Text.printMessage(Text.Message.GREETING);

        while(true){
            echo = in.nextLine();
            // lower case
            String lecho = echo.toLowerCase();
            if(lecho.equals("bye")){
                Text.printMessage(Text.Message.BYE);
                break;
            }else if(lecho.equals("list")) {
                tasklist.listShelf();
            }else if(lecho.contains("mark")){
                tasklist.markTask(echo);
            } else {
                tasklist.addItem(echo);
            }
        }

    }

}