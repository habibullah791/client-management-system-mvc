package UI.Controller;

import Model.Appointment;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controller.AppointmentsController;
import Helper.Navigate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/*
    * This class is used to show the appointments by month
    * It has a method to show the appointments by month
    * and open the template in the editor.
 */
public class AppointmentsTableController implements Initializable {

    @FXML
    private TableView<Appointment> appointmentsTable;
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
    private TableColumn<Appointment, LocalDateTime> Start;
    @FXML
    private TableColumn<Appointment, LocalDateTime> End;
    @FXML
    private TableColumn<Appointment, LocalDateTime> Create_Date;
    @FXML
    private TableColumn<Appointment, String> Created_By;
    @FXML
    private TableColumn<Appointment, LocalDateTime> Last_Updated;
    @FXML
    private TableColumn<Appointment, String> Last_Updated_By;
    @FXML
    private TableColumn<Appointment, String> Customer_Name;
    @FXML
    private TableColumn<Appointment, String> User_Name;
    @FXML
    private RadioButton ScheduleMonth;
    @FXML
    private RadioButton ScheduleWeek;
    @FXML
    private javafx.scene.control.Label primaryLabel;
    @FXML
    private javafx.scene.control.Label secondaryLabel;
    @FXML
    private javafx.scene.control.Label additionalLabel;
    @FXML
    private javafx.scene.control.Button gotoCustomerPage;
    @FXML
    private javafx.scene.control.Button gotoAddAppointment;

    /*
     * This method is used to show all the appointments in the table
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Helper.SystemLanguage.getLanguage().equals("fr")) {
            setFrench();
        }

        loadAppointments();
    }

    private void setFrench() {
        primaryLabel.setText("Détails des rendez-vous");
        secondaryLabel
                .setText("Double-cliquez pour afficher les informations sur les rendez-vous à modifier ou à supprimer");
        additionalLabel.setText("Calendrier des rendez-vous par");
        gotoCustomerPage.setText("Page client");
        gotoAddAppointment.setText("Ajouter un rendez-vous");
        ScheduleMonth.setText("Mois");
        ScheduleWeek.setText("Semaine");
        Appointment_ID.setText("ID");
        Title.setText("Titre");
        Description.setText("Description");
        Location.setText("Emplacement");
        Type.setText("Type");
        Start.setText("Début");
        End.setText("Fin");
        Create_Date.setText("Création");
        Created_By.setText("Créé par");
        Last_Updated.setText("Mis à jour");
        Last_Updated_By.setText("Mis à jour par");
        Customer_Name.setText("Client");
        User_Name.setText("Utilisateur");
    }

    /*
     * This method is used to load all the appointments in the table
     */
    public void loadAppointments() {

        Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("AppointmentId"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        Create_Date.setCellValueFactory(new PropertyValueFactory<>("CreateDate"));
        Created_By.setCellValueFactory(new PropertyValueFactory<>("CreatedBy"));
        Last_Updated.setCellValueFactory(new PropertyValueFactory<>("LastUpdate"));
        Last_Updated_By.setCellValueFactory(new PropertyValueFactory<>("LastUpdatedBy"));
        Customer_Name.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        User_Name.setCellValueFactory(new PropertyValueFactory<>("UserName"));

        Start.setCellFactory(column -> new TableCell<Appointment, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean empty) {
                super.updateItem(dateTime, empty);
                if (empty || dateTime == null) {
                    setText(null);
                } else {
                    // get the start time of the appointment
                    String start = dateTime.toString();
                    // split the start time to get the date and time
                    String[] startSplit = start.split("T");
                    // get the date
                    String startDate = startSplit[0];
                    // get the time
                    String startTime = startSplit[1];
                    // check if the time is like hh:mm add seconds
                    if (startTime.length() == 5) {
                        startTime = startTime + ":00";
                    }
                    String startDateTime = startDate + " " + startTime;
                    startDateTime = Helper.DateTimeHelper.convertToLocalDaateTime(startDateTime);
                    setText(startDateTime);
                }
            }
        });

        End.setCellFactory(column -> new TableCell<Appointment, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean empty) {
                super.updateItem(dateTime, empty);
                if (empty || dateTime == null) {
                    setText(null);
                } else {
                    // get the end time of the appointment
                    String end = dateTime.toString();
                    // split the end time to get the date and time
                    String[] endSplit = end.split("T");
                    // get the date
                    String endDate = endSplit[0];
                    // get the time
                    String endTime = endSplit[1];
                    // check if the time is like hh:mm add seconds
                    if (endTime.length() == 5) {
                        endTime = endTime + ":00";
                    }
                    String endDateTime = endDate + " " + endTime;
                    endDateTime = Helper.DateTimeHelper.convertToLocalDaateTime(endDateTime);
                    setText(endDateTime);
                }
            }
        });

        ArrayList<Appointment> appointments = AppointmentsController.getAllAppointments();
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        appointmentList.addAll(appointments);
        appointmentsTable.setItems(appointmentList);

        appointmentsTable.setRowFactory(tv -> {
            TableRow<Appointment> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Appointment rowData = row.getItem();
                    Navigate.goToShowAppointmentsDetailsPage(rowData);
                }
            });
            return row;
        });

    }

    /*
     * This method is used to go to the add appointment page
     */
    public void gotoCustomerPageHandler() {
        Navigate.goToCustomersPage();
    }

    /*
     * This method is used to go to the add appointment page
     */
    public void gotoAddAppointmentHandler() {
        Navigate.goToAddAppointmentPage();
    }

    /*
     * This method is used to go to the add appointment page
     */
    public void getSchedule() {
        if (ScheduleMonth.isSelected()) {
            System.out.println("ScheduleMonth clicked ");
            Navigate.gotoAppointmentMonthPage();
        } else {
            System.out.println("ScheduleWeek clicked ");
            Navigate.goToCustomersPage();
            ;
        }
    }
}
