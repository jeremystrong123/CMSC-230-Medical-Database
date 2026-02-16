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
}
