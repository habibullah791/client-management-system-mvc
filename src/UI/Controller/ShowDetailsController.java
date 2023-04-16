package UI.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.CustomersController;
import Helper.DialogBox;
import Helper.Navigate;
import Model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ShowDetailsController implements Initializable {

    @FXML
    TextField CustomerID;
    @FXML
    TextField CustomerName;
    @FXML
    TextField Address;
    @FXML
    TextField PostalCode;
    @FXML
    TextField Phone;
    @FXML
    TextField Division;
    @FXML
    TextField Country;
    @FXML
    ComboBox<String> comboBox;

    // label
    @FXML
    private javafx.scene.control.Label primarylabel;
    @FXML
    private javafx.scene.control.Label customerIdLabel;
    @FXML
    private javafx.scene.control.Label customerNameLabel;
    @FXML
    private javafx.scene.control.Label addressLabel;
    @FXML
    private javafx.scene.control.Label postalCodeLabel;
    @FXML
    private javafx.scene.control.Label phoneLabel;
    @FXML
    private javafx.scene.control.Label divisionLabel;
    @FXML
    private javafx.scene.control.Label countryLabel;

    // button
    @FXML
    private javafx.scene.control.Button editButton;
    @FXML
    private javafx.scene.control.Button deleteButton;

    public static Customer customer = null;

    /*
     * This method is used to initialize the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (Helper.SystemLanguage.getLanguage().equals("fr")) {
            setFrench();
        }

        CustomerID.setText(String.valueOf(customer.getCustomerId()));
        CustomerName.setText(customer.getCustomerName());
        Address.setText(customer.getAddress());
        PostalCode.setText(customer.getPostalCode());
        Phone.setText(customer.getPhone());
        Division.setText(customer.getDivision());
        Country.setText(customer.getCountry());
    }

    private void setFrench() {
        primarylabel.setText("Détails du client");
        customerIdLabel.setText("ID du client");
        customerNameLabel.setText("Nom du client");
        addressLabel.setText("Adresse");
        postalCodeLabel.setText("Code postal");
        phoneLabel.setText("Téléphone");
        divisionLabel.setText("Division");
        countryLabel.setText("Pays");
        editButton.setText("Modifier");
        deleteButton.setText("Supprimer");

    }

    /*
     * This method is used as an event handler for delete button
     */
    @FXML
    public void deleteCustomer() {

        System.out.println("Delete button clicked");
        int customerID = Integer.parseInt(CustomerID.getText());
        String customerName = CustomerName.getText();
        String address = Address.getText();
        String postalCode = PostalCode.getText();
        String phone = Phone.getText();
        String division = Division.getText();
        String country = Country.getText();

        if (customerID == 0 || customerName.isEmpty() || address.isEmpty() || postalCode.isEmpty() || phone.isEmpty()
                || division.isEmpty() || country.isEmpty()) {
            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Erreur", "Veuillez remplir tous les champs");
            } else {
                DialogBox.showDialog("Error", "Please fill all the fields");
                return;
            }
        }

        int isDeleted = CustomersController.deleteCustomer(customerID);

        if (isDeleted == 0) {
            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Erreur", "Client non supprimé");
            } else {
                DialogBox.showDialog("Error", "Customer not Deleted");
            }
        } else {
            if (Helper.SystemLanguage.getLanguage().equals("fr")) {
                DialogBox.showDialog("Succès", "Client supprimé avec succès");
            } else {
                DialogBox.showDialog("Success", "Customer Deleted successfully");
            }
            Navigate.goToCustomersPage();
        }
    }

    /*
     * This method is used as an event handler for edit button
     */
    @FXML
    public void editCustomer() {

        System.out.println("Update button clicked");
        int customerID = Integer.parseInt(CustomerID.getText());
        String customerName = CustomerName.getText();
        String address = Address.getText();
        String postalCode = PostalCode.getText();
        String phone = Phone.getText();
        String division = Division.getText();
        String country = Country.getText();

        if (customerID == 0 || customerName.isEmpty() || address.isEmpty() || postalCode.isEmpty() || phone.isEmpty()
                || division.isEmpty() || country.isEmpty()) {
            DialogBox.showDialog("Error", "Please fill all the fields");
            return;
        }
        int isAdded = CustomersController.updateCustomer(customerID, customerName, address, postalCode, phone, division,
                country);

        if (isAdded == 0) {
            DialogBox.showDialog("Error", "Customer not updated");
        } else {
            DialogBox.showDialog("Success", "Customer updated successfully");
            Navigate.goToCustomersPage();
        }
    }
}