import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        UI ui = new UI();
        Bot bot = new Bot();

        ui.greet();
        
        Scanner in = new Scanner(System.in);
        bot.start(in);
    }

}
