package UI.Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controller.AppointmentsController;
import Helper.Navigate;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/*
    * This class is used to display the report 3
    * It has a method to display the report 3
 */
public class Report3Controller implements Initializable {

    @FXML
    private TableView<Appointment> Report3Table;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentID;
    @FXML
    private TableColumn<Appointment, LocalDateTime> StartTime;
    @FXML
    private TableColumn<Appointment, LocalDateTime> EndTime;
    @FXML
    private TableColumn<Appointment, String> CustomerName;
    @FXML
    private TableColumn<Appointment, String> ContactName;
    @FXML
    private javafx.scene.control.Label primaryLabel;
    @FXML
    private javafx.scene.control.Label secondaryLabel1;
    @FXML
    private javafx.scene.control.Label secondaryLabel2;
    @FXML
    private javafx.scene.control.Button gotoCustomerTable;
    @FXML
    private javafx.scene.control.Button gotoReport1Table;
    @FXML
    private javafx.scene.control.Button gotoReport2Table;

    /*
     * This method is used to initialize the report 3 table
     * It has a method to initialize the report 3 table
     * and open the template in the editor.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Helper.SystemLanguage.getLanguage().equals("fr")) {
            setFrench();
        }

        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("AppointmentId"));
        StartTime.setCellValueFactory(new PropertyValueFactory<>("Start"));
        EndTime.setCellValueFactory(new PropertyValueFactory<>("End"));
        CustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        ContactName.setCellValueFactory(new PropertyValueFactory<>("ContactName"));

        ArrayList<Appointment> appointments = AppointmentsController.getReport3Data();
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList(appointments);
        Report3Table.setItems(appointmentList);

    }

    private void setFrench() {
        primaryLabel.setText("Rapport 3");
        secondaryLabel1.setText("Afficher les rendez-vous avec leur heure de début, heure de fin, nom du client,");
        secondaryLabel2.setText(", et le nom d'un contact spécifSique :");
        gotoCustomerTable.setText("Tableau des clients");
        gotoReport1Table.setText("Rapport 1");
        gotoReport2Table.setText("Rapport 2");
        AppointmentID.setText("ID");
        StartTime.setText("Heure de début");
        EndTime.setText("Heure de fin");
        CustomerName.setText("Nom du client");

    }

    /*
     * This method is used as a navigation to the customer table
     */
    @FXML
    public void gotoCustomerTable() {
        Navigate.goToCustomersPage();
    }

    /*
     * This method is used as a navigation to the appointment table
     */
    @FXML
    public void gotoReport1Table() {
        Navigate.gotoReport1Page();
    }

    /*
     * This method is used as a navigation to the report 2 table
     */
    @FXML
    public void gotoReport2Table() {
        Navigate.gotoReport2Page();
    }

}
