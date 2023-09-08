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
}
