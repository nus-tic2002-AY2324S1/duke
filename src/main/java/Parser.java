import java.text.ParseException;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class Parser{
        protected boolean NoError;

        public boolean getNoError(){
            return NoError;
        }

        public Parser(){
                NoError=true;
        }
        public static void Separator(){
                System.out.println("__________________________");
        }
        public boolean dateFormatInvalid(String dateStr) {
                if(dateStr.length()<16){
                        return true;
                }
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                sdf.setLenient(false);
                try {
                        sdf.parse(dateStr);
                } catch (ParseException e) {
                        return true;
                }

                return false;
        }
        public static LocalDateTime constructDateTime(String dateStr){

                int day = Integer.parseInt(dateStr.substring(0,2));
                int month = Integer.parseInt(dateStr.substring(3,5));
                int year = Integer.parseInt(dateStr.substring(6,10));
                int hour = Integer.parseInt(dateStr.substring(11,13));
                int minute = Integer.parseInt(dateStr.substring(14,16));

                return LocalDateTime.of(year, month, day, hour, minute);
        }
        public static boolean isSameDate(LocalDateTime date1, LocalDateTime date2){
                Integer day = date1.getDayOfYear();
            return day.equals(date2.getDayOfYear());
        }

        public void checkError(Keyword k,String line,ListTask Storage){
                this.NoError=true;
        String[] words = line.split(" ");
        try{
        switch(k){
        case VIEW:
        if(words.length==1)
                throw new MissingArgumentException("Select a date to view");
        if(dateFormatInvalid(words[1]+" 00:00"))
                throw new MissingArgumentException("Date format is dd/MM/yyyy");
        break;
        case DELETE:
        if(words.length==1)
                throw new MissingArgumentException("Nothing selected to delete");
        else if(Integer.parseInt(words[1])<=0)
                throw new MissingArgumentException("Cate cant find imaginary tasks");
        else if(Storage.size()<Integer.parseInt(words[1]))
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
        else if(Storage.size()<Integer.parseInt(words[1]))
                throw new MissingArgumentException("Task number is larger than the list , add more task");
        else if(Storage.get(Integer.parseInt(words[1])-1).isDone)
                throw new MissingArgumentException("Cate has already marked it as done");
        break;
        case UNMARK:
        if(Integer.parseInt(words[1])<=0)
                throw new MissingArgumentException("Only real numbers please");
        else if(Storage.size()<Integer.parseInt(words[1]))
                throw new MissingArgumentException("Task number is not within the list , try again");
        else if(!Storage.get(Integer.parseInt(words[1])-1).isDone)
                throw new MissingArgumentException("Mark it as done first, Cate is unable to Unmark");
        break;
        case TODO:
        if(words.length==1)
                throw new MissingArgumentException("Nothing Todo");
        break;
        case DEADLINE:
                String byDateTime= line.substring(line.indexOf("/by")+4);
        if(words.length==1)
                throw new MissingArgumentException("Where is the Deadline context and due time?");
        else if(words[1].equals("/by"))
                throw new MissingArgumentException("Theres no content for Deadline");
        else if(!line.contains("/by"))
                throw new MissingArgumentException("its not a Deadline without an expiry");
        else if(words[words.length-1].equals("/by"))
                throw new MissingArgumentException("Deadline due time is blank");
        if(dateFormatInvalid(byDateTime))
                throw new MissingArgumentException("Please use the format dd/MM/yyyy HH:mm with valid Date for due date");
        break;
        case EVENT:
                String fromDateTime= line.substring(line.indexOf("/from")+6,line.indexOf("/to"));
                String toDateTime= line.substring(line.indexOf("/to")+4);
        if(words.length==1)
                throw new MissingArgumentException("What Event is this? there is no content , start date time and end date time");
        else if(words[1].equals("/from"))
                throw new MissingArgumentException("Event is missing context");
        else if(words[1].equals("/to"))
                throw new MissingArgumentException("Event is missing context");
        else if(!line.contains("/from"))
                throw new MissingArgumentException("Event cant start without a start time , add /from");
        else if(!line.contains("/to"))
                throw new MissingArgumentException("Does the Event not end? , add /to");
        else if(line.contains("/from /to"))
                throw new MissingArgumentException("Event start time is blank");
        else if(words[words.length-1].equals("/to"))
                throw new MissingArgumentException("Event end time is blank");
        else if(line.indexOf("/from")>line.indexOf("/to"))
                throw new MissingArgumentException("Start time first followed by end time");
        else if(dateFormatInvalid(fromDateTime))
                throw new MissingArgumentException("Please use the format dd/MM/yyyy HH:mm with valid Date for start date");
        else if(dateFormatInvalid(toDateTime))
                throw new MissingArgumentException("Please use the format dd/MM/yyyy HH:mm with valid Date for end date");
        else if(constructDateTime(fromDateTime).isAfter(constructDateTime(toDateTime)))
                throw new MissingArgumentException("End date time cannot be earlier than Start date time");
        break;
        case FIND: if(words.length==1)
                throw new MissingArgumentException("There is no text to Find");
        break;

        }
        } catch (MissingArgumentException e){
                this.NoError=false;
        Separator();
        System.out.println(e);
        Separator();
        }
        }

}