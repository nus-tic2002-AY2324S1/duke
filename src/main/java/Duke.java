/**
 * Creates a simple task management application that functions by taking in user inputs via the console.
 * User inputs are parsed and stored inside the hard disk as a text file.
 */

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import NUS.duke.UserInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

class Duke extends Application {

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    private static final ArrayList<Task>userInputTasks = new ArrayList<>();

    public enum taskType{
        TODO, DEADLINE, EVENT, FIXEDDURATION
    }

    private final UserInterface userInterface;

    private final Display display;

    //constructor Duke to initialise classes Display & UserInterface
    public Duke(){
        userInterface = new UserInterface();
        display = new Display();
    }

    //method to call start() & save last-updated list of tasks to tasks.txt
    public static void main(String[] inputs){
        Duke duke = new Duke();
        duke.start();

        try{
            PrintWriter writer = new PrintWriter(new FileWriter("tasks.txt"));
            for(int i = 0; i<userInputTasks.size(); i++){
                writer.println(i+1 + ". " + userInputTasks.get(i));
            }
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //method to start the task management application
    public void start(){
        display.greetings();

        try{
            File file = new File("C:/repos/duke/src/main/java/TestCases");
            Scanner scanner = new Scanner(file);

            //Read the test cases from the file
            while(scanner.hasNextLine()){
                String testCase = scanner.nextLine();
                //Process the test case here
                if(testCase.equals("bye")){
                    break;
                }else{
                    checkCommand(testCase);
                    System.out.println("***************************************");
                }
            }
//            scanner.close();
            userInterface.closeScanner();
        Display.goodbye();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //method to store the tasks according to taskType
    public void storeTask(taskType taskType, String arguments){
        Task task = createTask(taskType,arguments);
        if(task != null){
            userInputTasks.add(task);
            int itemIndex = Task.getTaskCount() - 1;
            display.notification(userInputTasks, itemIndex);
        }
    }

    //method to check the first word of input for further action (call other functions)
    public void checkCommand(String userInput){
        try{
            String[] inputs = userInput.split("\\s+");
            if(inputs.length == 0 || userInput.isEmpty()){
                throw new EmptyInputException();
            }

            String command = inputs[0].toLowerCase(); //front part task type
            String arguments = userInput.toLowerCase().substring(command.length()).trim(); //behind part info

            switch(command){
                case "list": {
                    display.UserInputList(userInputTasks);
                    break;
                }
                case "todo": {
                    if (arguments.isEmpty()) {
                        throw new InvalidTaskException();
                    }else{
                        storeTask(taskType.TODO, arguments);
                    }
                    break;
                }
                case "deadline": {
                    if(!arguments.contains("/by")){
                        throw new InvalidDeadlineException();
                    }else{
                        storeTask(taskType.DEADLINE, arguments);
                    }
                    break;
                }
                case "event": {
                    if(!arguments.contains("/from") || !arguments.contains("/to")){
                        throw new InvalidEventException();
                    }else{
                        storeTask(taskType.EVENT, arguments);
                    }
                    break;
                }
                case "delete": {
                    try {
                        int indexPosition = Integer.parseInt(arguments);
                        indexPosition = indexPosition - 1;
                        if (indexPosition < Task.taskCount) {
                            deleteTask(indexPosition);
                        }else{
                            throw new NumberIndexOutOfBoundsException();
                        }
                    }catch(InvalidIndexException e){
                        throw new InvalidIndexException();
                    }
                    break;
                }
                case "mark":{
                    markTasksCompleted(userInput);
                    break;
                }
                case "unmark":{
                    unmarkTasksCompleted(userInput);
                    break;
                }
                case "find": {
                    if(userInputTasks.isEmpty()){
                        throw new EmptyListException();
                    }else if(arguments.isEmpty()){
                        throw new InvalidKeywordException();
                    }else{
                        findTask(arguments);
                    }
                    break;
                }
                case "clone": { //only works for T & F task types (individual feature 2)
                    createClone(arguments);
                    break;
                }
                case "update": { //only lists down related tasks for updating (individual feature 2)
                    findRelatedTasksForUpdate(arguments);
                    break;
                }
                case "change": { //individual feature 2
                    changeDateOrTime(arguments);
                    break;
                }
                case "date": {

                }
                case "day": {

                }
                default:
                    newCheckTask(userInput);
            }
        }catch(DukeExceptions e){
            System.out.println(e.getMessage());
        }
    }


    //(to be used on createDeadline & createEvent)
    //to be used on checkCommand(): if is valid date, then call findTaskWithDate else datetimeparseexception
    public boolean isValidDate(String dateInput){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate date = LocalDate.parse(dateInput, formatter);
            return true;
        }catch(DateTimeParseException e){
            return false;
        }
    }

    //is date within range
public boolean isDateWithinRange(String dateInputStr, String startDateStr, String endDateStr){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate dateInput = LocalDate.parse(dateInputStr, formatter);
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);
            return !dateInput.isBefore(startDate) && !dateInput.isAfter(endDate);
        }catch(Exception e){
            return false;
        }
}

    //find the day of dates
public DayOfWeek findDayOfWeek(String dateInput){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate date = LocalDate.parse(dateInput, formatter);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            return dayOfWeek;
        }catch(Exception e){
            return null;
        }
}

