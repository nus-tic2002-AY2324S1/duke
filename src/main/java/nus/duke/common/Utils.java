package nus.duke.common;

public class Utils {
    public static Integer tryParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }
}
