// MyList.java manages the list of tasks

package wargames;
import java.util.Arrays;

public class MyList {
    private Task[] list = new Task[0];

    public MyList(){

    }

    public void addToList(Task task){
        list = Arrays.copyOf(list, list.length + 1);
        list[list.length - 1] = task;
    }

    public void addToList(Task[] arr){
        for (Task task : arr){
            list = Arrays.copyOf(list, list.length + 1);
            list[list.length - 1] = task;
        }
    }

    public String[] getList(){
        return list;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            int count = i + 1;
            str.append(count).append(". ").append(list[i]).append("\n");
        }
        return str.toString();
    }
}
