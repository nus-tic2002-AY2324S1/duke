public class Task {
    private boolean isDone;
    private String description;

    public Task (String d,boolean x){
        this.isDone = x ;
        this.description = d;
    }


    public void setIsDone(boolean x){
        this.isDone = x;
    }

    public void setDescription(String s){
        this.description = s;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription(){
        return description;
    }

    public void printTask(){
        if (!isDone){
            System.out.println("[T][ ] "+ getDescription());
        }else {
            System.out.println("[T][X] "+ getDescription());
        }

    }

}
