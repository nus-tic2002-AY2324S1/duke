import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String echo;
        Scanner in = new Scanner(System.in);

        Text.printMessage(Text.Message.LOGO);
        Text.printMessage(Text.Message.GREETING);

        while(true){
            echo = in.nextLine();
            if(echo.equals("bye")){
                Text.printMessage(Text.Message.BYE);
                break;
            }else if(echo.equals("list")){
                Shelf.listShelf();
            } else {
                Shelf.addItem(echo);

            }
        }

    }

}