public class UI {
    protected void greet() {
        String botName = "Angel";
        System.out.println("Hello! I'm " + botName + " your personal friendly to-do list tracker bot.");
        System.out.println("I accept Deadlines, Events and simple To-Dos. Here's a mini manual!");
        System.out.println();
        help();
        System.out.println("What can I do for you?" + "\n");
    }

    protected void help(){
        System.out.println("To Add:");
        System.out.println("(a) A Deadline: start with 'deadline /by' and specify any day of the week. e.g.: deadline return book /by Sunday");
        System.out.println("(b) An Event: start with 'event' and a description, then '/from' and '/to' the Event Day and Time. e.g.: event project meeting /from Mon 2pm /to 4pm");
        System.out.println("(c) A To-Do: start with 'todo' and task name. e.g. todo borrow book");
        System.out.println("To List all items in your list, type: list"); 
        System.out.println("To Mark or Unmark your tasks, type: (a) mark 'task no.' or (b) unmark 'task no' ");
        System.out.println("To Delete your tasks, type: delete 'task no.' ");
        System.out.println();
    }

}
