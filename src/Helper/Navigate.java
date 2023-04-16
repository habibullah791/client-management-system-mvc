package Helper;

import java.io.File;

import Main.GUIMain;
import Model.Appointment;
import Model.Customer;
import UI.Controller.ShowAppointmentsDetailsController;
import UI.Controller.ShowDetailsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
    * This class is used to navigate between pages
    * It has a method to load the login page
    * It also has a method to load the home page
    * It also has a method to load the showDetails page
    * It also has a method to load the appointments page
    * It also has a method to load the showAppointmentsDetails page
*/
public class Navigate {

    /**
     * This method is used to load the login page
     */
    public static void goToLoginPage() {
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/Login.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading login page: " + e.getMessage());
        }
    }

    /**
     * This method is used to load the Customers page
     */
    public static void goToCustomersPage() {
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/CustomerTable.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading customers page: " + e.getMessage());
        }
    }

    /**
     * This method is used to load the Customers details page
     */
    public static void goToShowCustomerDetailsPage(Customer customer) {
        ShowDetailsController.customer = customer;
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/ShowDetails.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading showDetails page: ");
            e.printStackTrace();
        }
    }

    /**
     * This method is used to load the Appointments page
     */
    public static void goToAppointmentsPage() {
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/AppointmentsTable.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading appointments page: " + e.getMessage());
        }

    }

    /**
     * This method is used to load the Appointments details page
     */
    public static void goToShowAppointmentsDetailsPage(Appointment appointment) {
        ShowAppointmentsDetailsController.appointment = appointment;
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/showAppointmentsDetails.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading showAppointmentsDetails.fxml page: " + e.getMessage());
        }
    }

    /**
     * This method is used to load the addCustomer page
    */
    public static void goToAddCustomerPage() {
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/addNewCustomer.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading addCustomer page: " + e.getMessage());
        }
    }


    /**
     * This method is used to load the addAppointment page
    */
    public static void goToAddAppointmentPage() {
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/AddAppointment.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading addAppointment page: " + e.getMessage());
        }
    }


    /**
     * This method is used to load the AppointmentScheduleMonth page
    */
    public static void gotoAppointmentMonthPage() {
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/AppointmentScheduleMonth.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading AppointmentScheduleMonth page: " + e.getMessage().toString());
        }
    }


    /**
     * This method is used to load the AppointmentScheduleWeek page
    */
    public static void gotoAppointmentWeekPage() {
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/AppointmentScheduleWeek.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading AppointmentScheduleMonth page: " + e.getMessage().toString());
        }
    }

    /**
     * This method is used to load the Report1 page
    */
    public static void gotoReport1Page() {
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/Report1.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading AppointmentScheduleMonth page: " + e.getMessage().toString());
        }
    }

    /**
     * This method is used to load the Report2 page
    */
    public static void gotoReport2Page() {
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/Report2.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading AppointmentScheduleMonth page: " + e.getMessage().toString());
        }
    }

    /**
     * This method is used to load the Report3 page
    */
    public static void gotoReport3Page() {
        try {
            GUIMain.root = FXMLLoader.load(new File("src/UI/Report3.fxml").toURI().toURL());
            Scene scene = new Scene(GUIMain.root);
            GUIMain.primaryStage.setScene(scene);
            GUIMain.primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading AppointmentScheduleMonth page: " + e.getMessage().toString());
        }
    }
}
