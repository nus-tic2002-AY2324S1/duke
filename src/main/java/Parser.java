import java.util.ArrayList;

public class Parser{
        protected boolean NoError;

        public boolean getNoError(){
            return NoError;
        }
        public Parser(Keyword k,String line,ArrayList<Task> Storage){
                this.NoError=true;
        String[] words = line.split(" ");
        try{
        switch(k){
        case DELETE:
        if(words.length==1)
        throw new MissingArgumentException("Nothing selected to delete");
        if(Integer.parseInt(words[1])<=0)
        throw new MissingArgumentException("Cate cant find imaginary tasks");
        if(Storage.size()<Integer.parseInt(words[1]))
        throw new MissingArgumentException("Cate is unable to delete whats outside the list , try again");
        break;
        case LIST:
        if(Storage.isEmpty()){
        throw new MissingArgumentException("Oops , List is Empty");
        }
        break;
        case MARK:
        if(Integer.parseInt(words[1])<=0)
        throw new MissingArgumentException("If its not a real number, it wont be marked");
        if(Storage.size()<Integer.parseInt(words[1]))
        throw new MissingArgumentException("Task number is larger than the list , add more task");
        if(Storage.get(Integer.parseInt(words[1])-1).isDone)
        throw new MissingArgumentException("Cate has already marked it as done");
        break;
        case UNMARK:
        if(Integer.parseInt(words[1])<=0)
        throw new MissingArgumentException("Only real numbers please");
        if(Storage.size()<Integer.parseInt(words[1]))
        throw new MissingArgumentException("Task number is not within the list , try again");
        if(!Storage.get(Integer.parseInt(words[1])-1).isDone)
        throw new MissingArgumentException("Mark it as done first, Cate is unable to Unmark");
        break;
        case TODO:
        if(words.length==1)
        throw new MissingArgumentException("Nothing Todo");
        break;
        case DEADLINE:
        if(words.length==1)
        throw new MissingArgumentException("Where is the Deadline context and due time?");
        if(words[1].equals("/by"))
        throw new MissingArgumentException("Theres no content for Deadline");
        if(!line.contains("/by"))
        throw new MissingArgumentException("its not a Deadline without an expiry");
        if(words[words.length-1].equals("/by"))
        throw new MissingArgumentException("Deadline due time is blank");
        break;
        case EVENT:
        if(words.length==1)
        throw new MissingArgumentException("What Event is this? there is no content , start time and end time");
        if(words[1].equals("/from"))
        throw new MissingArgumentException("Event is missing context");
        if(words[1].equals("/to"))
        throw new MissingArgumentException("Event is missing context");
        if(!line.contains("/from"))
        throw new MissingArgumentException("Event cant start without a start time , add /from");
        if(!line.contains("/to"))
        throw new MissingArgumentException("Does the Event not end? , add /to");
        if(line.contains("/from /to"))
        throw new MissingArgumentException("Event start time is blank");
        if(words[words.length-1].equals("/to"))
        throw new MissingArgumentException("Event end time is blank");
        if(line.indexOf("/from")>line.indexOf("/to"))
        throw new MissingArgumentException("Start time first followed by end time");
        break;
        }
        } catch (MissingArgumentException e){
                this.NoError=false;
        Duke.Separator();
        System.out.println(e);
        Duke.Separator();

        }
        }

}