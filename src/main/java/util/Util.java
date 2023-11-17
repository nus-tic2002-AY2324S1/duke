package util;

public class Util {

    public String transformDescription(String input, String taskType){
        if (taskType=="T"){
            return input.split(" ", 2)[1];
        }
        else if (taskType=="D") {
            return input.split(" ", 2)[1].split("/by ")[0];
        }
        else if (taskType=="E") {
            return input.split(" ", 2)[1].split("/from ")[0];
        }
        return " ";
    }

    public String transfromDeadlineInformation(String input){
        String result = null;
        try{
            result =  input.split(" ", 2)[1].split("/by ")[1];
        } catch(Exception e){

        }
        return result;
    }

    public String transformEventFrom(String input){
        String result = null;
        try{
            result = input.split("/from",2)[1].split("/to ",2)[0];
        }catch(Exception e){

        }
        return result;
    }

    public String transformEventTo(String input){
        String result = null;
        try {
            result =  input.split("/to ")[1];
        }catch(Exception e){

        }
        return result;
    }
}
