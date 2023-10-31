package io;

import java.io.*;
import java.util.*;

/**
 * HelpMessage class is a class that handle the help message.
 * It has a method to print out the list of commands that what CrabY can do.
 */
public class HelpMessage extends CrabyMessage{
    public static void printHelpMessage() {
        try {
            String fileName = "helpMessage.txt";
            ClassLoader classLoader = HelpMessage.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);

            assert inputStream != null;
            Scanner scanner = new Scanner(inputStream);
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            System.out.print(content);
            inputStream.close();
        } catch (Exception e) {
            System.out.println(SPACES + "Error: " + e.getMessage());
        }
    }
}