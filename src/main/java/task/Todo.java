package task;

import parser.DateTimeParser;
import util.Util;

public class Todo extends Task{
    private static DateTimeParser dateParser;

    //variable x stands for isDone
    public Todo(String description, boolean isDone) {
        super(description,isDone);
    }

    @Override
    public void printTask(){
        System.out.println(getTask());

    }
}
