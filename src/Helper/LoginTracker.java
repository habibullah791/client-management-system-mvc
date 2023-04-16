package Helper;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;


/**
    * This class is used to log login attempts
    * It has a method to log login attempts
*/ 
interface LoginTrackerInterface {
    public void logLoginAttempt(String username, boolean success) throws IOException;
}

public class LoginTracker {

    private static final String FILENAME = "login_activity.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logLoginAttempt(String username, boolean success) throws FileNotFoundException, IOException {
        String successString = success ? "SuccessFul" : "Unsuccessful";
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true));
        writer.write(username + " " + DATE_TIME_FORMATTER.format(java.time.LocalDateTime.now()) + " " + successString);
        writer.newLine();
        writer.close();

    }

}



