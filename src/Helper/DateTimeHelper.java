package Helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {

    public static String convertToUTC(String localTime) {
        // Parse the local time string into a LocalDateTime object
        LocalDateTime localDateTime = LocalDateTime.parse(localTime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Get the default time zone of the system
        ZoneId localZoneId = ZoneId.systemDefault();

        // Create a ZonedDateTime object for the local time with the default time zone
        ZonedDateTime localZonedDateTime = ZonedDateTime.of(localDateTime, localZoneId);

        // Convert the ZonedDateTime to UTC
        ZonedDateTime utcZonedDateTime = localZonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));

        // Format the UTC ZonedDateTime into a string
        String utcTime = utcZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return utcTime;
    }

    public static boolean isStartTimeGreater(String startTime, String endTime) {

        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + endTime);

        // check if the start time is greater than the end time
        if (startTime.compareTo(endTime) >= 0) {
            System.out.println("Start time is greater than end time");
            return false;
        }
        // check if the start time is equal to the end time
        if (startTime.compareTo(endTime) == 0) {
            System.out.println("Start time is equal to end time");
            return false;
        }
        System.out.println("Start time is less than end time");
        return true;
    }

    public static String convertToLocalDaateTime(String utcTime) {
        LocalDateTime utcDateTime = LocalDateTime.parse(utcTime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Get the default time zone of the system
        ZoneId localZoneId2 = ZoneId.systemDefault();

        // Create a ZonedDateTime object for the UTC time with the default time zone
        ZonedDateTime utcZonedDateTime2 = ZonedDateTime.of(utcDateTime, ZoneId.of("UTC"));

        // Convert the ZonedDateTime to local time
        ZonedDateTime localZonedDateTime2 = utcZonedDateTime2.withZoneSameInstant(localZoneId2);

        // Format the local ZonedDateTime into a string
        String localTime = localZonedDateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return localTime;
    }
}
