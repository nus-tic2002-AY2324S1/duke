import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "__________              .__    .__ \n"
                + "\\______   \\__ __   ____ |  |__ |__|\n"
                + " |     ___/  |  \\_/ ___\\|  |  \\|  |\n"
                + " |    |   |  |  /\\  \\___|   Y  \\  |\n"
                + " |____|   |____/  \\___  >___|  /__|\n"
                + "                      \\/     \\/    \n";
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");

        String echo = "";
        Scanner typed = new Scanner(System.in);
        while(!echo.equals("bye")){
            echo = typed.nextLine();
            if(!echo.equals("bye")){
                System.out.println(echo);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
