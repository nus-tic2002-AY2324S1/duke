package duke;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    // default values for storage
    private String filePath, directoryPath;
    private File file, directory;
    private FileWriter fw;
    private ArrayList<Task> tasks;


    public Storage(String filePath){
        this.tasks = new ArrayList<Task>();

        this.filePath = filePath;
        file = new File(filePath);

        int endIndex = filePath.lastIndexOf("/");

        this.directoryPath = filePath.substring(0, endIndex+1);
        directory = new File(directoryPath);
    }

    public ArrayList<Task> loadData() throws IOException{
        try{
            Scanner s = new Scanner(file);
            int counter = 0;
            Task t;
        
            while (s.hasNext()) {
                String[] line = s.nextLine().split(",");

                switch(line[0]){
                    case "E":
                        // doesnt add invalid task
                        if (line.length != 5) continue;
                        t = new Event(line[2], line[3], line[4]);
                        tasks.add(t);
                        break;
                    case "D":
                        if(line.length != 4) continue;
                        t = new Deadline(line[2], line[3]);
                        tasks.add(t);
                        break;
                    case "T":
                        // doesnt add invalid task
                        if (line.length != 3) continue;
                        t = new ToDo(line[2]);
                        tasks.add(t);
                        break;
                }

                if(line[1].equals("true")){
                    t = tasks.get(counter);
                    t.markAsDone();
                }

                counter++;
            }

            s.close();
        }
        catch(FileNotFoundException e){
            if (!directory.exists()){
                directory.mkdir();
            }
            file.createNewFile();
        }

        return tasks;

    }


    public void storeData(TaskList tasks){
        try{
            fw = new FileWriter(filePath);

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
