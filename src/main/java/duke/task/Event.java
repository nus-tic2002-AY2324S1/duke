package duke.task;

import duke.exception.MissingDescriptionException;
public class Event extends Task {

    protected String from;
    protected String to;

    public Event(){}
    public Event(String description) throws MissingDescriptionException {
        setAbbreviation();

        pullEventDateTime(description);
        increaseNumberOfTaskByOne();
        response();
    }

    public void setAbbreviation() {
        abbreviation = 'E';
    }

    public void pullEventDateTime(String input) throws MissingDescriptionException {
        final String FROM = "/from";
        final String TO = "/to";
        int lenOfFrom = FROM.length();
        int lenOfTo = TO.length();
        int posOfFrom = -1;
        int posOfTo = -1;

        posOfFrom = input.indexOf(FROM);
        posOfTo = input.indexOf(TO);

        if(input.isEmpty()){
            String message = "OOPS!!! The \"description\" of a \"event\" cannot be empty :-(";
            throw new MissingDescriptionException(message);
        }else if(posOfFrom == -1){
            String message = "OOPS!!! The \"/from\" of a \"event\" cannot be empty :-(";
            throw new MissingDescriptionException(message);
        }else if(posOfTo == -1){
            String message = "OOPS!!! The \"/to\" of a \"event\" cannot be empty :-(";
            throw new MissingDescriptionException(message);
        }

        from = input.substring(posOfFrom + lenOfFrom, posOfTo).trim();
        to = input.substring(posOfTo + lenOfTo).trim();
        dukeDescription = input.substring(0,posOfFrom).trim();
    }

    @Override
    public String toString(){
        return String.format("[%c][%s] %s (from: %s to: %s)",abbreviation,getStatusIcon(),dukeDescription, from, to);
    }
}
