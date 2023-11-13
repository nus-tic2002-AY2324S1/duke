public class Task {

    protected String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    public String toFileString() {
        String isDoneSymbol = isDone ? "1" : "0";
        return "T | " + isDoneSymbol + " | " + description;
    }
    protected boolean getIsDone() {
        return isDone;
    }

    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split("\\s*\\|\\s*");
        if (parts.length >= 3) {
            String taskType = parts[0].trim();
            String isDoneSymbol = parts[1].trim();
            String description = parts[2].trim();

            boolean isDone = isDoneSymbol.equals("1");

            switch (taskType) {
                case "T":
                    return new ToDo(description, isDone);
                case "D":
                    if (parts.length >= 4) {
                        String by = parts[3].trim();
                        return new Deadline(description, by, isDone);
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        String from = parts[3].trim();
                        String to = parts[4].trim();
                        return new Event(description, from, to, isDone);
                    }
                    break;
                default:
                    return null; // Invalid task type
            }
        }
        return null; // Invalid fileString format
    }

    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }
}


