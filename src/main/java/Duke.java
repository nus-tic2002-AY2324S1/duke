
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static Storage storage;
    private static ListTask listofitems;
    private static UI ui;
    private static Parser parse;
    public static void main(String[] args) {

        storage = new Storage();
        ui.Run(listofitems,parse);
       try {
            listofitems = storage.load();
        } catch (FileNotFoundException e) {
            listofitems = new ListTask();
        }
/*
        Task t = new Todo("fly kite");
        listofitems.addTask(t);
        t = new Deadline("return kite","june 2023");
        listofitems.addTask(t);
        listofitems.listAll(Keyword.LIST);
        try{
        storage.save(listofitems);}
        catch (IOException e) {
           System.out.println("error save");
        } */
        listofitems.listAll(Keyword.LIST);
    }

}
