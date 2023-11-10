package task;
public class Deadline extends Task{

    private String by;
    public Deadline(String d,boolean x) {
        super(d,x);
        filterBy(d);
    }
    public String getBy() {
        return by;
    }
    public void setBy(String newBy) {
        by = newBy;
    }

    public void filterBy(String description) {
        String[] words = description.split(" ", 2);
        try {
            String[] splitBy = words[1].split("/by ");
            setDescription(splitBy[0]);
            this.by = splitBy[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            //error message display is at main class
        }
    }

    @Override
    public String toTextFile(){
        String result = "";
        if (!getIsDone()){
            result = "[D][ ] "+ getDescription() + "(by: "+getBy()+")";
        }else {
            result = "[D][X] "+ getDescription() + "(by: "+getBy()+")";
        }
        return result;
    }

    @Override
    public void printTask(){
        if (!getIsDone()){
            System.out.println("[D][ ] "+ getDescription() + "(by: "+getBy()+")");
        }else {
            System.out.println("[D][X] "+ getDescription() + "(by: "+getBy()+")");
        }

    }
}
