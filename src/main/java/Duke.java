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
        String[] instructions;
        int count = 0;
        int store = 0;
        String[] updates = new String[100];
        while(!echo.matches("bye(.*)")){
            echo = typed.nextLine();
            if(echo.matches("mark(.*)") && echo.contains(" ")){
                instructions = echo.split(" ");
                store = Integer.parseInt(instructions[1]);
                if(list.length >= store){
                    //print
                    updates[store-1] = "X";
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + updates[store-1] + "]" + " " + list[store-1]);
                }
            }else if(echo.matches("unmark(.*)") && echo.contains(" ")){
                instructions = echo.split(" ");
                store = Integer.parseInt(instructions[1]);
                if(list.length >= store){
                    //print
                    updates[store-1] = " ";
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + updates[store-1] + "]" + " " + list[store-1]);
                }
            }else if(!echo.equals("bye") && !echo.equals("list") && !echo.equals("")){
                //add to array:
                list[count] = echo;
                updates[count] = " ";
                count++;
                //System.out.println(echo);
                System.out.println("added: " + echo);
            }else if (echo.matches("list(.*)")) {
                for(int i = 1; i<= list.length; i++){
                    if(list[i-1]!=null) {
                        System.out.println(i + "." + "[" + updates[i-1] + "]" + " " + list[i - 1]);
                    }else{
                        break;
                    }
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
