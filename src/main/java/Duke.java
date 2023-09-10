import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String lineBreak = "****************************************";
        String logo ="\n" +
                "╭━━━╮╱╱╱╱╱╭╮\n" +
                "┃╭━╮┃╱╱╱╱╱┃┃\n" +
                "┃╰━━┳━━┳━━┫╰━┳━━┳━╮\n" +
                "╰━━╮┃╭╮┃╭╮┃╭╮┃╭╮┃╭╮╮\n" +
                "┃╰━╯┃╰╯┃╰╯┃┃┃┃╰╯┃┃┃┃\n" +
                "╰━━━┻━━┫╭━┻╯╰┻━━┻╯╰╯\n" +
                "╱╱╱╱╱╱╱┃┃\n" +
                "╱╱╱╱╱╱╱╰╯\n";

        // Greet the user and ask for input
        System.out.println(logo+"Hello, I'm Sophon:). \nHow can i assist you today?");
        System.out.println(lineBreak);

        // Create an array of string to store user's input
        String[] userInputArray = new String[100];
        // Counter to store the number of input stored in the array
        int n = 0;

        while(true){
            String userInput = scanner.nextLine();
            if(Objects.equals(userInput.toUpperCase(), "BYE")){
                System.out.println(lineBreak);
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println(lineBreak);
                break;
            }else{
                if(Objects.equals(userInput.toUpperCase(),"LIST")){
                    System.out.println(lineBreak);
                    for (int i = 0; i < n; i++) {
                        System.out.println((i + 1)+ ". " + userInputArray[i]);
                    }
                    System.out.println(lineBreak);
                }else{
                    userInputArray[n] = userInput;
                    n++;
                    System.out.println(lineBreak);
                    System.out.println("Added: " + userInput);
                    System.out.println(lineBreak);
                }
            }
        }
        scanner.close();
    }
}
