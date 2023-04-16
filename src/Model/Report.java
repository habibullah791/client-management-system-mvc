package Model;

/**
 * This class is used to create a report object
 */
public class Report {
    private String ReportType;
    private int Month;
    private int NumberOfAppointment;

    /**
     * This method is used to create a new instance of this class
     */
    public Report() {
    }

    /**
     * This method is used to create a new instance of this class
     * 
     * @param ReportType, Month, NumberOfAppointment
     */
    public Report(String ReportType, int Month, int NumberOfAppointment) {
        this.ReportType = ReportType;
        this.Month = Month;
        this.NumberOfAppointment = NumberOfAppointment;
    }

    /**
     * This method is used to get the report type
     * 
     * @return ReportType
     */
    public String getReportType() {
        return ReportType;
    }

    /**
     * This method is used to set the report type
     * 
     * @param ReportType
     */
    public void setReportType(String ReportType) {
        this.ReportType = ReportType;
    }

    /**
     * This method is used to get the month
     * 
     * @return Month
     */
    public int getMonth() {
        return Month;
    }

    /**
     * This method is used to set the month
     * 
     * @param Month
     */
    public void setMonth(int Month) {
        this.Month = Month;
    }

    /**
     * This method is used to get the number of appointments
     * 
     * @return NumberOfAppointment
     */
    public int getNumberOfAppointment() {
        return NumberOfAppointment;
    }

    /**
     * This method is used to set the number of appointments
     * 
     * @param NumberOfAppointment
     */
    public void setNumberOfAppointment(int NumberOfAppointment) {
        this.NumberOfAppointment = NumberOfAppointment;
    }


    /**
     * This method is used to get the report type, month, and number of appointments
     * 
     * @return ReportType, Month, NumberOfAppointment
     */
    @Override
    public String toString() {
        return "Report{" + "ReportType=" + ReportType + ", Month=" + Month + ", NumberOfAppointment="
                + NumberOfAppointment + '}';
    }
}
