import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PatientList {
    //instance variables
    private Patient[] patientArray;
    private int nextAvailableIndex;
    private int indexOfIteration;

    //constructors
    /**
     * Constructs a PatientList object that has a patientArray with 1000 maximum patients
     * Sets the nextAvailableIndex to 0 as the list should be empty
     * Sets the indexOfIteration to -1 as the empty list cannot be iterated through
     */
    public PatientList() {
        patientArray = new Patient[1000];
        nextAvailableIndex = 0;
        indexOfIteration = -1;
    }

    //methods
    /**
     * Add method to add a Patient to the PatientList in the correct order based off of their PatientIdentity.
     * 
     * @param p Patient to be added to the PatientList.
     * 
     * @return true if sucessful false if not
     */
    public boolean addPatient(Patient p) {
        int currentIndex = nextAvailableIndex - 1;
        if (nextAvailableIndex == 0) {
            patientArray[0] = p;
            nextAvailableIndex++;
            return true;
        }
        else if (nextAvailableIndex < patientArray.length) {
            while ((currentIndex >= 0) && p.getIdentity().isLessThan(patientArray[currentIndex].getIdentity())) {
                patientArray[currentIndex + 1] = patientArray[currentIndex];
                patientArray[currentIndex] = null;
                currentIndex--;
            }
            patientArray[currentIndex + 1] = p;
            nextAvailableIndex++;
            return true;
        }
        return false;
    }

    /**
     * Locates a patient inside of the PatientList using their identity through a binary search.
     * 
     * @param id PatientIdentity of the patient you want to find
     * 
     * @return Returns the Patient object with the given patient identity if found and returns null if not found.
     */
    public Patient findPatient(PatientIdentity id) {
        int min = 0;
        int max = nextAvailableIndex;
        while (min < max) {
            int mid = (max + min) / 2;
            if (patientArray[mid].getIdentity().match(id)) {
                return patientArray[mid];
            }
            else if (patientArray[mid].getIdentity().isLessThan(id)){
                min = mid + 1;
            }
            else {
                max = mid;
            }
        }
        return null;
    }

    /**
     * Initializes iteration from the start of the array, setting the indexOfIteration to 0
     * if the patientList's array contains any Patient objects, and keeping it at -1 if the
     * array is empty.
     */
    public void initIteration() {
        if (patientArray[0] != null) {
            indexOfIteration = 0;
        }
        else {
            indexOfIteration = -1;
        }
    }

    /**
     * Finds the current patient in the array and then iterates the index onto the next patient in the array
     * resets the index if there are no more patients in the array
     * 
     * @return returns the patient at the current index of iteration, or null if there are no patients in the array
     * or if the index has not been initialized
     */
    public Patient next() {
        Patient current;
        if (indexOfIteration == -1) {
            return null;
        }
        else {
            current = patientArray[indexOfIteration];
        }
        indexOfIteration++;
        if (patientArray[indexOfIteration] == null) {
            indexOfIteration = -1;
        }
        return current;
    }

    /**
     * Get method for the patient list's array
     * @return returns the patientArray
     */
    public Patient[] getPatientArray() {
        return patientArray;
    }

    /**
     * Get method for the indexOfIteration
     * @return returns the indexOfIteration's integer value
     */
    public int getIndexOfIteration() {
        return indexOfIteration;
    }

    /**
     * Saves a list of patients from a PatientList into a CSV file format.
     * 
     * @param filename file path for the CSV file that the strings will be written into.
     * 
     * @return true if successful, false if not.
     */
    public boolean saveToFile(String filename) {
        boolean result = true;
        File patientsFile = new File(filename);
        FileWriter writer;
        try {
            writer = new FileWriter(patientsFile);
            this.initIteration();
            while (indexOfIteration != -1) {
                String csvLine = this.next().toCSV();
                writer.write(csvLine + System.lineSeparator());
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * Takes a CSV file with patients and creates a PatientList.
     * 
     * @param filename file path of the CSV file that will be loaded into the system as a PatientList.
     * 
     * @return true if successful, false if failed.
     */
    public boolean importFromFile(String filename) {
        boolean result = true;
        File patientsFile = new File(filename);
        Scanner scan;
        try {
            scan = new Scanner(patientsFile);
            while (scan.hasNextLine()) {
                String current = scan.nextLine();
                Patient p = Patient.createPatientFromCSV(current);
                addPatient(p);
            }
            scan.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * Get method for the nextAvailableIndex.
     * @return returns the nextAvailableIndex's integer value.
     */
    public int getNextAvailableIndex() {
        return nextAvailableIndex;
    }
}
