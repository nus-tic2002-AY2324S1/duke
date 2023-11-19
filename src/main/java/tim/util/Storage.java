package tim.util;

import tim.ui.Display;
import tim.util.TaskList;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.File;

/**
 * Represents as a Storage object.
 * This class handles the saving and loading of the list of tasks.
 */
public class Storage {

    //solution below adapted from https://stackoverflow.com/questions/3921836/how-to-save-list-items-on-disk-instead-of-memory-in-java
    /**
     * Loads the list of tasks from the file.
     *
     * @location ./src/main/data/list.data .
     * @return List of tasks.
     * @throws IOException If the file is not found.
     * @throws ClassNotFoundException If the file is corrupted.
     */
    public static TaskList loadList () throws IOException, ClassNotFoundException{
        TaskList tasks = new TaskList();
        try{
            File f = new File("./data/", "list.data");
            if(f.exists()) {
                FileInputStream fin = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fin);
                tasks = (TaskList) ois.readObject();
                if(tasks.isEmpty()){
                    System.out.println("no saved list");
                } else {
                    System.out.println("saved list loaded");
                }
                ois.close();
            } else {
                System.out.println("new list created");
            }
            Display.printDash();
        } catch (IOException e){
            System.out.println("oh no!  unable to read file");
            Display.printDash();
        }catch (ClassNotFoundException e) {
            System.out.println("oh no!  file is corrupted");
            Display.printDash();
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @location ./src/main/data/list.data .
     * @param tasks list of tasks.
     */
    public static void saveList(TaskList tasks){
        try{
            if(!new File("./data/").exists()){
                new File("./data/").mkdir();
            }
            File f = new File("./data/", "list.data");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();

        } catch (IOException e) {
            System.out.println("oh no!  unable to create file");
            Display.printDash();
        }
    }

}
