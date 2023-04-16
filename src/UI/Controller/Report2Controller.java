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
    * This class is used to display the report 2
    * It has a method to display the report 2

 */
public class Report2Controller implements Initializable {

    @FXML
    private TableView<Appointment> Report2Table;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentID;
    @FXML
    private TableColumn<Appointment, String> Title;
    @FXML
    private TableColumn<Appointment, String> Description;
    @FXML
    private TableColumn<Appointment, String> Type;
    @FXML
    private TableColumn<Appointment, LocalDateTime> StartTime;
    @FXML
    private TableColumn<Appointment, LocalDateTime> EndTime;
    @FXML
    private TableColumn<Appointment, Integer> CustomerID;

    // labels
    @FXML
    javafx.scene.control.Label primarylabel;
    @FXML
    javafx.scene.control.Label secondarylabel1;
    @FXML
    javafx.scene.control.Label secondarylabel2;

    // buttons
    @FXML
    javafx.scene.control.Button gotoCustomerTable;
    @FXML
    javafx.scene.control.Button gotoReport1Table;
    @FXML
    javafx.scene.control.Button gotoReport3Table;

    /*
     * This method is used to initialize the report 2 table
     * It has a method to initialize the report 2 table
     * and open the template in the editor.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Helper.SystemLanguage.getLanguage().equals("fr")) {
            setFrench();
        }

        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("AppointmentId"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        StartTime.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        ArrayList<Appointment> report2 = AppointmentsController.getReport2Data();
        ObservableList<Appointment> report2List = FXCollections.observableArrayList(report2);
        Report2Table.setItems(report2List);

    }

    private void setFrench() {
        primarylabel.setText("Rapport 2");
        secondarylabel1.setText("Un calendrier pour chaque contact de votre organisation qui comprend l'ID de rendez-vous, le titre, le type");
        secondarylabel2.setText("et description, date et heure de début, date et heure de fin et ID client");
        gotoCustomerTable.setText("Tableau des clients");
        gotoReport1Table.setText("Rapport 1");
        gotoReport3Table.setText("Rapport 2");
        AppointmentID.setText("ID");
        Title.setText("Titre");
        Description.setText("La description");
        Type.setText("Type");
        StartTime.setText("Heure de début");
        EndTime.setText("Heure de fin");
        CustomerID.setText("ID client");
    }

    /*
     * This method is used as a navigation to the customer table
     */
    @FXML
    public void gotoCustomerTable() {
        Navigate.goToCustomersPage();
    }

    /*
     * This method is used as a navigation to the report 1 table
     */
    @FXML
    public void gotoReport1Table() {
        Navigate.gotoReport1Page();
    }

    /*
     * This method is used as a navigation to the report 3 table
     */
    @FXML
    public void gotoReport3Table() {
        Navigate.gotoReport3Page();
    }

}
