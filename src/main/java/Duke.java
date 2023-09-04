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
        String[] list = new String[100];
        Scanner typed = new Scanner(System.in);
        int count = 0;
        while(!echo.equals("bye")){
            echo = typed.nextLine();
            if(!echo.equals("bye") && !echo.equals("list")){
                //add to array:
                list[count] = echo;
                count++;
                //System.out.println(echo);
                System.out.println("added: " + echo);
            } else if (echo.equals("list")) {
                for(int i = 1; i<= list.length; i++){
                    if(list[i-1]!=null) {
                        System.out.println(i + ". " + list[i - 1]);
                    }else{
                        break;
                    }
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
