package tim.body;

import tim.tasks.Task;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

/**
 * This class handles the saving and loading of the list of tasks.
 */
public class FileManager {
    /**
     * This method loads the list of tasks from the file.
     * @return the list of tasks
     * @throws IOException if the file is not found
     * @throws ClassNotFoundException if the file is corrupted
     */
    static ArrayList<Task> loadList () throws IOException, ClassNotFoundException{
        ArrayList<Task> list = new ArrayList<Task>();
        try{
            File f = new File("./src/main/data/list.data");
            if(f.exists()) {
                FileInputStream fin = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fin);
                list = (ArrayList<Task>) ois.readObject();
                ois.close();
            } else {
                System.out.println("no saved list");
            }
        } catch (IOException e){
            System.err.println("unable to read file");
        }catch (ClassNotFoundException e) {
            System.err.println("file is corrupted");
        }
        return list;
    }

    /**
     * This method saves the list of tasks to the file.
     * @location ./src/main/data/list.data
     * @param list the list of tasks
     */
    static void saveList(ArrayList<Task> list){
        try{
            File f = new File("./src/main/data/list.data");
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();

        } catch (IOException e) {
            System.err.println("unable to create file");
        }
    }

}
