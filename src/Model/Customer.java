package Model;

import java.time.LocalDateTime;

/**
 * This class is used to create a customer object
 */
public class Customer {

    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private String division;
    private String country;

    /**
     * This method is used to create a new instance of this class
     */
    public Customer() {
    }

    /**
     * This method is used to create a new instance of this class
     * 
     * @param customerId, customerName, address, postalCode, phone, createDate,
     *                    createdBy, lastUpdate, lastUpdatedBy, division, country
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phone,
            LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, String division,
            String country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.division = division;
        this.country = country;
    }

    /**
     * This method is used to get the country id
     * 
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method is used to get the country
     * 
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
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
     * This method is used to get the address
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method is used to set the address
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method is used to get the postal code
     * 
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * This method is used to set the postal code
     * 
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * This method is used to get the phone
     * 
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method is used to set the phone
     * 
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * This method is used to get the division id
     * 
     * @return divisionId
     */
    public String getDivision() {
        return division;
    }

    /**
     * This method is used to set the division id
     * 
     * @param divisionId
     */
    public void setDivision(String divisionId) {
        this.division = divisionId;
    }

    /**
     * This method is used to display the customer information
     */
    @Override
    public String toString() {
        return "Customers [Customer_Id=" + customerId + ", Customer_Name=" + customerName + ", Address=" + address
                + ", Postal_Code=" + postalCode + ", Phone=" + phone + ", Create_Date=" + createDate + ", Created_By="
                + createdBy + ", Last_Update=" + lastUpdate + ", Last_Updated_By=" + lastUpdatedBy + ", Division_ID="
                + division + "]";
    }
}
