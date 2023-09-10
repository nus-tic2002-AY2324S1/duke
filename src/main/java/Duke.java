import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo ="\n" +
                "╭━━━╮╱╱╱╱╱╭╮\n" +
                "┃╭━╮┃╱╱╱╱╱┃┃\n" +
                "┃╰━━┳━━┳━━┫╰━┳━━┳━╮\n" +
                "╰━━╮┃╭╮┃╭╮┃╭╮┃╭╮┃╭╮╮\n" +
                "┃╰━╯┃╰╯┃╰╯┃┃┃┃╰╯┃┃┃┃\n" +
                "╰━━━┻━━┫╭━┻╯╰┻━━┻╯╰╯\n" +
                "╱╱╱╱╱╱╱┃┃\n" +
                "╱╱╱╱╱╱╱╰╯\n";
        String lineBreak = "****************************************";
        System.out.println(logo+"Hello, I'm Sophon:). \nHow can i assist you today?");
        System.out.println(lineBreak);

        Scanner scanner = new Scanner(System.in);
        while(true){
            String userInput = scanner.nextLine();
            if(Objects.equals(userInput.toUpperCase(), "BYE")){
                System.out.println(lineBreak);
                System.out.println("Bye. See you next time!");
                System.out.println(lineBreak);
                break;
            }else{
                System.out.println(lineBreak);
                System.out.println(userInput);
                System.out.println(lineBreak);
            }
        }
    }
}
