package UI;

import parser.Keyword;
import parser.Parser;
import task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The ListTask class is responsible for holding the list of tasks and manipulating tasks
 */
public class ListTask {
    protected ArrayList<Task> List;

    /**
     * constructor for ListTask
     */
    public ListTask(){
        List = new ArrayList<>();
    }

    /**
     *
     * @return true if empty , false otherwise
     */
    public boolean isEmpty(){
        return List.isEmpty();
    }

    /**
     * prints separator line
     */
    public static void Separator(){
        System.out.println("__________________________");
    }

    /**
     * adds tasks to the list
     *
     * @param T task to be added into the list
     */
    public void add(Task T){
        List.add(T);
    }

    /**
     *
     * @return size of list
     */
    public Integer size(){
        return List.size();
    }

    /**
     *
     * @param i task number in the list
     * @return task in the list
     */
    public Task get(Integer i){
        return List.get(i);
    }

    /**
     * removes task from the list
     *
     * @param number the task in the list to be removed
     */
    public void removeTask(int number){
        ArrayList<Task> StorageUpdate = new ArrayList<>();
        for(int i=0;i< List.size();i++){
            if(i != number) {
                StorageUpdate.add(List.get(i));
            }
            else {
                Separator();
                System.out.println("Cate has deleted this Task:");
                System.out.println(List.get(i));
            }
        }
        List = StorageUpdate;
    }

    /**
     * lists all the task in the list
     *
     * @param k flavor text after listing all tasks
     */
    public void listAll(Keyword k){
        Separator();
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
        else if (k==Keyword.SORT)
            System.out.println("The List has been sorted with the earliest End Date at the top");
        Separator();
    }

    /**
     * finds any tasks that contains input string
     *
     * @param line string to be searched for
     */
    public void findTask(String line) {
        ArrayList<Task> Found = new ArrayList<>();
        for (Task task : List) {
            if (task.toString().contains(line)) {
                Found.add(task);
            }
        }
        Separator();
        if (Found.isEmpty()) {
            System.out.println("Oops , Cate did not find any matching Tasks");
        } else {
            System.out.println("Here are the matching Tasks");
            for (int i = 0; i < Found.size(); i++) {
                System.out.println(i + 1 + "." + Found.get(i));
            }
        }
        Separator();
    }

    /**
     *
     * sorts the list of tasks with the todos at the top and latest end time events or by time deadlines to the bottom
     */
    public void sortSchedule(){
        ArrayList<Task> PreSort = new ArrayList<>();
        ArrayList<Task> Sorted = new ArrayList<>();
        ArrayList<Task> Arrangement = new ArrayList<>();
        for ( Task task : List){
            if(task.getTag().equals("T"))
                Arrangement.add(task);
        }
        for ( Task task : List){
            if(task.getTag().equals("D"))
                PreSort.add(task);
            if(task.getTag().equals("E"))
                PreSort.add(task);
        }


        for(int j=0;j< PreSort.size();j++){
            Task earliest = PreSort.get(0);
            for (Task task : PreSort) {
                if (earliest.endTime().isAfter(task.endTime())) {
                    earliest = task;
                }
            }
        PreSort.remove(earliest);
        j--;
        Sorted.add(earliest);
        }
        Arrangement.addAll(Sorted);
        List=Arrangement;
    }

    /**
     * view list of deadlines' by date before input date and inbetween event's from and to date
     *
     * @param date date to be compared with
     */
    public void viewSchedule(String date){
        LocalDateTime ViewDay = Parser.constructDateTime(date+" 00:00");
        ArrayList<Task> PreSort = new ArrayList<>();
        ArrayList<Task> Sorted = new ArrayList<>();
        for ( Task task : List){
            if(task.getStatusIcon().equals(" ")) {
                if (task.getTag().equals("D")){
                    PreSort.add(task);
                }
                if (task.getTag().equals("E")){
                    if(ViewDay.isAfter(task.startTime())){
                    PreSort.add(task);
                    }else if(Parser.isSameDate(task.startTime(),ViewDay)){
                        PreSort.add(task);
                    }
                }
            }
        }
        for( Task task : PreSort){
            if(ViewDay.isBefore(task.endTime()))
                Sorted.add(task);
        }
        Separator();
        System.out.println("Here is the schedule for " + date + " that has not been marked done");
        for (int i = 0; i < Sorted.size(); i++) {
            System.out.println(i + 1 + "." + Sorted.get(i));
        }
        Separator();
    }
}
