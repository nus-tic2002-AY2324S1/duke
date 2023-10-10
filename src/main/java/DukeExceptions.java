//For custom exceptions, use super to initialize the exception's error message
class DukeExceptions extends Exception {
    public DukeExceptions(String message){
        super(message);
    }
}

class InvalidInputException extends DukeExceptions {
    public InvalidInputException(){
        super("Oh no, please input a task clearly!");
    }
}

class InvalidDeadlineException extends DukeExceptions {
    public InvalidDeadlineException(){
        super("Please input a valid deadline using /by 'deadline'.");
    }
}

class InvalidEventException extends DukeExceptions {
    public InvalidEventException(){
        super("Please input a valid event using /from 'start' /to 'end'.");
    }
}

class InvalidTaskException extends DukeExceptions {
    public InvalidTaskException(){
        super("Please input a valid task stating details after 'todo'.");
    }
}

class EmptyInputException extends DukeExceptions {
    public EmptyInputException(){
        super("Please provide an input with details.");
    }
}

class NumberIndexOutOfBoundsException extends DukeExceptions {
    public NumberIndexOutOfBoundsException(){
        super("The index number of the task you indicate does not exist! Consider looking through the list again.");
    }
}

class InvalidIndexException extends DukeExceptions {
    public InvalidIndexException(){
        super("You did not indicate a proper index number! Consider looking through the list again.");
    }
}
