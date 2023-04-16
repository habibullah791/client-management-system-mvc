package UI.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Controller.AppointmentsController;
import Helper.DialogBox;
import Helper.Navigate;
import Model.Appointment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/*
    * This class is used to show the details of an appointment
    * It has a method to show the details of an appointment
*/
public class ShowAppointmentsDetailsController implements Initializable {

    @FXML
    TextField Appointment_ID;
    @FXML
    TextField Title;
    @FXML
    TextField Description;
    @FXML
    TextField Location;
    @FXML
    TextField Type;
    @FXML
    ComboBox StartTime;
    @FXML
    DatePicker StartDate;
    @FXML
    ComboBox EndTime;
    @FXML
    DatePicker EndDate;
    @FXML
    TextField Customer_name;
    @FXML
    ComboBox comboBox;

    @FXML
    private javafx.scene.control.Label primarylabel;
    @FXML
    private javafx.scene.control.Label idlabel;
    @FXML
    private javafx.scene.control.Label titlelabel;
    @FXML
    private javafx.scene.control.Label descLabel;
    @FXML
    private javafx.scene.control.Label locationLabel;
    @FXML
    private javafx.scene.control.Label typeLabel;
    @FXML
    private javafx.scene.control.Label startatLabel;
    @FXML
    private javafx.scene.control.Label endatLabel;
    @FXML
    private javafx.scene.control.Label customerlabel;

    // button
    @FXML
    private javafx.scene.control.Button deleteButton;
    @FXML
    private javafx.scene.control.Button editButton;

    public static Appointment appointment = new Appointment();

    /*
     * This method is used to show the details of an appointment
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (Helper.SystemLanguage.getLanguage().equals("fr")) {
            setFrench();
        }

        // initialize combo box with
        // assign the value of the combo box to a variable
        StartTime.getItems().addAll("08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00",
                "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "14:00:00", "14:30:00", "15:00:00",
                "15:30:00", "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00",
                "19:30:00", "20:00:00");

        // set the default value of the combo box
        StartTime.setValue("08:00:00");

        // assign the value of the combo box to a variable
        EndTime.getItems().addAll("08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00",
                "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "14:00:00", "14:30:00", "15:00:00",
                "15:30:00", "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00",
                "19:30:00", "20:00:00");

        // set the default value of the combo box
        EndTime.setValue("08:00:00");

        Appointment_ID.setText(String.valueOf(appointment.getAppointmentId()));
        Title.setText(String.valueOf(appointment.getTitle()));
        Description.setText(String.valueOf(appointment.getDescription()));
        Location.setText(String.valueOf(appointment.getLocation()));
        Type.setText(String.valueOf(appointment.getType()));
        StartTime.setValue("17:00:00");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = appointment.getStart().format(formatter);
        StartDate.setValue(LocalDate.parse(date, formatter));
        EndTime.setValue("17:00:00");
        String date2 = appointment.getEnd().format(formatter);
        EndDate.setValue(LocalDate.parse(date2, formatter));
        Customer_name.setText(String.valueOf(appointment.getCustomerName()));

    }

    public void setFrench() {
        primarylabel.setText("Détails du rendez-vous");
        idlabel.setText("ID du rendez-vous");
        titlelabel.setText("Titre");
        descLabel.setText("Description");
        locationLabel.setText("Emplacement");
        typeLabel.setText("Type");
        startatLabel.setText("Commence à");
        endatLabel.setText("Fini à");
        customerlabel.setText("Nom du client");
        deleteButton.setText("Effacer");
        editButton.setText("Modifier");

    }

    /*
     * This method is used as a handler for the delete button
     */
    @FXML
    public void deleteHandler() {
        System.out.println("Delete button clicked");
        int appointmentID = Integer.parseInt(Appointment_ID.getText());
        String title = Title.getText();
        String description = Description.getText();
        String location = Location.getText();
        String type = Type.getText();
        String startTime = StartTime.getValue().toString();
        LocalDate startDate = this.StartDate.getValue();
        String endTime = EndTime.getValue().toString();
        LocalDate endDate = this.EndDate.getValue();
        String customer_name = Customer_name.getText();

        if (appointmentID == 0 || title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty()
                || startTime.isEmpty()
                || endTime.isEmpty() || customer_name.isEmpty() || startDate == null
                || endDate == null) {
            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Erreur", "Veuillez remplir tous les champs requis");
            } else {
                DialogBox.showDialog("Error", "Please fill all the required fields");
                return;
            }
        }

        int isDeleted = AppointmentsController.deleteAppointment(appointmentID);

        if (isDeleted == 1) {
            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Succès", "Rendez-vous supprimé avec succès");
            } else {
                DialogBox.showDialog("Success", "Appointment deleted successfully");
            }
            Navigate.goToAppointmentsPage();
        } else {
            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Erreur", "Rendez-vous non supprimé");
            } else {
                DialogBox.showDialog("Error", "Appointment not deleted");
            }
        }
    }

    /*
     * This method is used as a handler for the edit button
     */
    @FXML
    public void editHandler() {
        System.out.println("Edit button clicked");

        int appointmentID = Integer.parseInt(Appointment_ID.getText());
        String title = Title.getText();
        String description = Description.getText();
        String location = Location.getText();
        String type = Type.getText();
        String startTime = StartTime.getValue().toString();
        LocalDate startDate = this.StartDate.getValue();
        String endTime = EndTime.getValue().toString();
        LocalDate endDate = this.EndDate.getValue();
        String Customer_name = this.Customer_name.getText();

        if (appointmentID == 0 || title.isEmpty() || description.isEmpty() || type.isEmpty() || startTime.isEmpty()
                || endTime.isEmpty() || Customer_name.isEmpty()) {
            DialogBox.showDialog("Error", "Please fill all the required fields");
            return;
        }

        int isAdded = AppointmentsController.updateAppointment(appointmentID, title, description, location, type,
                startTime,
                startDate, endTime, endDate, Customer_name);

        if (isAdded == 1) {
            DialogBox.showDialog("Success", "Appointment updated successfully");
            Navigate.goToAppointmentsPage();
        } else {
            DialogBox.showDialog("Error", "Appointment not updated");
        }
    }

}