    //find tasks on particular date
public void findTaskWithDate(ArrayList<Task>userInputTasks, String dateInput){
        ArrayList<Task>tasksFound = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
    LocalDate date = LocalDate.parse(dateInput, formatter);
        if(!userInputTasks.isEmpty()){
            for(int i = 0; i<userInputTasks.size(); i++){
//                if(userInputTasks.get(i).contains(date)){

//                }
            }
        }
}

    //find tasks on particular day



    //method to help mark tasks
    public void markTasksCompleted(String userInput) throws NumberIndexOutOfBoundsException, InvalidIndexException {
        String[] input = userInput.split("\\s+");
        boolean numeric = true;
        try{
            int num = Integer.parseInt(input[1]);
            if(num <= 0 || num>userInputTasks.size()){
                throw new NumberIndexOutOfBoundsException();
            }
        }catch(NumberIndexOutOfBoundsException e){
            numeric = false;
        }
        if(numeric){
            int indexNumber = Integer.parseInt(input[1]) - 1;
            display.mark(userInputTasks, indexNumber);
            System.out.println(userInputTasks.get(indexNumber));
        }else{
           throw new InvalidIndexException();
        }
    }

    //method to help unmark tasks
    public void unmarkTasksCompleted(String userInput) throws NumberIndexOutOfBoundsException, InvalidIndexException {
        String[] input = userInput.split("\\s+");
        boolean numeric = true;
        try{
            int num = Integer.parseInt(input[1]);
            if(num <= 0 || num>userInputTasks.size()){
                throw new NumberIndexOutOfBoundsException();
            }
        }catch(NumberIndexOutOfBoundsException e){
            numeric = false;
        }
        if(numeric){
            int indexNumber = Integer.parseInt(input[1]) - 1;
            display.unmark(userInputTasks, indexNumber);
            System.out.println(userInputTasks.get(indexNumber));
        }else{
            throw new InvalidIndexException();
        }
    }


    //method to check for taskType and call other method(s) to create that taskType
private Task createTask(taskType taskType, String arguments) {
        Task task = null;
        switch(taskType){
            case TODO:
                task = new Task('T', arguments);
                break;
            case DEADLINE:
                task = createDeadlineTask(arguments);
                break;
            case EVENT:
                task = createEventTask(arguments);
                break;
            case FIXEDDURATION:
                task = createFixedDuration(arguments);
                break;
        }
        return task;
}


//method for finding specific tasks with a particular keyword (input) provided
private void findTask(String input) throws InvalidKeywordException{
        //arguments (input) should be a single word
    int count = 1;
    for (int i = 0; i<userInputTasks.size(); i++){
        String[] inputParts = input.split("\\s+");
        if(inputParts.length != 1){
            throw new InvalidKeywordException();
        }else{
            //the input is a single word but may not be THE keyword
            if(userInputTasks.get(i).getTaskName().contains(inputParts[0])){
                if(count == 1){
                    display.findMessage(); //need print ONCE only
                    count++;
                }
                System.out.print(i+1 + ".");
                System.out.println(userInputTasks.get(i));
            }
        }
    }
}

//method for listing all tasks of a genre
    private void findRelatedTasksForUpdate(String arguments){
        boolean check = true;
        try {
            String[] inputs = arguments.split("\\s+");
            if (inputs.length == 0 || arguments.isEmpty()) {
                throw new InvalidTaskTypeException();
            }
            switch (arguments) {
                case "task": {
                    for (int k = 0; k < userInputTasks.size(); k++) {
                        if (userInputTasks.get(k).getTaskType() == 'T') {
                            listRelevantTasks(check);
                            check = false;
                        }
                    }
                    break;
                }
                case "deadline": {
                    for (int i = 0; i < userInputTasks.size(); i++) {
                        if (userInputTasks.get(i).getTaskType() == 'D') {
                            listRelevantDeadlines(check);
                            check = false;
                        }
                    }
                    break;
                }
                case "event": {
                    for (int j = 0; j < userInputTasks.size(); j++) {
                        if (userInputTasks.get(j).getTaskType() == 'E') {
                            listRelevantEvents(check);
                            check = false;
                        }
                    }
                    break;
                }
                case "fixedduration": {
                    for (int h = 0; h < userInputTasks.size(); h++) {
                        if (userInputTasks.get(h).getTaskType() == 'F') {
                            listRelevantFixedDurations(check);
                            check = false;
                        }
                    }
                    break;
                }
            }
        }catch(DukeExceptions e){
            System.out.println(e.getMessage());
        }
    }

