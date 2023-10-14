package commandsTask;

public class Help extends Task{
    public static final String CMD = "help";
    public static final String CMD_HELP = "Command [" + CMD + "]\n"
            + "Type \"help\" to get list of commands";

    public Help(){
        this.toRecord = false;
        this.toUpdateList = false;
    }

    @Override
    public String toString(){
        return
                "Here are the list of available commands: "
                + "\n" + Todo.CMD_HELP
                + "\n" + Deadline.CMD_HELP
                + "\n" + Event.CMD_HELP
                + "\n" + Help.CMD_HELP;
    }
}
