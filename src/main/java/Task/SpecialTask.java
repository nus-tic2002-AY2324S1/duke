package Task;

import Task.Task;

public class SpecialTask extends Task {
    private String type;
    public SpecialTask(String description, String type) {
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
                this.type = " ";
        }
    }
    public String getTypeIcon(){
        return type;
    }
}
