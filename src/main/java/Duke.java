import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;

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
        ArrayList<String> list = new ArrayList<String>();
        Scanner typed = new Scanner(System.in);
        String[] instructions;
        int count = 0;
        int store = 0; //counter for updates array
        String from = "";
        String to = "";
        String[] by = new String[100];
        String[] event = new String[100];
        String[] event2 = new String[100];
        ArrayList<String> list1 = new ArrayList<String>();


        while (!echo.matches("bye(.*)")) {
                    echo = typed.nextLine();
                    if (!echo.equals("bye") && !echo.equals("list")) {
                        if (echo.contains("/from") && echo.contains("/to")) {
                            event = echo.split("/from");
                            event2 = event[1].split("/to");
                            from = event2[0];
                            to = event2[1];
                            list.add("[E]" + "[ ] " + event[0] + "(from:" + from + " to:" + to + ")");
                            //list[i-1][4] change for mark&unmark [X] [ ]
                            //i is counter for printing 1. 2. 3.
                            System.out.println("Got it. I've added this task:");
                            System.out.println(list.get(count));
                            count++;
                            System.out.println("Now you have " + count + " tasks in the list.");
                        } else if (echo.contains("/by")) {
                            by = echo.split("/by");
                            list.add("[D][ ] " + by[0] + "(by:" + by[1] + ")");
                            System.out.println("Got it. I've added this task:");
                            System.out.println(list.get(count));
                            count++;
                            System.out.println("Now you have " + count + " tasks in the list.");

                        }else if(echo.matches("blah") || echo.matches("bleah") || echo.matches("boo") || echo.matches("boom") || echo.matches("bang")){
                            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                            System.out.println("please be clear");
                        }else if(echo.matches("to") || echo.matches("do") || echo.matches("todo")){
                            System.out.println("OOPS!!! The description of a todo cannot be empty.");
                            System.out.println("please include what to do in a todo");
                        } else if (!echo.matches("mark(.*)") && !echo.matches("unmark(.*)") && !echo.matches("delete(.*)")) {
                            list.add("[T][ ] " + echo);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(list.get(count));
                            count++;
                            System.out.println("Now you have " + count + " tasks in the list.");
                        } else if (echo.matches("mark(.*)") && echo.contains(" ") && !list.isEmpty()) {
                            instructions = echo.split(" ");
                            store = Integer.parseInt(instructions[1]); //position number start from 1
                            if (list.size() >= store) {
                                list.set(store-1, (list.get(store-1).substring(0,4) + "X" + list.get(store-1).substring(5))); //.replace
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println(list.get(store-1));
                            }
                        } else if (echo.matches("unmark(.*)") && echo.contains(" ") && !list.isEmpty()) {
                            instructions = echo.split(" ");
                            store = Integer.parseInt(instructions[1]); //position number start from 1
                            if (list.size() >= store) {
                                list.set(store-1, (list.get(store-1).substring(0,4) + " " + list.get(store-1).substring(5)));
                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.println(list.get(store-1));
                            }
                        }else if (echo.matches("delete(.*)") && echo.contains(" ") && !list.isEmpty()){
                            instructions = echo.split(" ");
                            store = Integer.parseInt(instructions[1]);
                            if(list.size() >= store){
                                System.out.println("Noted. I've removed this task:");
                                System.out.println("    " + list.get(store-1));
                                list.remove(store-1);
                                System.out.println("Now you have " + list.size() + " tasks in the list.");
                            }
                        }

                    } else if (echo.matches("list")) {
                        for (int i = 1; i <= list.size(); i++) {
                            if (list.get(i - 1) != null) {
                                System.out.println(i + "." + list.get(i - 1));
                            } else {
                                break;
                            }
                        }
                    }

                try{
                    if (echo == "blah" || echo == "bleah" || echo == "boo" || echo == "boom" || echo == "bang") {
                        throw new InvalidEntryException();
                    }else if(echo == "to" || echo == "do" || echo == "todo"){
                        throw new CannotBeEmptyException();
                    }
                }catch(InvalidEntryException e){
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("please be clear");
                }catch(CannotBeEmptyException e){
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("please include what to do in a todo");
                }

    }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static class InvalidEntryException extends Exception{ //method to access own error messages
        //no other code needed
    }

    public static class CannotBeEmptyException extends Exception{
        //no other code needed
    }

}
