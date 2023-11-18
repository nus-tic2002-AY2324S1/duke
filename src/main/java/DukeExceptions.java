//This java file has custom exceptions that extends existing Exceptions
//For custom exceptions, use super to initialize the exception's error message
class DukeExceptions extends Exception {
    public DukeExceptions(String message){
        super(message);
    }
}

//for inputs such as "boo" or "nnn" (that do not make sense)
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

//for inputs such as "" (enter) or " " (spacing)
class EmptyInputException extends DukeExceptions {
    public EmptyInputException(){
        super("Please provide an input with details.");
    }
}

//e.g. for inputs such as "mark 12" when the number of tasks in list is only 9
class NumberIndexOutOfBoundsException extends DukeExceptions {
    public NumberIndexOutOfBoundsException(){
        super("The index number of the task you indicate does not exist! Consider looking through the list again.");
    }
}

//e.g. for inputs such as "delete abc"
class InvalidIndexException extends DukeExceptions {
    public InvalidIndexException(){
        super("You did not indicate a proper index number! Consider looking through the list again.");
    }
}

//e.g. for inputs such as "find two words" with 2 keywords /  "find " with no keywords
class InvalidKeywordException extends DukeExceptions {
    public InvalidKeywordException(){
        super("That keyword is not valid! Consider looking through the list again.");
    }
}

//e.g. for inputs such as mark/unmark/delete/find when the list is empty
class EmptyListException extends DukeExceptions {
    public EmptyListException(){
        super("The list is empty. The action you requested cannot be carried out.");
    }
}

class InvalidTaskTypeException extends DukeExceptions {
    public InvalidTaskTypeException(){
        super("Please key in a valid task type!");
    }
}