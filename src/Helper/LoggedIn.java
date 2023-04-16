package Helper;
import Model.User;


/**
    * This class is used to store the logged in user
    * It has a method to get the user
    * It also has a method to set the user
*/ 
public class LoggedIn {
    public static User user = null;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LoggedIn.user = user;
    }
}
