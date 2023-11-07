package tim.util;

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
    /**
     * Loads the list of tasks from the file.
     *
     * @location ./src/main/data/list.data
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
                ois.close();
            } else {
                System.out.println("no saved list");
            }
        } catch (IOException e){
            System.err.println("unable to read file");
        }catch (ClassNotFoundException e) {
            System.err.println("file is corrupted");
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @location ./src/main/data/list.data
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
            System.err.println("unable to create file");
        }
    }

}
