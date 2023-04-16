package UI.Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Controller.CustomersController;

import Helper.Navigate;
import Model.Customer;

/*
    * This class is used to show the customers
    * It has a method to show the customers
    * and open the template in the editor.
*/
public class CustomerTableController implements Initializable {

    @FXML
    private TableView<Customer> customersTable;
    @FXML
    private TableColumn<Customer, Integer> Customer_ID;
    @FXML
    private TableColumn<Customer, String> Customer_Name;
    @FXML
    private TableColumn<Customer, String> Address;
    @FXML
    private TableColumn<Customer, String> Postal_Code;
    @FXML
    private TableColumn<Customer, String> Phone;
    @FXML
    private TableColumn<Customer, LocalDateTime> Create_Date;
    @FXML
    private TableColumn<Customer, String> Created_By;
    @FXML
    private TableColumn<Customer, LocalDateTime> Last_Update;
    @FXML
    private TableColumn<Customer, String> Last_Updated_By;
    @FXML
    private TableColumn<Customer, String> Division;
    @FXML
    private TableColumn<Customer, String> Country;
    @FXML
    private javafx.scene.control.Button Reports;
    @FXML
    private javafx.scene.control.Button showAppointments;
    @FXML
    private javafx.scene.control.Button addCustomer;
    @FXML
    private javafx.scene.control.Label primaryLabel;
    @FXML
    private javafx.scene.control.Label secondaryLabel;
    @FXML
    private javafx.scene.control.Label additionalLabel;

    /*
     * This method is used to initialize the controller class.
     * It has a method to show the customers
     * and open the template in the editor.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // check if the language is french
        if (Helper.SystemLanguage.getLanguage().equals("fr")) {
            setFrench();
        } else {
            setEnglish();
        }
        loadCustomers();

    }

    /*
     * This method is used to show the customers
     * It has a method to show the customers
     * and open the template in the editor.
     */
    public void loadCustomers() {

        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        Customer_Name.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Postal_Code.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Create_Date.setCellValueFactory(new PropertyValueFactory<>("CreateDate"));
        Created_By.setCellValueFactory(new PropertyValueFactory<>("CreatedBy"));
        Last_Update.setCellValueFactory(new PropertyValueFactory<>("LastUpdate"));
        Last_Updated_By.setCellValueFactory(new PropertyValueFactory<>("LastUpdatedBy"));
        Division.setCellValueFactory(new PropertyValueFactory<>("Division"));
        Country.setCellValueFactory(new PropertyValueFactory<>("Country"));

        ArrayList<Customer> customers = CustomersController.getAllCustomers();
        ObservableList<Customer> customersList = FXCollections.observableArrayList();
        customersList.addAll(customers);
        // print customersList
        customersTable.setItems(customersList);

        customersTable.setRowFactory(tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Customer rowData = row.getItem();
                    Navigate.goToShowCustomerDetailsPage(rowData);
                }
            });
            return row;
        });
    }

    /*
     * This method is used as a handler for the add customer button
     */
    @FXML
    public void addCustomerHandler() {
        System.out.println("Add Customer");
        Navigate.goToAddCustomerPage();
    }

    /*
     * This method is used as a handler for the appointments button
     */
    @FXML
    public void showAppointmentsHandler() {
        Navigate.goToAppointmentsPage();
    }

    /*
     * This method is used as a handler for the reports button
     */
    @FXML
    public void showReportsHandler() {
        Navigate.gotoReport1Page();
    }

    public void setFrench() {
        primaryLabel.setText("Informations clients");
        secondaryLabel.setText("Double-cliquez pour afficher les informations client à modifier ou à supprimer");
        addCustomer.setText("Ajouter un client");
        showAppointments.setText("Afficher les rendez-vous");
        Reports.setText("Rapports");
        additionalLabel.setText("Allez voir les rapports :");
        Customer_ID.setText("ID");
        Customer_Name.setText("Nom");
        Address.setText("Adresse");
        Postal_Code.setText("Code postal");
        Phone.setText("Téléphone");
        Create_Date.setText("Créer");
        Created_By.setText("Créé par");
        Last_Update.setText("Mise à jour");
        Last_Updated_By.setText("Mis à jour par");
        Division.setText("Division");
        Country.setText("Pays");
    }

    public void setEnglish() {
        primaryLabel.setText("Customers Information");
        secondaryLabel.setText("Double Click To View Customer Info For Edit or Delete");
        addCustomer.setText("Add Customer");
        showAppointments.setText("Show Appointments");
        Reports.setText("Reports");
        additionalLabel.setText("Go to see the reports : ");
    }
}