/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controller.AppointmentsController;
import Controller.CustomersController;
import Helper.DialogBox;
import Helper.Navigate;
import Model.Appointment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/*
    * This class is used to add an appointment
    * It has a method to add an appointment
*/
public class AddAppointmentController implements Initializable {

    @FXML
    TextField Title;
    @FXML
    TextField Description;
    @FXML
    TextField Type;
    @FXML
    TextField Location;
    @FXML
    ComboBox Contact;
    @FXML
    ComboBox StartTime;
    @FXML
    DatePicker StartDate;
    @FXML
    ComboBox EndTime;
    @FXML
    DatePicker EndDate;
    @FXML
    TextField CustomerName;
    @FXML
    ComboBox ContactInfo;

    @FXML
    javafx.scene.control.Label primaryLabel;
    @FXML
    javafx.scene.control.Label titleLabel;
    @FXML
    javafx.scene.control.Label descLabel;
    @FXML
    javafx.scene.control.Label locationLabel;
    @FXML
    javafx.scene.control.Label typeLabel;
    @FXML
    javafx.scene.control.Label startatLabel;
    @FXML
    javafx.scene.control.Label endatLabel;
    @FXML
    javafx.scene.control.Label customerNameLabel;
    @FXML
    javafx.scene.control.Button addAppointment;
    @FXML
    javafx.scene.control.Button gotoAppointment;

    /*
     * This method is used to add an appointment data to the table
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (Helper.SystemLanguage.getLanguage().equals("fr")) {
            setFrench();
        }

        Contact.getItems().addAll("Anika Costa", "Daniel Garcia", "Li Lee");

        StartTime.getItems().addAll("08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00",
                "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "14:00:00", "14:30:00", "15:00:00",
                "15:30:00",
                "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00", "19:30:00",
                "20:00:00",
                "20:30:00", "21:00:00", "21:30:00", "22:00:00");

        EndTime.getItems().addAll("08:00:00 ", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00",
                "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "14:00:00", "14:30:00", "15:00:00",
                "15:30:00",
                "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00", "19:30:00",
                "20:00:00",
                "20:30:00", "21:00:00", "21:30:00", "22:00:00");

        Contact.setValue("Li Lee");
        StartTime.setValue("08:00:00");
        EndTime.setValue("08:30:00");

    }

    private void setFrench() {
        primaryLabel.setText("Ajouter un rendez-vous");
        titleLabel.setText("Titre");
        descLabel.setText("Description");
        locationLabel.setText("Emplacement");
        typeLabel.setText("Taper");
        startatLabel.setText("Commence à");
        endatLabel.setText("Fini à");
        customerNameLabel.setText("Nom du client");
        addAppointment.setText("Ajouter un rendez-vous");
        gotoAppointment.setText("Dos");

    }

    /*
     * This method is used as a handler for the add appointment button
     */
    public void addAppointmentHandler() {

        String appointmentTitle = Title.getText().trim();
        String description = Description.getText().trim();
        String type = Type.getText().trim();
        String location = Location.getText().trim();
        String contact = Contact.getValue().toString();
        String startTime = StartTime.getValue().toString();
        String startDate = StartDate.getValue().toString();
        String endTime = EndTime.getValue().toString();
        String endDate = EndDate.getValue().toString();
        String customerName = CustomerName.getText().trim();

        int isCustomerAvailable = CustomersController.isCustomerAvailable(customerName);

        // convert the date and time to UTC
        String startDateTime = startDate + " " + startTime.trim();
        String endDateTime = endDate + " " + endTime.trim();

        startDateTime = Helper.DateTimeHelper.convertToUTC(startDateTime);
        endDateTime = Helper.DateTimeHelper.convertToUTC(endDateTime);

        boolean isTimeValid = Helper.DateTimeHelper.isStartTimeGreater(startDateTime, endDateTime);
        ArrayList<Appointment> appointments = AppointmentsController.isOverlappingAppointment(startDateTime,
                endDateTime);

                if(!isTimeValid) {
                    System.out.println("End time cannot be less or equal to start time");
                }
                

        if (!isTimeValid) {
            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Erreur",
                        "L'heure de fin ne peut pas être inférieure ou égale à l'heure de début");
            } else {
                DialogBox.showDialog("Error", "End time cannot be less  or equal to start time");
            }
            return;
        }
        if (appointments.size() > 0) {
            String start = Helper.DateTimeHelper.convertToLocalDaateTime(endDateTime);
            String end = Helper.DateTimeHelper.convertToLocalDaateTime(endDateTime);
            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Rendez-vous superposé",
                        "ID du rendez-vous: " + appointments.get(0).getAppointmentId() + "\nHeure de début: "
                                + start + "\nHeure de fin: " + end);
            } else {
                DialogBox.showDialog("Overlapping Appointment",
                        "Appointment ID: " + appointments.get(0).getAppointmentId() + "\nStart Time: "
                                + start + "\nEnd Time: " + end);
            }
            return;

        } else if (isCustomerAvailable == 0) {
            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Client non trouvé !", "Veuillez d'abord ajouter le client");
            } else {
                DialogBox.showDialog("Customer Not Found !", "Please add the customer first");
            }
            Navigate.goToAddCustomerPage();
        } else {
            if (appointmentTitle.isEmpty() || description.isEmpty() || type.isEmpty() || location.isEmpty()
                    || contact.isEmpty() || startTime.isEmpty() || startDate.isEmpty() || endTime.isEmpty()
                    || endDate.isEmpty() || customerName.isEmpty()) {
                if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                    DialogBox.showDialog("Erreur", "Veuillez remplir tous les champs");
                } else {
                    DialogBox.showDialog("Error", "Please fill all the fields");
                }
            } else {

                int isAdded = AppointmentsController.addAppointment(appointmentTitle, description, type, location,
                        contact,
                        startDateTime, endDateTime, customerName);

                if (isAdded == 1) {
                    if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                        DialogBox.showDialog("Succès", "Rendez-vous ajouté avec succès");
                    } else {
                        DialogBox.showDialog("Success", "Appointment added successfully");
                    }
                    Navigate.goToAppointmentsPage();
                } else {
                    if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                        DialogBox.showDialog("Erreur", "Rendez-vous non ajouté");
                    } else {
                        DialogBox.showDialog("Error", "Appointment not added");
                    }
                }
            }
        }
    }

    public void gotoAppointmentHandler() {
        Navigate.goToAppointmentsPage();
    }
}
