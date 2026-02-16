public class Name {
    //instance variables
    private String firstName;
    private String lastName;

    //constructors
    /**
     * Constructs a name object out of a given first and last name.
     * 
     * @param first first name of the patient.
     * 
     * @param last last name of the patient.
     */
    public Name(String first, String last){
        firstName=first;
        lastName=last;
    }

    //methods
    /**
     * Accessor method for the last name of the patient.
     * @return returns the last name of the patient as a string.
     */
    String getLastName() {
        return lastName;
    }

    /**
     * Accessor method for the first name of the patient.
     * @return returns the first name of the patient as a string.
     */
    String getFirstName() {
        return firstName;
    }

    /**
     * Accessor method for the full name of the patient.
     * @return returns the last then first name of the patient as a string, with a space in between.
     */
    public String getFullName() {
        return lastName + " " + firstName;
    }

    /**
     * Checks if two names match.
     * 
     * @param other name to compare to.
     * 
     * @return true if the names match and false if they do not.
     */
    public boolean match(Name other){
        if (getFullName().toLowerCase().equals(other.getFullName().toLowerCase())) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks if one name is less than another name based off of the last name,
     * resorts to first names if the last names match.
     * 
     * @param other name to compare to.
     * 
     * @return true if the other name is less than (comes earlier alphabetically) and false if not.
     */
    public boolean isLessThan(Name other) {
        if (lastName.toLowerCase().compareTo(other.lastName.toLowerCase()) < 0) {
            return true;
        }
        else if (lastName.toLowerCase().compareTo(other.lastName.toLowerCase()) == 0) {
            if (firstName.toLowerCase().compareTo(other.firstName.toLowerCase()) < 0) {
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
     * Prints out the name as a string.
     * @return 'name: ' follow by the patient's full name
     */
    public String toString() {
        return "name: " + getFullName().toString();
    }
}
