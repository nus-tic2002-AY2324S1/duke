import java.util.ArrayList;

public class Mark extends Duke implements ICommand{

    private int index;
    public Mark(String taskDescription){
        try {
            putIndex(taskDescription);
            response();
        } catch (NumberFormatException e) {
            String str = "The index number of the \"mark\" must be an integer :-(";
            Conversation.echo(str);
        } catch (IndexOutOfBoundsException e) {
            String str = "The index number of the \"mark\" is out of range :-(";
            Conversation.echo(str);

        }
    }

    /*private static void processMark(String input, boolean isMark) {
        String[] inputs = input.split(" ");
        int index = -1;

        try {
            index = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid index number for the mark item.");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.printf("The number after the %s is mandatory!\n",isMark?"Mark":"Unmark");
        }

        if (index == -1) {
            return;
        }

        // Todo: handle the mark/unmark index out of range exception
        if (isMark) {
            markTask(index);
        } else {
            unMarkTask(index);
        }
    }*/

    /*private static void markTask (int index) {
        line();
        indentation();
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(index - 1).markAsDone(true);
        indentation();
        System.out.println(tasks.get(index - 1));
        line();
    }*/

    private void putIndex(String input){
        index = Integer.parseInt(input);
    }
    @Override
    public void response(){
        ArrayList<Task> tasks = getTasks();
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("Nice! I've marked this task as done:");
        tasks.get(index-1).markAsDone(true);
        inputs.add(tasks.get(index-1).toString());
        Conversation.echo(inputs);

    }
}