    public void listRelevantTasks(boolean check){
        if(check){
            System.out.println("These are the tasks that you may update: ");
            for(int k = 0; k<userInputTasks.size(); k++){
                if(userInputTasks.get(k).getTaskType() == 'T'){
                    System.out.println((k+1) + "." + userInputTasks.get(k).toString());
                }
            }
            System.out.println("Please key in 'Change (index number)' of the task you wish to update");
        }
    }

    public void listRelevantDeadlines(boolean check){
        if(check){
            System.out.println("These are the deadlines that you may update: ");
            for(int i = 0; i<userInputTasks.size(); i++){
                if(userInputTasks.get(i).getTaskType() == 'D'){
                    System.out.println((i+1) + "." + userInputTasks.get(i).toString());
                }
            }
            System.out.println("Please key in 'Change (index number)' of the deadline you wish to update");
        }
    }

    public void listRelevantEvents(boolean check){
        if(check){
            System.out.println("These are the events that you may update: ");
            for(int j = 0; j<userInputTasks.size(); j++){
                if(userInputTasks.get(j).getTaskType() == 'E'){
                    System.out.println((j+1) + "." + userInputTasks.get(j).toString());
                }
            }
            System.out.println("Please key in 'Change (index number)' of the event you wish to update");
        }
    }

    public void listRelevantFixedDurations(boolean check){
        if(check){
            System.out.println("These are the fixed duration tasks that you may update: ");
            for(int h = 0; h<userInputTasks.size(); h++){
                if(userInputTasks.get(h).getTaskType() == 'F'){
                    System.out.println((h+1) + "." + userInputTasks.get(h).toString());
                }
            }
            System.out.println("Please key in 'Change (index number)' of the fixed duration task you wish to update");
        }
    }

