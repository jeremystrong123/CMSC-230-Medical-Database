import java.text.SimpleDateFormat;
import java.util.Date;

public class Prescription {
    //instance variables
    private String nameOfMedicine;
    private Date dateOfIssue;
    private int dosage;
    private String prescriber;

    //constructors
    /**
    * Constructs a prescription out of the given values for each parameter.
    * 
    * @param nom name of the medicine.
    * 
    * @param doi date of the precription's issue.
    * 
    * @param dose dosage amount of the prescription.
    * 
    * @param doctor last name of the doctor that prescribed the medicine.
    */
    public Prescription(String nom, Date doi, int dose, String doctor) {
        nameOfMedicine = nom;
        dateOfIssue = doi;
        dosage = dose;
        prescriber = doctor;
    }

    //accessor methods
    /**
     * Accessor method for the nameOfMedicine.
     * @return returns the name of the medicine as a string.
     */
    public String getNameOfMedicine() {
        return nameOfMedicine;
    }

    /**
     * Accessor method for the dateOfIssue.
     * @return returns the date of the prescription's issue.
     */
    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    /**
     * Accessor method for the prescriber.
     * @return returns the last name of the prescription's prescriber as a string.
     */
    public String getNameOfPrescriber() {
        return prescriber;
    }

    /**
     * Creates a Prescription object from a line of a CSV file.
     * 
     * @param data prescription data from a line of the CSV file in the format 
     * 'patient_name, patient_dob, medicine_name, date_of_issue, dosage, prescriber'
     * 
     * @return Prescription object created from the CSV line.
     * 
     * @throws IllegalArgumentException if the input data is null.
     */
    public static Prescription createPrescriptionFromCSV(String data) {
        if (data == null) {
            throw new IllegalArgumentException("input data cannot be null");
        }
        String[] tokens = data.split(",");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date doi = formatter.parse(tokens[4]);
            int dose = Integer.parseInt(tokens[5]);
            Prescription pt = new Prescription(tokens[3], doi, dose, tokens[6]);
            return pt;
        } catch (java.text.ParseException ex) {}
        return null;
    }
}
