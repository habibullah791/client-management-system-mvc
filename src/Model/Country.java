package Model;

import java.time.LocalDateTime;

/**
 * This class is used to create a country object
 */
public class Country {

    private int countryId;
    private String country;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;

    /**
     * This method is used to create a new instance of this class
     */
    public Country() {
    }

    /**
     * This method is used to create a new instance of this class
     * 
     * @param countryId, country, createDate, createdBy, lastUpdate, lastUpdatedBy
     */
    public Country(int countryId, String country, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate,
            String lastUpdatedBy) {
        this.countryId = countryId;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This method is used to set the country id
     * 
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * This method is used to set the country
     * 
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
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
     * This method is used to set the created by
     * 
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
     * This method is used to set the last updated by
     * 
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This method is used to get the country id
     * 
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * This method is used to get the country
     * 
     * @return country
     */
    public String getCountry() {
        return country;
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
     * This method is used to get the created by
     * 
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
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
     * This method is used to get the last updated by
     * 
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method is used to display the country object
     */
    @Override
    public String toString() {
        return "Countries{" + "countryId=" + countryId + ", country=" + country + ", createDate=" + createDate
                + ", createdBy=" + createdBy + ", lastUpdate=" + lastUpdate + ", lastUpdatedBy=" + lastUpdatedBy + '}';
    }

}
