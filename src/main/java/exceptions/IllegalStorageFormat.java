package exceptions;

public class IllegalStorageFormat extends Exception {

    public IllegalStorageFormat() { super("The text in joshua.txt was not formatted correctly."); }

    public IllegalStorageFormat(String message) { super(message); }
}
