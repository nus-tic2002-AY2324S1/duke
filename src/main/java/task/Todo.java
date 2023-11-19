package task;

import parser.DateTimeParser;
import util.Util;

public class Todo extends Task{
    private static DateTimeParser dateParser;
    private String from;
    private String to;

    private Util util;

    //variable x stands for isDone
    public Todo(String description, boolean isDone) {
        super(description,isDone);
    }

    @Override
    public void printTask(){
        System.out.println(getTask());

    }
}
