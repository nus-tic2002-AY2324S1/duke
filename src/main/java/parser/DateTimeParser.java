package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateTimeParser {
    /*
    method to extract date from inputString and format it.
    find date matches format dd/mm/yyyy, which is a required format from user side,
    parse it to dd-MM-yyyy format
     */

    public static String toDate(String inputString){
        String printedDate = " ";

        //need to check if the date fit into the format


        //compile regex format to match the expected result
        Pattern pattern = Pattern.compile("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[1,2])/((19|20)\\d\\d)");

        //create matcher to check if the string matches expected results
        Matcher matcher = pattern.matcher(inputString);

        //validate if the inputDate matches expected pattern
        if (matcher.find()){
            // Extract the matched date
            String inputDate = matcher.group(1);

            // Parse the extracted date
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");//-> Example: 2013-06-23
            LocalDate formattedDate = LocalDate.parse(inputDate, inputFormatter);

            //print date to MMM dd yyyy
            printedDate = formattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")); // -> Example: Oct 15 2019
        }
        else {
            System.out.println("Please follow date format as 'dd/MM/yyyy'");
        }
        return printedDate;
    }

}
