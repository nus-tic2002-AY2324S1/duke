package duke.task;

import duke.exception.MissingDescriptionException;

public class Todo extends Task {
    public Todo(String description)  {
        super(description);
        setAbbreviation();
        increaseNumberOfTaskByOne();
    }

    public void execute()throws MissingDescriptionException{
        if(dukeDescription.isEmpty()){
            String message = "OOPS!!! The \"description\" of a \"todo\" cannot be empty :-(";
            throw new MissingDescriptionException(message);
        }
        response();
    }

    public void setAbbreviation() {
        abbreviation = 'T';
    }

    public Todo(){
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%c][%s] %s",abbreviation,getStatusIcon(),dukeDescription);
    }

}
