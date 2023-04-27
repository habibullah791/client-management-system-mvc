package Controller;

import DB.DBHandler;
import Model.Appointment;
import Model.Report;
import Helper.LoggedIn;

import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class is the controller for the Appointments
 * It handles all the logic for the Appointments
 */
public class AppointmentsController {

    private static DBHandler dbHandler;

    ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    /**
     * This method is the constructor for the AppointmentsController
     */
    public AppointmentsController() {
        dbHandler = new DBHandler();
        connectToDatabase();
    }

    /**
     * This method is used to connect to the database
     * 
     * @return String
     */
    public String connectToDatabase() {
        return dbHandler.connectToDatabase();
    }

    /**
     * This method is used to close the connection to the database
     */
    public void closeConnection() {
        dbHandler.closeConnection();
    }

    /**
     * Adds a new appointment to the list of appointments.
     *
     * @param appointment the appointment to add to the list
     */
    public void addNewAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * This method is used to remove an appointment
     * 
     * @param appointment
     */
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    /**
     * Updates an appointment in the list of appointments with the same ID as the
     * given appointment.
     *
     * @param appointment the appointment to update
     */
    public void updateAppointment(Appointment appointment) {
        appointments.replaceAll(a -> {
            if (a.getAppointmentId() == appointment.getAppointmentId()) {
                return appointment;
            } else {
                return a;
            }
        });
    }

    /**
     * Retrieves all appointments by loading them from the AppointmentsController
     * and returning the list of appointments.
     *
     * @return ArrayList of Appointment objects representing all appointments.
     */
    public static ArrayList<Appointment> getAllAppointments() {
        AppointmentsController appointmentsController = new AppointmentsController();
        appointmentsController.loadAppointments();
        return appointmentsController.appointments;
    }

