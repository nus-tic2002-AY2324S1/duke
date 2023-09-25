import java.util.ArrayList;

public class Shelf {
    private static ArrayList<SpecialTask> shelf;
    public Shelf(){
        this.shelf = new ArrayList<>();
    }
    public static void listShelf(){
        if(shelf.isEmpty()){
            Text.printMessage(Text.Message.NOITEM);
        }
        // listing sequence
        System.out.print(Text.newline);
        for(int i = 0; i < shelf.size(); i++){
            System.out.println(Integer.toString(i+1)+ ": " + "["  + shelf.get(i).getStatusIcon() + "]" + "["  + shelf.get(i).getTypeIcon() + "]" + shelf.get(i).description);
        }
        System.out.println(Text.newline);
    }
    public static void addItem(String item){
        SpecialTask t = new SpecialTask(item,"");
        shelf.add(t);
        System.out.println(
                Text.newline
                        + "added: " + item + "\n"
                        + Text.newline
        );
    }
    public static void markTask(String[] msg){
        String check = msg[0];
        int idx = Integer.parseInt(msg[1]) - 1;
        if(idx >= shelf.size()){
            System.out.println("You have selected an invalid task");
            return;
        }

        System.out.print(Text.newline);
        if (check.equals("mark")){
            Task.setMarked(shelf.get(idx));
            System.out.println("Nice! I've marked this task as done:");
        } else if(check.equals("unmark")){
            Task.setUnmarked(shelf.get(idx));
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("[" + shelf.get(idx).getStatusIcon() + "]" + shelf.get(idx).description + "\n" + Text.newline);
    }

    public static void addSpecialTask(String item, String type){
        SpecialTask t = new SpecialTask(item,type);
        shelf.add(t);
        System.out.println(Text.newline + "Got it. I've added this task:");
        System.out.print("[" + t.getTypeIcon() + "]" + "[ ] " + item + "\n" + Text.newline);
        System.out.println("Now you have "+ shelf.size() +" tasks in the list.");
    }
}
