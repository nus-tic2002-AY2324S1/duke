package Duke;

public class DukeException extends Exception{
    public DukeException(String msg){
        if(msg.equals("mark")){
            System.out.println("Please enter the task number you want to mark/unmark!");
        }
    }
}
