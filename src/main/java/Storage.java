import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

// Save the tasks in the hard disk automatically whenever the task list changes. 
// Load the data from the hard disk when the chatbot starts up. 
// You may hard-code the file name and relative path from the project root e.g., ./data/duke.txt
// File format can look like the below:
// T | 1 | read book
// D | 0 | return book | June 6th
// E | 0 | project meeting | Aug 6th 2-4pm
// T | 1 | join sports club
// Stretch goal: Handle the situation of the data file being corrupted (i.e., content not in the expected format).


// public static void main(String[] args) {
//     File f = new File("data/fruits.txt");
//     System.out.println("full path: " + f.getAbsolutePath());
//     System.out.println("file exists?: " + f.exists());
//     System.out.println("is Directory?: " + f.isDirectory());
// }

public class Storage {
    private static final String DIRECTORY = "./data/";
    private static final String FILE_PATH = "./data/list.txt";


    protected void loadData(TaskList tasks){
        try{
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            int counter = 0;
        
            while (s.hasNext()) {
                counter++;
                String[] line = s.nextLine().split(",");
                try{
                    tasks.addItem(line);
                }
                catch (InvalidInputException e){
                    System.out.println("Ignoring and deleting task at line no. " + counter + ". Task has invalid format.");
                    counter--;
                    continue;
                }
    
                if(line[1].equals("true")){
                    tasks.markItem(counter);
                }
                
            }
            s.close();
        }
        catch(FileNotFoundException e){
            // check if directory exists 
            // ---> if doesnt exist create directory first
            // ---> if exist, create file

            File directory = new File(DIRECTORY);
            File file = new File(FILE_PATH); 
            if (directory.exists()){
                try{
                    file.createNewFile();
                }
                catch(IOException e1){
                    System.out.print("Directory Exists but error creating new file!");
                }
            }
            else{
                if (directory.mkdir()){
                    try{
                        file.createNewFile();
                    }
                    catch(IOException e1){
                        System.out.print("Directory created but error creating new file!");
                    }
                }
                else{
                    System.out.print("Error creating directory!");
                }

            }
        }

    }

    protected void storeData(TaskList tasks){
        try{
            FileWriter fw = new FileWriter(FILE_PATH);

            for(int i=0; i<tasks.totalTasks; i++){
                String textToAdd = tasks.tasks.get(i).writeFile();
                fw.write(textToAdd + "\n");
            } 

            fw.close();

        } catch(IOException e){
            System.out.println("Error Writing to File!");
        }     
    }


}
