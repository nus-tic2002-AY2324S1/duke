package Task;
/**
 * Stores and creates different types of tasks
 * Child class of Task
 */
public class SpecialTask extends Task {
    protected String type;
    public SpecialTask(String type, String description) {
        super(description);
        switch(type){
            case "todo":
                this.type = "T";
                break;
            case "deadline":
                this.type = "D";
                break;
            case "event":
                this.type = "E";
                break;
            default:
                this.type = "~";
        }
    }
    public String printTypeIcon(){
        if(this.type.equals("~")){
            return " ";
        }
        return this.type;
    }
    public String getType(){
        return this.type;
    }
}