    /**
     * This method is used to get an appointment by ID and return it
     * 
     * @param appointmentId
     * @return Appointment or null
     */
    public Appointment getAppointmentByID(int appointmentId) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId() == appointmentId) {
                return appointments.get(i);
            }
        }
        return null;
    }

    /**
     * This method is used to load all appointments from the database
     */
    public void loadAppointments() {
        try {
            if (dbHandler.getConnection() == null) {

            } else {
                String sql = "SELECT a.Appointment_ID, a.Title, a.Description, a.Location, a.Type, a.Start, a.End, a.Create_Date, a.Created_By, a.Last_Update, a.Last_Updated_By, c.Customer_Name, u.User_Name, co.Contact_Name FROM appointments a INNER JOIN customers c ON a.Customer_ID = c.Customer_ID INNER JOIN users u ON a.User_ID = u.User_ID INNER JOIN contacts co ON a.Contact_ID = co.Contact_ID;";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setAppointmentId(rs.getInt("Appointment_ID"));
                    appointment.setTitle(rs.getString("Title"));
                    appointment.setDescription(rs.getString("Description"));
                    appointment.setLocation(rs.getString("Location"));
                    appointment.setType(rs.getString("Type"));
                    // get the date and tim
                    String start = rs.getTimestamp("Start").toString();
                    String end = rs.getTimestamp("End").toString();

                    start = start.substring(0, start.length() - 2);
                    end = end.substring(0, end.length() - 2);

                    String startTime = Helper.DateTimeHelper.convertToLocalDaateTime(start);
                    String endTime = Helper.DateTimeHelper.convertToLocalDaateTime(end);

                    appointment.setStart(
                            LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    appointment
                            .setEnd(LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    appointment.setCreateDate(rs.getTimestamp("Create_Date").toLocalDateTime());
                    appointment.setCreatedBy(rs.getString("Created_By"));
                    appointment.setLastUpdate(rs.getTimestamp("Last_Update").toLocalDateTime());
                    appointment.setLastUpdatedBy(rs.getString("Last_Updated_By"));
                    appointment.setCustomerName(rs.getString("Customer_Name"));
                    appointment.setUserName(rs.getString("User_Name"));
                    appointment.setContactName(rs.getString("Contact_Name"));
                    appointments.add(appointment);

                }

                System.out.println("Appointments loaded successfully");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to delete an appointment by ID
     * 
     * @param appointmentID
     * @return int or 0
     */
    public static int deleteAppointment(int appointmentID) {
        try {
            if (dbHandler.getConnection() == null) {
                return 0;
            } else {
                String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);
                ps.setInt(1, appointmentID);
                return ps.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * This is a functional interface used to convert the time and date to a
     * datetime
     * 
     * @param String, LocalDate
     */
    @FunctionalInterface
    interface TimeDateConverter {
        String convert(String time, LocalDate date);
    }

    /**
     * This is a lambda expression used to convert the time and date to a datetime
     */
    static TimeDateConverter converter = (time, date) -> {
        if (time.length() == 5) {
            time = time + ":00";
        }
        String dateTime = date + " " + time;
        // convert to datetime
        return dateTime;
    };

    /**
     * This method is used to update an appointment
     * 
     * @param appointmentID, title, description, location, type, startTime,
     * @return int or 0
     */
    public static int updateAppointment(int appointmentID, String title, String description, String location,
            String type, String startDateTime, String endDateTime,
            String customer_name) {
        try {
            if (dbHandler.getConnection() == null) {
                return 0;
            } else {

                String userName = LoggedIn.user.getUserName();
                // convert to datetime
                String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = NOW(), Created_By = ?, Last_Update = NOW(), Last_Updated_By = ?, Customer_ID = (SELECT Customer_ID FROM customers WHERE Customer_Name = ?), User_ID = (SELECT User_ID FROM users WHERE User_Name = ?), Contact_ID = ? WHERE Appointment_ID = ?";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);

                ps.setString(1, title);
                ps.setString(2, description);
                ps.setString(3, location);
                ps.setString(4, type);
                ps.setString(5, startDateTime);
                ps.setString(6, endDateTime);
                ps.setString(7, userName);
                ps.setString(8, userName);
                ps.setString(9, customer_name);
                ps.setString(10, userName);
                ps.setInt(11, 1);
                ps.setInt(12, appointmentID);

                return ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * This method is used to add an appointment
     * 
     * @param appointmentTitle, description, type, location, contact, startTime,
     * @return int or 0
     */
    public static int addAppointment(String appointmentTitle, String description, String type, String location,
            String contact, String startTime, String endTime, String customerName) {
        try {
            if (dbHandler.getConnection() == null) {
                return 0;
            } else {
                // String Start = converter.convert(startTime, LocalDate.parse(startDate));
                // String End = converter.convert(endTime, LocalDate.parse(endDate));
                String userName = LoggedIn.getUser().getUserName();

                String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?, (SELECT Customer_ID FROM customers WHERE Customer_Name = ?), (SELECT User_ID FROM users WHERE User_Name = ?), ?)";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);

                ps.setString(1, appointmentTitle);
                ps.setString(2, description);
                ps.setString(3, location);
                ps.setString(4, type);
                ps.setString(5, startTime);
                ps.setString(6, endTime);
                ps.setString(7, userName);
                ps.setString(8, userName);
                ps.setString(9, customerName);
                ps.setString(10, userName);
                ps.setInt(11, 1);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Returns a list of appointments for the current month and year.
     *
     * @return an ArrayList of Appointment objects, or null if there was an error
     *         accessing the database
     */
    public static ArrayList<Appointment> getAppointmentsByMonth() {
        try {
            DBHandler dbHandler = new DBHandler();
            dbHandler.connectToDatabase();
            if (dbHandler.getConnection() == null) {
                return null;
            } else {
                String sql = "SELECT a.Appointment_ID, a.Title, a.Description, a.Location, a.Type, a.Start, a.End, c.Customer_ID, u.User_ID FROM appointments a INNER JOIN customers c ON a.Customer_ID = c.Customer_ID INNER JOIN users u ON a.User_ID = u.User_ID INNER JOIN contacts co ON a.Contact_ID = co.Contact_ID WHERE MONTH(a.Start) = MONTH(CURRENT_DATE()) AND YEAR(a.Start) = YEAR(CURRENT_DATE());";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ArrayList<Appointment> appointments = new ArrayList<>();
                while (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setAppointmentId(rs.getInt("Appointment_ID"));
                    appointment.setTitle(rs.getString("Title"));
                    appointment.setDescription(rs.getString("Description"));
                    appointment.setLocation(rs.getString("Location"));
                    appointment.setType(rs.getString("Type"));
                    appointment.setStart(rs.getTimestamp("Start").toLocalDateTime());
                    appointment.setEnd(rs.getTimestamp("End").toLocalDateTime());
                    appointment.setCustomerId(rs.getInt("Customer_ID"));
                    appointment.setUserId(rs.getInt("User_ID"));
                    appointments.add(appointment);
                }
                return appointments;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Returns an ArrayList of Appointment objects for appointments scheduled in the
     * current week.
     *
     * @return an ArrayList of Appointment objects, or null if there was an error
     *         retrieving data from the database.
     */
    public static ArrayList<Appointment> getAppointmentsByWeek() {
        try {
            DBHandler dbHandler = new DBHandler();
            dbHandler.connectToDatabase();
            if (dbHandler.getConnection() == null) {
                return null;
            } else {
                String sql = "SELECT a.Appointment_ID, a.Title, a.Description, a.Location, a.Type, a.Start, a.End,  c.Customer_ID, u.User_ID FROM appointments a INNER JOIN customers c ON a.Customer_ID = c.Customer_ID INNER JOIN users u ON a.User_ID = u.User_ID INNER JOIN contacts co ON a.Contact_ID = co.Contact_ID WHERE WEEK(a.Start) = WEEK(CURRENT_DATE()) AND YEAR(a.Start) = YEAR(CURRENT_DATE());";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ArrayList<Appointment> appointments = new ArrayList<>();
                while (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setAppointmentId(rs.getInt("Appointment_ID"));
                    appointment.setTitle(rs.getString("Title"));
                    appointment.setDescription(rs.getString("Description"));
                    appointment.setLocation(rs.getString("Location"));
                    appointment.setType(rs.getString("Type"));
                    appointment.setStart(rs.getTimestamp("Start").toLocalDateTime());
                    appointment.setEnd(rs.getTimestamp("End").toLocalDateTime());
                    appointment.setCustomerId(rs.getInt("Customer_ID"));
                    appointment.setUserId(rs.getInt("User_ID"));
                    appointments.add(appointment);
                }
                return appointments;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Retrieves a list of appointments that start within the next 15 minutes.
     *
     * @return an ArrayList of Appointment objects, or null if there was an error
     *         connecting to the database.
     */
    public static ArrayList<Appointment> isAppointmentIn15Minute() {
        try {
            DBHandler dbHandler = new DBHandler();
            dbHandler.connectToDatabase();
            if (dbHandler.getConnection() == null) {
                return null;
            } else {
                String sql = "SELECT a.Appointment_ID, a.Start FROM appointments a INNER JOIN customers c ON a.Customer_ID = c.Customer_ID INNER JOIN users u ON a.User_ID = u.User_ID INNER JOIN contacts co ON a.Contact_ID = co.Contact_ID WHERE a.start between NOW() AND DATE_ADD(NOW(), INTERVAL 15 MINUTE);";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ArrayList<Appointment> appointments = new ArrayList<>();
                while (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setAppointmentId(rs.getInt("Appointment_ID"));
                    appointment.setStart(rs.getTimestamp("Start").toLocalDateTime());
                    appointments.add(appointment);
                }
                return appointments;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Retrieves data for report 1 from the database.
     * 
     * @return An ArrayList of Report objects, where each Report object contains
     *         information about the appointment type, the month in which the
     *         appointments occurred, and the number of appointments for that type
     *         in that month.
     *         Returns null if there was an error connecting to the database or
     *         retrieving the data.
     */
    public static ArrayList<Report> getReport1Data() {
        try {
            DBHandler dbHandler = new DBHandler();
            dbHandler.connectToDatabase();
            if (dbHandler.getConnection() == null) {
                return null;
            } else {
                String sql = "SELECT a.Type, MONTH(a.Start) AS 'Month', COUNT(a.Start) AS 'Number of Appointments' FROM appointments a GROUP BY a.Type, MONTH(a.Start);";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ArrayList<Report> report1s = new ArrayList<>();
                while (rs.next()) {
                    Report report1 = new Report();
                    report1.setReportType(rs.getString("Type"));
                    report1.setMonth(rs.getInt("Month"));
                    report1.setNumberOfAppointment(rs.getInt("Number of Appointments"));
                    report1s.add(report1);
                }
                return report1s;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Retrieves data for report 2 from the database.
     * 
     * @return An ArrayList of Appointment objects, where each Appointment object
     *         contains information about the appointment ID, title, description,
     *         type, start time, end time, and customer ID.
     *         Returns null if there was an error connecting to the database or
     */
    public static ArrayList<Appointment> getReport2Data() {
        try {
            DBHandler dbHandler = new DBHandler();
            dbHandler.connectToDatabase();
            if (dbHandler.getConnection() == null) {
                return null;
            } else {
                String sql = "SELECT a.Appointment_ID, a.Title, a.Description, a.Type, a.Start, a.End, c.Customer_ID FROM appointments a INNER JOIN customers c ON a.Customer_ID = c.Customer_ID INNER JOIN users u ON a.User_ID = u.User_ID INNER JOIN contacts co ON a.Contact_ID = co.Contact_ID WHERE co.Contact_ID = 1;";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ArrayList<Appointment> report2s = new ArrayList<>();
                while (rs.next()) {
                    Appointment report2 = new Appointment();
                    report2.setAppointmentId(rs.getInt("Appointment_ID"));
                    report2.setTitle(rs.getString("Title"));
                    report2.setDescription(rs.getString("Description"));
                    report2.setType(rs.getString("Type"));
                    report2.setStart(rs.getTimestamp("Start").toLocalDateTime());
                    report2.setEnd(rs.getTimestamp("End").toLocalDateTime());
                    report2.setCustomerId(rs.getInt("Customer_ID"));
                    report2s.add(report2);
                }
                return report2s;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Retrieves data for report 3 from the database.
     * 
     * @return An ArrayList of Appointment objects, where each Appointment object
     *         contains information about the appointment ID, start time, end time,
     *         customer name, and contact name.
     *         Returns null if there was an error connecting to the database or
     */
    public static ArrayList<Appointment> getReport3Data() {
        try {
            DBHandler dbHandler = new DBHandler();
            dbHandler.connectToDatabase();
            if (dbHandler.getConnection() == null) {
                return null;
            } else {
                String sql = "SELECT a.Appointment_ID, a.Start , a.End, c.Customer_Name, con.Contact_Name FROM appointments a INNER JOIN customers c ON a.Customer_ID = c.Customer_ID INNER JOIN contacts con ON a.Contact_ID = con.Contact_ID WHERE con.Contact_Name IN ('Li Lee' AND 'Daniel Garcia');";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ArrayList<Appointment> report2s = new ArrayList<>();
                while (rs.next()) {
                    Appointment report2 = new Appointment();
                    report2.setAppointmentId(rs.getInt("Appointment_ID"));
                    report2.setStart(rs.getTimestamp("Start").toLocalDateTime());
                    report2.setEnd(rs.getTimestamp("End").toLocalDateTime());
                    report2.setCustomerName(rs.getString("Customer_Name"));
                    report2.setContactName(rs.getString("Contact_Name"));
                    report2s.add(report2);
                }
                return report2s;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Retrieves data for isOverlappingAppointment from the database.
     * 
     * @return An ArrayList of Appointment objects, where each Appointment object
     *         contains information about the appointment ID, start time, and end
     *         time.
     *         Returns null if there was an error connecting to the database or
     */
    public static ArrayList<Appointment> isOverlappingAppointment(String start, String end) {
        try {
            DBHandler dbHandler = new DBHandler();
            dbHandler.connectToDatabase();
            if (dbHandler.getConnection() == null) {
                return null;
            } else {
                String sql = "SELECT Appointment_ID, Start, End FROM appointments WHERE Start BETWEEN ? AND ?;";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);
                ps.setString(1, start);
                ps.setString(2, end);
                ResultSet rs = ps.executeQuery();
                ArrayList<Appointment> appointments = new ArrayList<>();

                while (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setAppointmentId(rs.getInt("Appointment_ID"));
                    appointment.setStart(rs.getTimestamp("Start").toLocalDateTime());
                    appointment.setEnd(rs.getTimestamp("End").toLocalDateTime());
                    appointments.add(appointment);
                }

                return appointments;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Retrieves data for canceledAppointments from the database.
     * 
     * @return An ArrayList of Appointment objects, where each Appointment object
     *         contains information about the appointment ID and type.
     *         Returns null if there was an error connecting to the database or
     */
    public static ArrayList<Appointment> canceledAppointments() {
        try {
            DBHandler dbHandler = new DBHandler();
            dbHandler.connectToDatabase();
            if (dbHandler.getConnection() == null) {
                return null;
            } else {
                String sql = "SELECT Appointment_ID, type FROM appointments WHERE Start < NOW() AND End < NOW();";
                PreparedStatement ps = dbHandler.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ArrayList<Appointment> appointments = new ArrayList<>();

                while (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setAppointmentId(rs.getInt("Appointment_ID"));
                    appointment.setType(rs.getString("Type"));
                    appointments.add(appointment);
                }

                return appointments;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}