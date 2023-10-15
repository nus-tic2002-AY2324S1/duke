import java.util.Scanner;

public class Bot {

    boolean typing = true;
    taskList tasks = new taskList();
    UI ui = new UI();

    protected void start(Scanner in){

        while(typing){
            try {
                String line = in.nextLine().trim();
                if(line.equals("bye")){
                    System.out.println("Bye! Hope I'll get to see you soon! :)");
                    typing = false;
                    continue;
                }
                else if(line.contains("list")){
                    try{
                        tasks.listItems();
                    }
                    catch (EmptyListException e) {
                        System.out.println("Ooops! It looks like you don't have anything in your list! Add something to your list first!");
                    }
                }
                else if(line.contains("mark")){
                    try{
                        tasks.markItem(line);
                    }
                    catch(MissingTaskException e){
                        System.out.println("This task doesnt exist!");
                    }
                    
                }
                else if(line.contains("help")){
                    ui.help();
                }
            
                else if(line.contains("delete")){
                    try{
                        tasks.deleteItem(line);
                    }
                    catch(EmptyListException e){
                        System.out.println("Looks like there's nothing left in your list to delete! Try adding a new item in the list.");
                    }
                    catch (MissingTaskException e){
                        System.out.println("This task doesnt exist! Try again.");
                    }
                }
            
                else{
                    try{
                        tasks.addItem(line);
                    }
                    catch(InvalidInputException e){
                        System.out.println("Invalid Input.");
                        System.out.println("To call for help, type: help");
                    }
                }
            
                System.out.println();
                System.out.println("What else can I do for you?");
            }
    
            catch (IndexOutOfBoundsException e){
                System.out.println("Hey, looks like you gave an incomplete sentence!");
                ui.help();
            }
    
        }
        
    }

    

}
