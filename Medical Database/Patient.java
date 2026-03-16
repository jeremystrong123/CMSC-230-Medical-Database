import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
    //instance variables
    private PatientIdentity identity;

    //constructors
    /**
     * Constructs a Patient with the given PatientIdentity.
     * 
     * @param id PatientIdentity for the patient to be created.
     */
    public Patient(PatientIdentity id){
        identity = id;
    }

    //methods
    /**
     * Accessor for PatientIdentity.
     * @return the PatientIdentity of the patient.
     */
    public PatientIdentity getIdentity(){
        return identity;
    }

    /**
     * toString for the identity of the patient.
     * @return 'identity: ' followed by the identity of the patient as a string.
     */
    public String toString() {
        return "identity: " + identity.toString();
    }

    /**
     * Turns a patient object into a CSV line, in the order 'last name,first name,dob'
     */
    public String toCSV() {
        PatientIdentity id = this.getIdentity();
        Name nm = id.getName();
        String fn = nm.getFirstName();
        String ln = nm.getLastName();
        Date db = id.getDateOfBirth();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dob = formatter.format(db);
        String[] tokens = {ln, fn, dob};
        String csv = String.join(",", tokens);
        return csv;
    }

    /**
     * Creates a Patient object from a line of a CSV file.
     * 
     * @param data patient data from a line of the CSV file in the format 'last name,first name,dob'
     * 
     * @return Patient object created from the CSV line.
     * 
     * @throws IllegalArgumentException if the input data is null.
     */
    public static Patient createPatientFromCSV(String data) {
        if (data == null) {
            throw new IllegalArgumentException("input data cannot be null");
        }
        String[] tokens = data.split(",");
        try {
            Name nm = new Name(tokens[1], tokens[0]);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = formatter.parse(tokens[2]);
            PatientIdentity id = new PatientIdentity(nm, dob);
            return new Patient(id);
        } catch (java.text.ParseException ex) {}
        return null;
    }
}
