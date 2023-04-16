package Model;

import java.time.LocalDateTime;

/**
 * This class is used to create a first level division object
 */
public class FirstLevelDivision {
    private int divisionId;
    private String division;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int countryId;

    /**
     * This method is used to create a new instance of this class
     */
    public FirstLevelDivision() {
    }
    
    /**
     * This method is used to create a new instance of this class
     * 
     * @param divisionId, division, createDate, createdBy, lastUpdate, lastUpdatedBy, countryId
     */
    public FirstLevelDivision(int divisionId, String division, LocalDateTime createDate, String createdBy,
            LocalDateTime lastUpdate, String lastUpdatedBy, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }

    /**
     * This method is used to get the division id
     * 
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * This method is used to set the division id
     * 
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * This method is used to get the division
     * 
     * @return division
     */
    public String getDivision() {
        return division;
    }

    /**
     * This method is used to set the division
     * 
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
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
    public int getCountryId() {
        return countryId;
    }

    /**
     * This method is used to set the created by
     * 
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
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
     * This method is used display the division
     */
    @Override
    public String toString() {
        return " " + division + " ";
    }
}
