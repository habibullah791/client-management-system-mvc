package Model;

import java.time.LocalDateTime;

/**
 * This class is used to create an appointment object
 */
public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;
    private String customerName;
    private String userName;
    private String contactName;

    /**
     * This method is used to create a new instance of this class
     */
    public Appointment() {
    }

    /**
     * This method is used to create a new instance of this class
     * 
     * @param appointmentId, title, description, location, type, start, end,
     *                       createDate, createdBy, lastUpdate, lastUpdatedBy,
     *                       customerId, userId, contactId, customerName, userName,
     *                       contactName
     */
    public Appointment(int appointmentId, String title, String description, String location, String type,
            LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy,
            LocalDateTime lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId,
            String customerName, String userName, String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.customerName = customerName;
        this.userName = userName;
        this.contactName = contactName;
    }

    /**
     * This method is used get the appointment id
     * 
     * @return appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * This method is used to set the appointment id
     * 
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * This method is used to get the title
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method is used to set the title
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method is used to get the description
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method is used to set the description
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method is used to get the location
     * 
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method is used to set the location
     * 
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method is used to get the type
     * 
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * This method is used to set the type
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method is used to get the start time
     * 
     * @return start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * This method is used to set the start time
     * 
     * @param start
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * This method is used to get the end time
     * 
     * @return end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * This method is used to set the end time
     * 
     * @param end
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
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
     * This method is used to get the customer id
     * 
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * This method is used to set the customer id
     * 
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
     * This method is used to get the contact id
     * 
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * This method is used to set the contact id
     * 
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * This method is used to get the customer name
     * 
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method is used to set the customer name
     * 
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
     * This method is used to get the contact name
     * 
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * This method is used to set the contact name
     * 
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * This method is used display the appointment information
     */
    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId + ", title=" + title + ", description=" + description
                + ", location=" + location + ", type=" + type + ", start=" + start + ", end=" + end + ", createDate="
                + createDate + ", createdBy=" + createdBy + ", lastUpdate=" + lastUpdate + ", lastUpdatedBy="
                + lastUpdatedBy + ", customerId=" + customerId + ", userId=" + userId + ", contactId=" + contactId
                + ", customerName=" + customerName + ", userName=" + userName + ", contactName=" + contactName + '}';
    }

}
