package Model;

import java.time.LocalDateTime;


/**
 * This class is used to create a user object
 */
public class User {
    private int userId;
    private String userName;
    private String password;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    
    /**
     * This method is used to create a new instance of this class
     */
    public User() {
    }
    
    /**
     * This method is used to create a new instance of this class
     * 
     * @param userId, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy
     */
    public User(int userId, String userName, String password, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This method is used to get the user id
     * 
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * This method is used to set the user id
     * 
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * This method is used to get the user name
     * 
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method is used to set the user name
     * 
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method is used to get the password
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method is used to set the password
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method is used to get the create date
     * 
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * This method is used to set the create date
     * 
     * @param createDate
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * This method is used to get the created by
     * 
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method is used to set the created by
     * 
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method is used to get the last update
     * 
     * @return lastUpdate
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This method is used to set the last update
     * 
     * @param lastUpdate
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * This method is used to get the last updated by
     * 
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method is used to set the last updated by
     * 
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This method is used to get the user object as a string
     * 
     * @return user object as a string
     */
    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", password=" + password + ", createDate=" + createDate + ", createdBy=" + createdBy + ", lastUpdate=" + lastUpdate + ", lastUpdatedBy=" + lastUpdatedBy + '}';
    }
}
