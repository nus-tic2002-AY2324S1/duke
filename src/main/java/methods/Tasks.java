package methods;

import java.util.ArrayList;

public class Tasks {
    private ArrayList<String> list;
    private String item;
    private int listSize;

    public Tasks() {
        this.list = new ArrayList<String>();
        this.item = "";
        this.listSize = 0;
    }

    public void AddToList(String input) {
        this.item = input.toUpperCase();
        this.list.add(this.listSize, this.item);
        this.listSize++;
        System.out.println(this.item + " added to list!");
        System.out.println("You've " + this.listSize + " item(s) in list~");
    }

    public void List() {
        if (this.listSize == 0) {
            System.out.println("List is empty!");
        } else {
            int itemNum = 1;
            for (String item : this.list) {
                System.out.println(itemNum + ". " + item);
                itemNum++;
            }
        }
    }

    public ArrayList<String> getList() {
        return this.list;
    }

    public String getItem() {
        return this.item;
    }

    public int getListSize() {
        return this.listSize;
    }
}
