package UI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import Controller.CustomersController;
import Helper.DialogBox;
import Helper.Navigate;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

/*
    * This class is used to add a customer
    * It has a method to add a customer
*/
public class AddNewCustomerController implements Initializable {

    @FXML
    TextField Customer_Name;
    @FXML
    TextField Address;
    @FXML
    TextField Postal_Code;
    @FXML
    TextField Phone;
    @FXML
    ComboBox divisionComboBox;
    @FXML
    ComboBox countryComboBox;

    @FXML
    javafx.scene.control.Label primaryLabel;
    @FXML
    javafx.scene.control.Label nameLabel;
    @FXML
    javafx.scene.control.Label addressLabel;
    @FXML
    javafx.scene.control.Label postalCodeLabel;
    @FXML
    javafx.scene.control.Label phoneLabel;
    @FXML
    javafx.scene.control.Label divisionLabel;
    @FXML
    javafx.scene.control.Button addCustomer;
    @FXML
    javafx.scene.control.Button gotoCustomer;

    /*
     * This method is used to add a customer data to the table
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if(Helper.SystemLanguage.getLanguage().equals("fr")) {
            setFrench();
        }

        String[] usDivisions = {
                "Alabama",
                "Alaska",
                "Arizona",
                "Arkansas",
                "California",
                "Colorado",
                "Connecticut",
                "Delaware",
                "District of Columb",
                "Florida",
                "Georgia",
                "Hawaii",
                "Idaho",
                "Illinois",
                "Indiana",
                "Iowa",
                "Kansas",
                "Kentucky",
                "Louisiana",
                "Maine",
                "Maryland",
                "Massachusetts",
                "Michigan",
                "Minnesota",
                "Mississippi",
                "Missouri",
                "Montana",
                "Nebraska",
                "Nevada",
                "New Hampshire",
                "New Jersey",
                "New Mexico",
                "New York",
                "North Carolina",
                "North Dakota",
                "Ohio",
                "Oklahoma",
                "Oregon",
                "Pennsylvania",
                "Rhode Island",
                "South Carolina",
                "South Dakota",
                "Tennessee",
                "Texas",
                "Utah",
                "Vermont",
                "Virginia",
                "Washington",
                "West Virginia",
                "Wisconsin",
                "Wyoming"
        };

        String[] canadaDivisions = {
                "Alberta",
                "British Columbia",
                "Manitoba",
                "New Brunswick",
                "Newfoundland and Labrador",
                "Northwest Territories",
                "Nova Scotia",
                "Nunavut",
                "Ontario",
                "Prince Edward Island",
                "Québec",
                "Saskatchewan",
                "Yukon"
        };
        String[] ukDivisions = {
                "England",
                "Wales",
                "Scotland",
                "Northern Ireland"
        };
        countryComboBox.getItems().addAll(
                "U.S",
                "UK",
                "Canada");
        countryComboBox.setValue("U.S");
        divisionComboBox.getItems().addAll(usDivisions);
        divisionComboBox.setValue(usDivisions[0]);

        countryComboBox.valueProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem.equals("U.S")) {
                divisionComboBox.getItems().clear();
                divisionComboBox.getItems().addAll(usDivisions);
                divisionComboBox.setValue(usDivisions[0]);
            } else if (newItem.equals("UK")) {
                divisionComboBox.getItems().clear();
                divisionComboBox.getItems().addAll(ukDivisions);
                divisionComboBox.setValue(ukDivisions[0]);
            } else if (newItem.equals("Canada")) {
                divisionComboBox.getItems().clear();
                divisionComboBox.getItems().addAll(canadaDivisions);
                divisionComboBox.setValue(canadaDivisions[0]);
            }
        });

        // set the default value
    }

    private void setFrench() {
        primaryLabel.setText("Ajouter un nouveau client");
        nameLabel.setText("Nom du client");
        addressLabel.setText("Adresse");
        postalCodeLabel.setText("Code postal");
        phoneLabel.setText("Téléphone");
        divisionLabel.setText("Division");
        addCustomer.setText("Ajouter un client");
        gotoCustomer.setText("Dos");

    }

    /*
     * This method is used as an event handler for the add customer button
     */
    @FXML
    public void addCustomerHandler() {
        System.out.println("Adding new customer to the database");

        // get the values from the text fields
        String customerName = Customer_Name.getText();
        String address = Address.getText();
        String postalCode = Postal_Code.getText();
        String phone = Phone.getText();
        String division = divisionComboBox.getValue().toString();
        String country = countryComboBox.getValue().toString();

        if (customerName.isEmpty() || address.isEmpty() || postalCode.isEmpty() || phone.isEmpty()) {
            if(Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Erreur", "Veuillez remplir tous les champs");
            } else {
                DialogBox.showDialog("Error", "Please fill all the fields");
            }
        }
        if (!postalCode.matches("[0-9]+") || !phone.matches("[0-9]+")) {
            if(Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Erreur", "Veuillez entrer un code postal et un numéro de téléphone valides");
            } else {
                DialogBox.showDialog("Error", "Please enter valid postal code and phone number");
                return;
            }
        }

        int isAdded = CustomersController.addCustomer(customerName, address, postalCode, phone, division, country);

        if (isAdded == 1) {
            if(Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Succès", "Client ajouté avec succès");
            } else {
                DialogBox.showDialog("Success", "Customer added successfully");
            }
            Navigate.goToCustomersPage();
        } else {
            if(Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Erreur", "Quelque chose a mal tourné");
            } else {
                DialogBox.showDialog("Error", "Something went wrong");
            }
        }
    }

    public void gotoCustomerHandler() {
        Navigate.goToCustomersPage();
    }
}