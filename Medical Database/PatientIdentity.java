import java.util.Date;

public class PatientIdentity {
    //instance variables
    private Name name;
    private Date dateOfBirth;

    //constructors
    /**
     * Constructs a PatientIdentity out of a given name and date of birth.
     * 
     * @param nm name of the patient.
     * 
     * @param dob date of birth of the patient (month, day, year).
     */
    public PatientIdentity(Name nm, Date dob){
        name = nm;
        dateOfBirth = dob;
    }

    //methods
    /**
     * Checks a given patient identity matches the identity of the patient object.
     * 
     * @param other given patient identity.
     * 
     * @return true if match, false if not.
     */
    public boolean match(PatientIdentity other){
        if (name.match(other.getName()) && (dateOfBirth.compareTo(other.getDateOfBirth()) == 0 )) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks if a patient's identity is before or after another based off of their name, 
     * if the names match it then resorts to date of birth.
     * 
     * @param other patient to compare to.
     * 
     * @return true if the identity is less than and false if not
     */
    public boolean isLessThan(PatientIdentity other){
        if (name.isLessThan(other.getName())) {
            return true;
        }
        else if (name.match(other.getName())) {
            if (dateOfBirth.compareTo(other.getDateOfBirth()) < 0 ) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Accessor method for the name.
     * @return returns the name object associated with the patient.
     */
    Name getName() {
        return name;
    }
    
    /**
     * Accessor method for the date of birth.
     * @return returns the date object associated with the patient.
     */
    Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Prints out the patient identity as a string with both the name and date of birth
     * @return 'name: ' follow by the patients name, then 'dob: ' followed by the patient's date of birth
     */
    public String toString() {
        return "name: " + name.toString() + " dob: " + dateOfBirth.toString();
    }

}