    //method for changing dates and times
    private void changeDateOrTime(String argument) throws InvalidInputException{
        String[] inputs = argument.split("\\s+");
        int index = Integer.parseInt(inputs[0]) - 1;
        String newstarttime = null;
        String newendtime = null;
        String newstartdate = null;
        String newenddate = null;
        try{

            for(int x = 0; x<inputs.length; x++){
                if(inputs[x] == "starttime"){
                    newstarttime = inputs[x+1];
                }
                if(inputs[x] == "startdate"){
                    newstartdate = inputs[x+1];
                }
                if(inputs[x] == "endtime"){
                    newendtime = inputs[x+1];
                }
                if(inputs[x] == "enddate"){
                    newenddate = inputs[x+1];
                }
            }

            String input = userInputTasks.get(index).toString();
            Character type = userInputTasks.get(index).getTaskType();
            String name = userInputTasks.get(index).getTaskName();

            if(type == 'D'){
                    if (newenddate == null) {
                        String[] inputSplit = input.split("\\s+");
                        for (int j = 0; j < inputSplit.length; j++) {
                            if (inputSplit[j].equals("(by:")) {
                                newenddate = inputSplit[j + 1];
                            }
                        }
                    }

                if(newendtime == null){
                    String[] inputSplit1 = input.split("\\s+");
                    for(int k = 0; k < inputSplit1.length; k++){
                        if(inputSplit1[k].equals("(from:")){ // here got issues
                            newendtime = inputSplit1[k+2];
                        }
                    }
                }
                Deadline newDeadline = new Deadline(type, name, newenddate, newendtime); // Create a new Deadline object
                userInputTasks.set(index, newDeadline);
                display.changesMadeNotification(userInputTasks, index);

            }else if(type == 'E'){

                if(newstartdate == null){
                    String[] inputSplit2 = input.split("\\s+");
                    for(int i = 0; i < inputSplit2.length; i++){
                        if(inputSplit2[i].equals("(from:")){
                            newstartdate = inputSplit2[i+1];
                        }
                    }
                }

                if(newstarttime == null){
                    String[] inputSplit3 = input.split("\\s+");
                    for(int h = 0; h < inputSplit3.length; h++){
                        if(inputSplit3[h].equals("(from:")){
                            newstarttime = inputSplit3[h+2];
                        }
                    }
                }

                if(newenddate == null){
                    String[] inputSplit4 = input.split("\\s+");
                    for(int u = 0; u < inputSplit4.length; u++){
                        if(inputSplit4[u].equals("to:")){
                            newenddate = inputSplit4[u+1];
                        }
                    }
                }

                if(newendtime == null){
                    String[] inputSplit5 = input.split("\\s+");
                    for(int v = 0; v < inputSplit5.length; v++){
                        if(inputSplit5[v].equals("to:")){
                            newendtime = inputSplit5[v+2];
                        }
                    }
                }
                Event newEvent = new Event(type, name, newstartdate, newstarttime, newenddate, newendtime); // Create a new Event object
                userInputTasks.set(index, newEvent); // Replace the old task with the new one
                display.changesMadeNotification(userInputTasks, index);

            }else{
                throw new InvalidInputException();
            }
        }catch(InvalidInputException e){
            throw new InvalidInputException();
        }
    }

//method for creating Deadline tasks
private Task createDeadlineTask(String arguments){

        int byIndex = arguments.indexOf("/by");
        String taskName = arguments.substring(0, byIndex).trim();
        String date = arguments.substring(byIndex + 3).trim();
        //call validator here
        String[] dateTime = date.split("\\s+");
        String dueDate = dateTime[0];
        String dueTime = dateTime[1];

        return new Deadline('D', taskName, dueDate, dueTime);
    }

    //method for creating Event tasks
private Task createEventTask(String arguments) {

    int fromIndex = arguments.indexOf("/from");
    int toIndex = arguments.indexOf("/to");
    String taskName = arguments.substring(0, fromIndex).trim();
    String from = arguments.substring(fromIndex + 5, toIndex).trim();
    String fromSet[] = from.split("\\s+");
    String fromDate = fromSet[0];
    String fromTime = fromSet[1];
    String to = arguments.substring(toIndex + 3).trim();
    String toSet[] = to.split("\\s+");
    String toDate = toSet[0];
    String toTime = toSet[1];

    return new Event('E', taskName, fromDate, fromTime, toDate, toTime);
}

    //method to clone a task (only T & F types)
    private void createClone(String arguments) throws NumberIndexOutOfBoundsException, InvalidIndexException {
        String[] input = arguments.split("\\s+");
        boolean numeric = true;
        try{
            int num = Integer.parseInt(input[0]);
            if(num<=0 || num>userInputTasks.size()){
                throw new NumberIndexOutOfBoundsException();
            }
        }catch(NumberIndexOutOfBoundsException e){
            numeric = false;
        }
        if(numeric){
            int indexNumber = Integer.parseInt(input[0]) - 1;
            Task clone = userInputTasks.get(indexNumber);
            switch(clone.getTaskType()){
                case 'T':{
                    storeTask(taskType.TODO, clone.getTaskName());
                    break;
                }
                case 'F':{
                    storeTask(taskType.FIXEDDURATION, clone.getTaskName());
                    break;
                }
            }
        }else{
            throw new InvalidIndexException();
        }
    }

//method used in conjunction with checkCommand() for checking if it is a FIXEDDURATION taskType
public void newCheckTask(String userInput) throws InvalidInputException{
        if(userInput.contains("(needs") || userInput.contains("at least") || userInput.contains("need")){
            storeTask(taskType.FIXEDDURATION, userInput);
        }else{
            throw new InvalidInputException();
        }
}

//method for creating a FIXEDDURATION taskType (individual feature 1)
private Task createFixedDuration(String userInput){
        return new FixedDuration('F', userInput);
    }

    //method for deleting tasks
    public void deleteTask(int itemIndex) throws InvalidIndexException{
        Task deletedTask = userInputTasks.remove(itemIndex);
        Task.removeTask();
        display.deletedMessage(deletedTask);
    }

}
