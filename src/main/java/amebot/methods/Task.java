package amebot.methods;

import java.util.ArrayList;

public class Task {
    private ArrayList<String> taskLists;
    private String item;
    private ArrayList<String> statusLists;
    private String status;
    private int listSize;

    public Task() {
        taskLists = new ArrayList<String>();
        item = "";
        statusLists = new ArrayList<String>();
        status = "";
        listSize = 0;
    }

    public void addToList(String input) {
        item = input.toUpperCase();
        taskLists.add(listSize, item);
        status = "[ ]";
        statusLists.add(listSize, status);
        listSize++;

        System.out.println(item + " successfully added to list!\n"
                + "You've " + listSize + " item(s) in list~");
    }

    public void listAllTasks() {
        if (listSize == 0) {
            System.out.println("List is empty!");
        } else {
            int itemNum = 1;

            System.out.println("Item(s) in current list:");
            for (String desc : taskLists) {
                item = desc;
                status = statusLists.get(itemNum - 1);
                System.out.println(itemNum + ". " + status + " " + desc);
                itemNum++;
            }
        }
    }

    public ArrayList<String> getTaskList() {
        return taskLists;
    }

    public String getItem() {
        return item;
    }

    public ArrayList<String> getStatusList() {
        return statusLists;
    }

    public void setStatus(int index, String status) {
        String message = "";

        if (status.equals("check")) {
            this.status = "[✓]";
            message = "You've completed this item!\n";

        } else {
            this.status = "[ ]";
            message = "Pending item!\n";
        }

        statusLists.set(index, this.status);
        System.out.println(message + this.status + " " + taskLists.get(index));
    }

    public String getStatus() {
        return status;
    }

    public int getListSize() {
        return listSize;
    }
}
