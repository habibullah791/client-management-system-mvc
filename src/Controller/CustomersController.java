package Controller;

import DB.DBHandler;
import Model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is used to control the customers table in the database
 * It has methods to add, remove, update, and load customers from the database
 * It also has a method to check if a customer is available in the database
 * If it is not available, it will add it to the database
 * It also has a method to add a customer to the database
 */
public class CustomersController {

    private static DBHandler dbHandler;

    ArrayList<Customer> customers = new ArrayList<Customer>();

    /**
     * This method is used to connect to the database
     * It returns a string to indicate if the connection was successful or not
     */
    public String connectToDatabase() {
        return dbHandler.connectToDatabase();
    }

    /**
     * This method is used to close the connection to the database
     */
    public void closeConnection() {
        dbHandler.closeConnection();
    }

    /**
     * This method is called when a new instance of this class is created
     */
    public CustomersController() {
        dbHandler = new DBHandler();
        connectToDatabase();
    }

    /**
     * This method is used to add a new customer to the database
     * It takes a customer object as a parameter
     */
    public void addNewCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * This method is used to remove a customer from the database
     * It takes a customer object as a parameter
     */
    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    /**
     * This method is used to update a customer in the database
     * It takes a customer object as a parameter
     */
    public void updateCustomer(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() == customer.getCustomerId()) {
                customers.set(i, customer);
            }
        }
    }

    /**
     * This method is used to get all customers from the database
     */
    public static ArrayList<Customer> getAllCustomers() {
        CustomersController customersController = new CustomersController();
        customersController.loadCustomers();
        return customersController.customers;
    }

    /**
     * This method is used get customer by ID
     * 
     * @param customerId
     * @return customer
     */
    public Customer getCustomerByID(int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() == customerId) {
                return customers.get(i);
            }
        }
        return null;
    }

    /**
     * This method is used get customer by name
     * 
     * @param customerName
     * @return customer
     */
    public Customer getCustomerByName(String customerName) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerName().equals(customerName)) {
                return customers.get(i);
            }
        }
        return null;
    }

    /**
     * This method is used to load all customers from the database
     */
    public void loadCustomers() {
        try {
            if (dbHandler.getConnection() == null) {
                // System.out.println("Connection is null, attempting to
                // connect...[UserController.loadUsers()]");
                // dbHandler.connectToDatabase();
            }
            String query = "SELECT c.Customer_ID, c.Customer_Name, c.Address, c.Postal_Code, c.Phone, c.Create_Date, c.Created_By, c.Last_Update, c.Last_Updated_By, d.Division, co.Country FROM customers c JOIN first_level_divisions d ON c.Division_ID = d.Division_ID JOIN countries co ON d.Country_ID = co.Country_ID;";
            PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("Customer_ID"));
                customer.setCustomerName(resultSet.getString("Customer_Name"));
                customer.setAddress(resultSet.getString("Address"));
                customer.setPostalCode(resultSet.getString("Postal_Code"));
                customer.setPhone(resultSet.getString("Phone"));
                LocalDateTime createDate = resultSet.getTimestamp("Create_Date").toLocalDateTime();
                customer.setCreateDate(createDate);
                customer.setCreatedBy(resultSet.getString("Created_By"));
                LocalDateTime lastUpdate = resultSet.getTimestamp("Last_Update").toLocalDateTime();
                customer.setLastUpdate(lastUpdate);
                customer.setLastUpdatedBy(resultSet.getString("Last_Updated_By"));
                customer.setDivision(resultSet.getString("Division"));
                customer.setCountry(resultSet.getString("Country"));
                customers.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to delete a customer from the database
     * 
     * @param customerId
     * @return 1 if successful, 0 if not
     */
    public static int deleteCustomer(int customerId) {
        try {
            if (dbHandler.getConnection() == null) {
                System.out.println("Connection is null, attempting to connect...[UserController.loadUsers()]");
                dbHandler.connectToDatabase();
            }

            String query2 = "DELETE FROM appointments WHERE Customer_ID = ?;";
            PreparedStatement preparedStatement2 = dbHandler.getConnection().prepareStatement(query2);
            preparedStatement2.setInt(1, customerId);
            preparedStatement2.executeUpdate();
            System.out.println("Customer appointments deleted successfully");
            String query = "DELETE FROM customers WHERE Customer_ID = ?;";
            PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
            System.out.println("Customer deleted successfully");

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Updates the customer details in the database.
     *
     * @param CustomerID   the ID of the customer to update
     * @param customerName the updated name of the customer
     * @param address      the updated address of the customer
     * @param postalCode   the updated postal code of the customer
     * @param phone        the updated phone number of the customer
     * @param division     the updated division of the customer
     * @param Country      the updated country of the customer
     * @return 1 if the customer is updated successfully, 0 otherwise
     */
    public static int updateCustomer(int CustomerID, String customerName, String address, String postalCode,
            String phone,
            String division, String Country) {
        try {

            if (dbHandler.getConnection() == null) {
                System.out.println("Connection is null, attempting to connect...[UserController.loadUsers()]");
                dbHandler.connectToDatabase();
            }
            int countryId = CountriesControler.addCountry(Country, dbHandler);
            int divisonId = FirstLevelDivisionController.addNewDivision(division, dbHandler, countryId);

            String query2 = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?;";
            PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query2);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, postalCode);
            preparedStatement.setString(4, phone);
            preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(6, "admin");
            preparedStatement.setTimestamp(7, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(8, "admin");
            preparedStatement.setInt(9, divisonId);
            preparedStatement.setInt(10, CustomerID);
            preparedStatement.executeUpdate();

            System.out.println("Customer updated successfully");
            return 1;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Adds a new customer to the database.
     *
     * @param customerName the name of the customer.
     * @param address      the address of the customer.
     * @param postalCode   the postal code of the customer.
     * @param phone        the phone number of the customer.
     * @param division     the name of the first-level division where the customer
     *                     is located.
     * @param country      the name of the country where the customer is located.
     * @return 1 if the customer was added successfully, 0 otherwise.
     */
    public static int addCustomer(String customerName, String address, String postalCode, String phone, String division,
            String country) {
        try {
            if (dbHandler.getConnection() == null) {
                System.out.println("Connection is null, attempting to connect...[UserController.loadUsers()]");
                dbHandler.connectToDatabase();
            }
            int countryId = CountriesControler.addCountry(country, dbHandler);
            int divisonId = FirstLevelDivisionController.addNewDivision(division, dbHandler, countryId);

            String query = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, postalCode);
            preparedStatement.setString(4, phone);
            preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(6, "admin");
            preparedStatement.setTimestamp(7, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(8, "admin");
            preparedStatement.setInt(9, divisonId);
            preparedStatement.executeUpdate();

            System.out.println("Customer added successfully");
            return 1;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int isCustomerAvailable(String customerName) {
        try {
            if (dbHandler.getConnection() == null) {
                System.out.println("Connection is null, attempting to connect...[UserController.loadUsers()]");
                dbHandler.connectToDatabase();
            }
            String query = "SELECT * FROM customers WHERE Customer_Name = ?;";
            PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
            preparedStatement.setString(1, customerName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}