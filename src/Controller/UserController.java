package Controller;

import DB.DBHandler;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
    * This class is used to control the users table in the database
    * It has methods to add, remove, update, and load users from the database
    * It also has a method to check if a user is available in the database
    * If it is not available, it will add it to the database
    * It also has a method to add a user to the database
*/
public class UserController {
    private DBHandler dbHandler;

    ArrayList<User> users = new ArrayList<User>();

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
    public UserController() {
        dbHandler = new DBHandler();
        connectToDatabase();
    }

    /**
        * This method is used to add a new user to the database
        * @param user
    */
    public void addNewUser(User user) {
        users.add(user);
    }

    /**
        * This method is used to remove a user from the database
        * @param user
    */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
        * This method is used to update a user in the database
        * @param user
    */
    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == user.getUserId()) {
                users.set(i, user);
            }
        }
    }

    /**
        * This method is used to check if a user is available in the database
        * If it is not available, it will add it to the database
        * @return users
    */
    public ArrayList<User> getAllUsers() {
        return users;
    }

    
    /**
     * This method is used to get a user by ID
     * @param userId
     * @return User
    */
    public User getUserByID(int userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == userId) {
                return users.get(i);
            }
        }
        return null;
    }

    /**
     * This method is used to get a user by name
     * @param userName
     * @return User
    */
    public User getUserByName(String userName) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(userName)) {
                return users.get(i);
            }
        }
        return null;
    }

    
    /**
     * This method is used to load all users from the database
    */
    public void loadUsers() {
        try {
            if (dbHandler.getConnection() == null) {
                System.out.println("Connection is null, attempting to connect...[UserController.loadUsers()]");
                dbHandler.connectToDatabase();
            }
            PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("User_ID"));
                user.setUserName(resultSet.getString("User_Name"));
                user.setPassword(resultSet.getString("Password"));
                user.setCreateDate(resultSet.getTimestamp("Create_Date").toLocalDateTime());
                user.setCreatedBy(resultSet.getString("Created_By"));
                user.setLastUpdate(resultSet.getTimestamp("Last_Update").toLocalDateTime());
                user.setLastUpdatedBy(resultSet.getString("Last_Updated_By"));
                users.add(user);
            }

        } catch (Exception e) {
            System.out.println("Error loading users from database...[UserController.loadUsers()]");
            e.printStackTrace();
        }   
        finally {
            dbHandler.closeConnection();
        }
    }

    
    /**
     * This method is used to  create a new user in the database
     * @param user
    */
    public void createNewUser(User user){
        try {
            if (dbHandler.getConnection() == null) {
                System.out.println("Connection is null, attempting to connect...[UserController.createNewUser()]");
                dbHandler.connectToDatabase();
            }
            PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement("INSERT INTO users (User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(user.getCreateDate()));
            preparedStatement.setString(4, user.getCreatedBy());
            preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(user.getLastUpdate()));
            preparedStatement.setString(6, user.getLastUpdatedBy());
            preparedStatement.executeUpdate();

            System.out.println("User added to database");
        } catch (Exception e) {
            System.out.println("Error loading users from database...[UserController.createNewUser()]");
            e.printStackTrace();
        }
        finally {
            dbHandler.closeConnection();
        }
    }

    /**
     * This method is used to authenticate a user
     * @param userName, password
     * @return User
    */
    public static User authenticateUser(String userName, String password) {
        try {
            DBHandler dbHandler = new DBHandler();
            dbHandler.connectToDatabase();
            PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement("SELECT * FROM users WHERE User_Name = ? AND Password = ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("User_ID"));
                user.setUserName(resultSet.getString("User_Name"));
                user.setPassword(resultSet.getString("Password"));
                return user;
            }
        } catch (Exception e) {
            System.out.println("Error authenticating user...[UserController.authenticateUser()]");
        }

        return null;
    }

}
