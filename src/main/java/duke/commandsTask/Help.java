package duke.commandsTask;

public class Help extends Task{
    public static final String CMD = "help";
    public static final String CMD_HELP = "[" + CMD + "] \t\t"
            + "Return the list of commands ||"
            + "Type \"help\" to get list of commands";

    public Help(){
        this.toRecord = false;
        this.toUpdateList = false;
    }

    @Override
    public String toString(){
        return "Here are the list of available commands: "
                + "\n" + Help.CMD_HELP
                + "\n............................."
                + "\n" + Todo.CMD_HELP
                + "\n" + Deadline.CMD_HELP
                + "\n" + Event.CMD_HELP
                + "\n............................."
                + "\n" + Mark.CMD_HELP
                + "\n" + Unmark.CMD_HELP
                + "\n............................."
                + "\n" + ListCMD.CMD_HELP
                + "\n" + Delete.CMD_HELP
                + "\n" + Bye.CMD_HELP;
    }
}
