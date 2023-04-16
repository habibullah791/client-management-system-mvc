package UI.Controller;

import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;

import Controller.AppointmentsController;
import Helper.Navigate;
import Model.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/*
    * This class is used to display the report 1
    * It has a method to display the report 1
*/
public class Report1Controller implements Initializable {

    @FXML
    private TableView<Report> Report1Table;
    @FXML
    private TableColumn<Report, String> ReportType;
    @FXML
    private TableColumn<Report, Integer> Month;
    @FXML
    private TableColumn<Report, Integer> NumberOfAppointment;
    @FXML
    javafx.scene.control.Label primarylabel;
    @FXML
    javafx.scene.control.Label secondarylabel;
    @FXML
    javafx.scene.control.Button gotoCustomerTable;
    @FXML
    javafx.scene.control.Button gotoReport2Table;
    @FXML
    javafx.scene.control.Button gotoReport3Table;


    /*
     * This method is used to display the report 1 in the table
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (Helper.SystemLanguage.getLanguage().equals("fr")) {
            setFrench();
        }

        ReportType.setCellValueFactory(new PropertyValueFactory<>("ReportType"));
        Month.setCellValueFactory(new PropertyValueFactory<>("Month"));
        NumberOfAppointment.setCellValueFactory(new PropertyValueFactory<>("NumberOfAppointment"));

        ArrayList<Report> report1 = AppointmentsController.getReport1Data();
        ObservableList<Report> report1List = FXCollections.observableArrayList(report1);
        Report1Table.setItems(report1List);
    }

    private void setFrench() {
        primarylabel.setText("Rapport 1");
        secondarylabel.setText("Nombre total de rendez-vous clients par type et par mois");
        ReportType.setText("Type de rapport");
        Month.setText("Mois");
        NumberOfAppointment.setText("Nombre de rendez-vous");
        gotoCustomerTable.setText("Tableau des clients");
        gotoReport2Table.setText("Rapport 2");
        gotoReport3Table.setText("Rapport 3");
    }

    /*
     * This method is used to go to the customer table
     */
    @FXML
    public void gotoCustomerTable() {
        Navigate.goToCustomersPage();
    }

    /*
     * This method is used to go to the report 2 table
     */
    @FXML
    public void gotoReport2Table() {
        Navigate.gotoReport2Page();
    }

    /*
     * This method is used to go to the report 3 table
     */
    @FXML
    public void gotoReport3Table() {
        Navigate.gotoReport3Page();
    }

}
