package UI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import Controller.AppointmentsController;
import Helper.Navigate;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/*
    * This class is used to show the appointments by week
    * It has a method to show the appointments by week
    * and open the template in the editor.
*/
public class AppointmentScheduleWeekController implements Initializable {

    @FXML
    private TableView<Appointment> showByWeek;
    @FXML
    private TableColumn<Appointment, Integer> Appointment_ID;
    @FXML
    private TableColumn<Appointment, String> Title;
    @FXML
    private TableColumn<Appointment, String> Description;
    @FXML
    private TableColumn<Appointment, String> Location;
    @FXML
    private TableColumn<Appointment, String> Type;
    @FXML
    private TableColumn<Appointment, String> StartDateTime;
    @FXML
    private TableColumn<Appointment, String> EndDateTime;
    @FXML
    private TableColumn<Appointment, String> CustomerID;
    @FXML
    private TableColumn<Appointment, String> UserID;
    @FXML
    private RadioButton ScheduleMonth;
    @FXML
    private RadioButton ScheduleWeek;

    @FXML
    javafx.scene.control.Label primaryLabel;
    @FXML
    javafx.scene.control.Label secondaryLabel;
    @FXML
    javafx.scene.control.Label additionLabel;
    @FXML
    javafx.scene.control.Button gotoCustomerPage;
    @FXML
    javafx.scene.control.Button gotoAddAppointment;

    /*
     * This method is used to show the appointments by week in the table
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Helper.SystemLanguage.getLanguage().equals("fr")) {
            setFrench();
        }

        Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("location"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        StartDateTime.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndDateTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        UserID.setCellValueFactory(new PropertyValueFactory<>("userId"));

        ArrayList<Appointment> appointments = AppointmentsController.getAppointmentsByWeek();
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList(appointments);
        showByWeek.setItems(appointmentList);
    }

    private void setFrench() {
        primaryLabel.setText("Détails des rendez-vous par semaine");
        secondaryLabel.setText("Aller à la page de rendez-vous pour modifier ou supprimer");
        additionLabel.setText("Calendrier des rendez-vous par");
        gotoCustomerPage.setText("Page client");
        gotoAddAppointment.setText("tous les rendez-vous");
        ScheduleMonth.setText("mois");
        ScheduleWeek.setText("Semaine");
        Appointment_ID.setText("ID");
        Title.setText("Titre");
        Description.setText("La description");
        Location.setText("Emplacement");
        Type.setText("Type");
        StartDateTime.setText("Commencer");
        EndDateTime.setText("Fin");
        CustomerID.setText("ID client");
        UserID.setText("ID utilisateur");

    }

    /*
     * This method is used as a handler for the button to go to the customer page
     */
    public void gotoCustomerPageHandler() {
        Navigate.goToCustomersPage();
    }

    /*
     * This method is used as a handler for the button to go to the appointment page
     */
    public void gotoAddAppointmentHandler() {
        Navigate.goToAppointmentsPage();
    }

    /*
     * This method is used as a handler for the button to go to the report page
     */
    public void getSchedule() {
        if (ScheduleMonth.isSelected()) {
            System.out.println("ScheduleMonth clicked ");
            Navigate.gotoAppointmentMonthPage();
        } else {
            System.out.println("ScheduleWeek clicked ");
            Navigate.gotoAppointmentWeekPage();
            ;
        }
    }

}
