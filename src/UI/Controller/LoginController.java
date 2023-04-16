package UI.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controller.AppointmentsController;
import Controller.UserController;
import Helper.DialogBox;
import Helper.LoggedIn;
import Helper.LoginTracker;
import Helper.Navigate;
import Model.Appointment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/*
    * This class is used to login
    * It has a method to login
*/
public class LoginController implements Initializable {

    @FXML
    TextField usernameIP;
    @FXML
    TextField passwordIP;
    @FXML
    javafx.scene.control.Label usernameLabel;
    @FXML
    javafx.scene.control.Label passwordLabel;
    @FXML
    javafx.scene.control.Label primaryLabel;
    @FXML
    javafx.scene.control.Label secondaryLabel;
    @FXML
    javafx.scene.control.Button loginBtn;
    @FXML
    javafx.scene.control.Label locationLabel;

    /*
     * This initialize method is used to initialize the login page
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // get the location of the system
        ZoneId zoneId = ZoneId.systemDefault();
        // print the location of the system
        locationLabel.setText(zoneId.toString());
        // locationLabel.setText(zoneId.toString());

        if (Helper.SystemLanguage.getLanguage().equals("fr")) {
            setFrench();
        }
    }

    /*
     * This method is used to login
     */
    @FXML
    public void loginBtnHandler() throws FileNotFoundException, IOException {
        String username = usernameIP.getText().trim();
        String password = passwordIP.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username or password is empty");
            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Erreur", "Nom d'utilisateur ou mot de passe est vide");
            } else {
                DialogBox.showDialog("Error", "Username or password is empty");
            }
            return;
        } else {
            LoggedIn.user = UserController.authenticateUser(username, password);
            if (LoggedIn.user != null) {
                Navigate.goToCustomersPage();
                LoginTracker.logLoginAttempt(username, true);
                dueAppointment();
                cancelAppointments();
            } else {
                System.out.println("Username or password is incorrect");
                if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                    DialogBox.showDialog("Erreur", "Nom d'utilisateur ou mot de passe est incorrect");
                } else {
                    DialogBox.showDialog("Error", "Username or password is incorrect");
                }
                LoginTracker.logLoginAttempt(username, false);
            }
        }
    }

    private void cancelAppointments() {

        ArrayList<Appointment> appointments = AppointmentsController.canceledAppointments();

        if (appointments.size() > 0) {

            // create a string to store all the appointment ids
            String appointmentIds = "";
            // loop through the appointments
            for (Appointment appointment : appointments) {
                // add the appointment id to the string
                appointmentIds += appointment.getAppointmentId() + " ,  ";
            }

            // create a string to store the appointment type
            String appointmentType = "";
            // loop through the appointments
            for (Appointment appointment : appointments) {
                // add the appointment type to the string
                appointmentType += appointment.getType() + " ,  ";
            }
            if (Helper.SystemLanguage.getLanguage().equals("en")) {
                DialogBox.showDialog("Canceled Appointments", "The following appointments have been canceled respectively: \nAppointment ID: "
                    + appointmentIds + "\nAppointment Type: " + appointmentType);
            } else if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Rendez-vous annulés", "Les rendez-vous suivants ont été annulés respectivement: \nID de rendez-vous: "
                    + appointmentIds + "\nType de rendez-vous: " + appointmentType);
            }
        }
    }

    /*
     * This method is used to show the due appointment
     */
    public void dueAppointment() {
        ArrayList<Appointment> appointments = AppointmentsController.isAppointmentIn15Minute();

        if (appointments.size() > 0) {
            int appointmentId = appointments.get(0).getAppointmentId();
            String appointmentTime = appointments.get(0).getStart().toString();

            String[] dateTime = appointmentTime.toString().split("T");
            String date = dateTime[0];
            String time = dateTime[1];
            String[] timeArray = time.split(":");
            int hour = Integer.parseInt(timeArray[0]);
            int minute = Integer.parseInt(timeArray[1]);
            String ampm = "AM";
            if (hour > 12) {
                hour -= 12;
                ampm = "PM";
            }

            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Alerte de rendez-vous",
                        "Vous avez un rendez-vous dans 15 minutes. \nID de rendez-vous: "
                                + appointmentId + "\nHeure:  " + hour + ":" + minute + " " + ampm + "\nDate: " + date);
            } else {

                DialogBox.showDialog("Appointment Alert", "You have an appointment in 15 minutes. \nAppointment ID: "
                        + appointmentId + "\nTime:  " + hour + ":" + minute + " " + ampm + "\nDate: " + date);
            }
        } else {
            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Alerte de rendez-vous", "Vous n'avez pas de rendez-vous dans 15 minutes.");
            } else {
                DialogBox.showDialog("Appointment Alert", "You have no appointment in 15 minutes.");
            }
        }
    }

    public void setFrench() {
        usernameLabel.setText("Nom d'utilisateur");
        passwordLabel.setText("Mot de passe");
        primaryLabel.setText("Système de gestion des clients");
        secondaryLabel.setText("Connectez-vous ici");
        loginBtn.setText("Connexion");
    }
}
