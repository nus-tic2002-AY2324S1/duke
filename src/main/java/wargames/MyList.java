// MyList.java manages the list of tasks

package wargames;
import java.util.ArrayList;
import java.util.Arrays;

public class MyList {
    private ArrayList<Task> tasklist = new ArrayList<>();

    public MyList(){

    }

    public void addToTaskList(Task task){
        tasklist.add(task);
    }

    public void addToList(Task[] arr){
        tasklist.addAll(Arrays.asList(arr));
    }

    public Task getItem(int index){
        return tasklist.get(index);
    }

    public ArrayList<Task> getList(){
        return tasklist;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasklist.size(); i++) {
            int count = i + 1;
            str.append(count).append(". ").append(tasklist.get(i)).append("\n");
        }
        return str.toString();
    }
}
