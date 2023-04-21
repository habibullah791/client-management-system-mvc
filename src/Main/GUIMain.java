package Main;

import Helper.Navigate;

import java.time.ZoneId;
import java.util.Locale;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * This class is used to run the application
 * It has a method to run the application
 * It also has a method to start the application
 */
public class GUIMain extends Application {
    public static Parent root;
    public static Stage primaryStage;

    /**
     * This method is used to run the application
     */
    public static void run() {
        Application.launch(GUIMain.class, (java.lang.String[]) null);
    }

    /**
     * This method is used to start the application
     */
    @Override
    public void start(Stage primaryStage) {

        // determine the system language
        Locale locale = Locale.getDefault();

        System.out.println("System language: " + locale.getLanguage());
        // set the language
        if (locale.getLanguage().equals("fr")) {
            Helper.SystemLanguage.setLanguage("fr");
        } else {
            Helper.SystemLanguage.setLanguage("en");
        }
        GUIMain.primaryStage = primaryStage;
        Navigate.goToLoginPage();
        // Navigate.goToAppointmentsPage();
    }

    /**
     * This method is used to run the application
     */
    public static void main(String[] args) {

        // load the database
        run();
    }

}
