package Model;

/**
 * This class is used to create a contact object
 */
public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    /**
     * This method is used to create a new instance of this class
     */
    public Contacts() {
    }

    /**
     * This method is used to create a new instance of this class
     * 
     * @param contactId, contactName, email
     */
    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
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
     * This method is used to get the email
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method is used to set the email
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method is used to display the contact object
     */
    @Override
    public String toString() {
        return "Contacts{" + "contactId=" + contactId + ", contactName=" + contactName + ", email=" + email + '}';
    }
}
