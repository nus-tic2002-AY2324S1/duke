// MyList.java manages the list of tasks

package wargames;
import java.util.Arrays;

public class MyList {
    private String[] list = new String[0];

    public MyList(){

    }

    public void addToList(String item){
        list = Arrays.copyOf(list, list.length + 1);
        list[list.length - 1] = item;
    }

    public void addToList(String[] arr){
        for (String item : arr){
            list = Arrays.copyOf(list, list.length + 1);
            list[list.length - 1] = item;
        }
    }

    public String[] getList(){
        return list;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < list.length; i++) {
            int count = i + 1;
            str += count + ". " + list[i] + "\n";
        }
        return str;
    }
}
