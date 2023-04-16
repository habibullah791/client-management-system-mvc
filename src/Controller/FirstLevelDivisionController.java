package Controller;

import DB.DBHandler;
import Model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
    * This class is used to control the first_level_divisions table in the database
    * It has methods to add, remove, update, and load divisions from the database
    * It also has a method to check if a division is available in the database
    * If it is not available, it will add it to the database
    * It also has a method to add a division to the database
*/ 
public class FirstLevelDivisionController {

    private static DBHandler dbHandler;

    ArrayList<FirstLevelDivision> divisions = new ArrayList<FirstLevelDivision>();


    /**
        * This method is used to connect to the database
        * @return String
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
    public FirstLevelDivisionController() {
        dbHandler = new DBHandler();
        connectToDatabase();
    }

    /**
        * This method is used to add a new division to the database
        * @param division
    */
    public void addNewDivision(FirstLevelDivision division) {
        divisions.add(division);
    }

    /**
        * This method is used to remove a division from the database
        * @param division
    */
    public void removeDivision(FirstLevelDivision division) {
        divisions.remove(division);
    }

    /**
        * This method is used to update a division in the database
        * @param division
    */
    public void updateDivision(FirstLevelDivision division) {
        for (int i = 0; i < divisions.size(); i++) {
            if (divisions.get(i).getDivisionId() == division.getDivisionId()) {
                divisions.set(i, division);
            }
        }
    }

    /**
        * This method is used to get all divisions
    */
    public static ArrayList<FirstLevelDivision> getAllDivisions() {
        FirstLevelDivisionController divisionController = new FirstLevelDivisionController();
        divisionController.loadFirstLevelDivision();
        return divisionController.divisions;
    }

    /**
        * This method is used to get a division by its ID
        * @param divisionId
        * @return FirstLevelDivision
    */
    public FirstLevelDivision getDivisionByID(int divisionId) {
        for (int i = 0; i < divisions.size(); i++) {
            if (divisions.get(i).getDivisionId() == divisionId) {
                return divisions.get(i);
            }
        }
        return null;
    }

    
    /**
        * This method is used to load all divisions from the database
    */
    public void loadFirstLevelDivision() {

        try {
            if (dbHandler.getConnection() == null) {
                System.out.println("Connection is null, attempting to connect...[UserController.loadUsers()]");
                dbHandler.connectToDatabase();
            }
            String query = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = dbHandler.getConnection().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            // loop through the result set
            while (resultSet.next()) {
                FirstLevelDivision division = new FirstLevelDivision();
                division.setDivisionId(resultSet.getInt("Division_ID"));
                division.setDivision(resultSet.getString("Division"));
                division.setCreateDate(resultSet.getTimestamp("Create_Date").toLocalDateTime());
                division.setCreatedBy(resultSet.getString("Created_By"));
                division.setLastUpdate(resultSet.getTimestamp("Last_Update").toLocalDateTime());
                division.setLastUpdatedBy(resultSet.getString("Last_Updated_By"));
                division.setCountryId(resultSet.getInt("COUNTRY_ID"));
                addNewDivision(division);
            }
            dbHandler.closeConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    /**
        * This method is used to delete a division from the database
        * @param division
    */
    public void deleteDivision(FirstLevelDivision division) {
        try {
            if (dbHandler.getConnection() == null) {
                System.out.println("Connection is null, attempting to connect...[UserController.loadUsers()]");
                dbHandler.connectToDatabase();
            }
            String query = "DELETE FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement ps = dbHandler.getConnection().prepareStatement(query);
            ps.setInt(1, division.getDivisionId());
            ps.executeUpdate();
            dbHandler.closeConnection();

            System.out.println("Division deleted successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
        * This method is used to check if a division is available in the database
        * If it is not available, it will add it to the database
        * @param division, dbHandler, countryId
        * @return int
    */
    public static int checkIfDivisionExists(String division, DBHandler dbHandler, int countryId) {

        int divisionID = 0;
        try {
            String query = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
            PreparedStatement ps = dbHandler.getConnection().prepareStatement(query);
            ps.setString(1, division);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                divisionID = resultSet.getInt("Division_ID");
            } else {

                query = "INSERT INTO first_level_divisions (Division, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES (?, ?, ?, ?, ?, ?)";
                ps = dbHandler.getConnection().prepareStatement(query);
                ps.setString(1, division);
                ps.setString(2, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()).toString());
                ps.setString(3, "admin");
                ps.setString(4, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()).toString());
                ps.setString(5, "admin");
                ps.setInt(6, countryId);
                ps.executeUpdate();

                // get the division id
                query = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
                ps = dbHandler.getConnection().prepareStatement(query);
                ps.setString(1, division);

                // print the added division
                resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    divisionID = resultSet.getInt("Division_ID");
                } else {
                    System.out.println("Error: Division ID not found");
                }

            }
            System.out.println("Checking if division55 exists");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return divisionID;
    }

    /**
        * This method is used to add a new division to the database
        * @param divisionName, conn, countryId
        * @return int
    */
    public static int addNewDivision(String divisionName, DBHandler conn, int countryId) {

        int divisionIdToUpdate = checkIfDivisionExists(divisionName, conn, countryId);
        if (divisionIdToUpdate == -1) {
            return -1;
        } else {
            return divisionIdToUpdate;
        }
    }
}
