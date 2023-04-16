package Controller;

import DB.DBHandler;
import Model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is used to control the countries table in the database
 * It has methods to add, remove, update, and load countries from the database
 * It also has a method to check if a country is available in the database
 * If it is not available, it will add it to the database
 * It also has a method to add a country to the database
 */
public class CountriesControler {

    private static DBHandler dbHandler;
    ArrayList<Country> countries = new ArrayList<Country>();

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
    public CountriesControler() {
        dbHandler = new DBHandler();
        connectToDatabase();
    }

    /**
     * This method is used to add a new country to the database
     * It takes a country object as a parameter
     */
    public void addNewCountry(Country country) {
        countries.add(country);
    }

    /**
     * This method is used to remove a country from the database
     * It takes a country object as a parameter
     */
    public void removeCountry(Country country) {
        countries.remove(country);
    }

    /**
     * This method is used to update a country in the database
     * It takes a country object as a parameter
     */
    public void updateCountry(Country country) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getCountryId() == country.getCountryId()) {
                countries.set(i, country);
            }
        }
    }

    /**
     * This method is used to get all the countries from the database
     * It returns an arraylist of country objects
     */
    public static ArrayList<Country> getAllCountries() {
        CountriesControler countriesControler = new CountriesControler();
        countriesControler.loadCountries();
        return countriesControler.countries;
    }

    /**
     * Returns the country object associated with the specified country ID.
     *
     * This function searches the list of countries and returns the country object
     * with the specified ID. If no country object is found with the specified ID,
     * this function returns null.
     *
     * @param countryId the ID of the country to retrieve
     * @return the country object with the specified ID, or null if no such country
     *         is found
     */
    public Country getCountryByID(int countryId) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getCountryId() == countryId) {
                return countries.get(i);
            }
        }
        return null;
    }

    /**
     * This method is used to load all the countries from the database
     */
    public void loadCountries() {
        try {
            if (dbHandler.getConnection() == null) {
                System.out.println("Connection is null");
                return;
            } else {
                String sql = "SELECT * FROM countries";
                PreparedStatement statement = dbHandler.getConnection().prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Country country = new Country();
                    country.setCountryId(resultSet.getInt("Country_ID"));
                    country.setCountry(resultSet.getString("Country"));
                    country.setCreateDate(resultSet.getTimestamp("Create_Date").toLocalDateTime());
                    country.setCreatedBy(resultSet.getString("Created_By"));
                    country.setLastUpdate(resultSet.getTimestamp("Last_Update").toLocalDateTime());
                    country.setLastUpdatedBy(resultSet.getString("Last_Updated_By"));
                    countries.add(country);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * Checks if the specified country is available in the database. If the country
     * is found in the "countries" table, the method returns the corresponding
     * Country_ID. If not found, it inserts the new country into the table and
     * returns the newly generated Country_ID. Returns -1 if an error occurs.
     * 
     * @param country   the name of the country to search for.
     * @param dbHandler the instance of DBHandler that is used to connect to the
     *                  database.
     * @return the Country_ID of the specified country if found or newly generated
     *         if inserted; -1 if an error occurs.
     */
    public static int isCountryAvailable(String country, DBHandler dbHandler) {
        try {

            String sql = "SELECT * FROM countries WHERE Country = ?";
            PreparedStatement statement = dbHandler.getConnection().prepareStatement(sql);
            statement.setString(1, country);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("Country_ID");
            } else {

                String sql2 = "NISERT INTO countries (Country, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement2 = dbHandler.getConnection().prepareStatement(sql2);
                statement2.setString(1, country);
                statement2.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
                statement2.setString(3, "admin");
                statement2.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
                statement2.setString(5, "admin");
                statement2.executeUpdate();

                String sql3 = "SELECT * FROM countries WHERE Country = ?";
                PreparedStatement statement3 = dbHandler.getConnection().prepareStatement(sql3);
                statement3.setString(1, country);
                ResultSet resultSet3 = statement3.executeQuery();

                if (resultSet3.next()) {
                    return resultSet3.getInt("Country_ID");
                } else {
                    return -1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Adds a country to the database if it doesn't already exist.
     *
     * @param country   the name of the country to add
     * @param dbHandler the database handler object to use for the operation
     * @return the ID of the country if it already exists in the database, or -1 if
     *         the country could not be added
     */
    public static int addCountry(String country, DBHandler dbHandler) {
        int countryId = isCountryAvailable(country, dbHandler);

        if (countryId == -1) {
            return -1;
        } else {
            return countryId;
        }
    }
}