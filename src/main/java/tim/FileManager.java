package tim;
import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public static ArrayList<Task> loadList () throws IOException, ClassNotFoundException{
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

    public static void saveList(ArrayList<Task> list){
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
