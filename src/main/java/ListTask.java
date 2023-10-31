import java.util.ArrayList;

public class ListTask {
    protected ArrayList<Task> List;

    public ListTask(){
        List = new ArrayList<>();
    }
    public ListTask(ArrayList<Task> AL){
        List = AL;
    }

    public void AddTask(Task T){
        List.add(T);
    }
    public Integer Size(){
        return List.size();
    }
    public Task getTask(Integer i){
        return List.get(i);
    }
    public void RemoveTask(int number){
        ArrayList<Task> StorageUpdate = new ArrayList<>();
        for(int i=0;i< List.size();i++){
            if(i != number)
                StorageUpdate.add(List.get(i));
            else {
                Duke.Separator();
                System.out.println("Cate has deleted this Task:");
                System.out.println(List.get(i));
            }
        }
        List = StorageUpdate;
    }
    public void ListAll(Keyword k){
        Duke.Separator();
        System.out.println("Here is your list of Tasks");
        for (int i = 0; i < List.size(); i++) {
            System.out.println(i + 1 + "." + List.get(i));
        }
        if(List.isEmpty())
            System.out.println("Well done , the List is now empty");
        else if(k==Keyword.LIST)
            System.out.println("Work harder, there is " + List.size() + " more task now");
        else if (k==Keyword.DELETE)
            System.out.println("Great! there is only " + List.size() + " task in the List now");
        Duke.Separator();
    }

}
