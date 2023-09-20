import java.util.ArrayList;

public class Shelf {
    private static final ArrayList<String> shelf = new ArrayList<>();
    public static void listShelf(){
        if(shelf.isEmpty()){
            Text.printMessage(Text.Message.NOITEM);
        }
        // listing sequence
        System.out.println(Text.newline);
        for(int i = 0; i < shelf.size(); i++){
            System.out.println(Integer.toString(i+1)+ ": " + shelf.get(i) + "\n");
        }
        System.out.println(Text.newline);
    }
    public static void addItem(String item){
        shelf.add(item);
        System.out.println(
                Text.newline
                        + "added: " + item + "\n"
                        + Text.newline
        );
    }
}
