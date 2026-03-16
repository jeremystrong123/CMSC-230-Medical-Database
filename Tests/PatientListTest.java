import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class PatientListTest {

    private Name name;
    private Date dob;
    private PatientIdentity id;
    private Patient pt;
    private PatientList pl;

    @BeforeEach
    void createListAndPatient() {
        name = new Name("John", "Smith");
        dob = new Date(90, 2, 2);
        id = new PatientIdentity(name, dob);
        pt = new Patient(id);
        pl = new PatientList();
    }

    @Test
    void addPatientTestForTrueAndWorks() {
        assertEquals(true, pl.addPatient(pt));
        assertEquals(pt, pl.getPatientArray()[0]);
    }

    @Test
    void addPatientFalseWhenFull() {
        for (int i = 0; i<1000; i++) {
            pl.addPatient(pt);
        }
        assertEquals(false, pl.addPatient(pt));
    }

    @Test
    void addPutsLesserPatientBefore() {
        pl.addPatient(pt);
        Name name2 = new Name("A", "A");
        PatientIdentity id2 = new PatientIdentity(name2, dob);
        Patient pt2 = new Patient(id2);
        pl.addPatient(pt2);
        assertEquals(pt2, pl.getPatientArray()[0]);
    }

    @Test
    void addPutsGreaterPatientAfter() {
        pl.addPatient(pt);
        Name name2 = new Name("Z", "Z");
        PatientIdentity id2 = new PatientIdentity(name2, dob);
        Patient pt2 = new Patient(id2);
        pl.addPatient(pt2);
        assertEquals(pt2, pl.getPatientArray()[1]);
    }

    @Test
    void findPatientWorks() {
        pl.addPatient(pt);
        Name name2 = new Name("Y", "Y");
        PatientIdentity id2 = new PatientIdentity(name2, dob);
        Patient pt2 = new Patient(id2);
        pl.addPatient(pt2);
        Name name3 = new Name("Z", "Z");
        PatientIdentity id3 = new PatientIdentity(name3, dob);
        Patient pt3 = new Patient(id3);
        pl.addPatient(pt3);
        assertEquals(pt, pl.findPatient(id));
        assertEquals(pt2, pl.findPatient(id2));
        assertEquals(pt3, pl.findPatient(id3));
    }

    @Test
    void findPatientReturnsNullForPatientThatDoesNotExist() {
        pl.addPatient(pt);
        Name name2 = new Name("Y", "Y");
        PatientIdentity id2 = new PatientIdentity(name2, dob);
        Patient pt2 = new Patient(id2);
        pl.addPatient(pt2);
        Name name3 = new Name("Z", "Z");
        PatientIdentity id3 = new PatientIdentity(name3, dob);
        Patient pt3 = new Patient(id3);
        assertEquals(null, pl.findPatient(id3));
    }

    @Test
    void initIterationDoesNotInitializeEmptyArray() {
        pl.initIteration();
        assertEquals(-1, pl.getIndexOfIteration());
    }

    @Test
    void initIterationWorks() {
        pl.addPatient(pt);
        pl.initIteration();
        assertEquals(0, pl.getIndexOfIteration());
    }

    @Test
    void iteratorReturnsNullIfNotInitialized() {
        assertEquals(null, pl.next());
    }

    @Test
    void iteratorReturnsProperPatientAndIteratesThroughArray() {
        pl.addPatient(pt);
        Name name2 = new Name("A", "A");
        PatientIdentity id2 = new PatientIdentity(name2, dob);
        Patient pt2 = new Patient(id2);
        pl.addPatient(pt2);
        Name name3 = new Name("Z", "Z");
        PatientIdentity id3 = new PatientIdentity(name3, dob);
        Patient pt3 = new Patient(id3);
        pl.addPatient(pt3);
        pl.initIteration();
        assertEquals(pt2, pl.next());
        assertEquals(pt, pl.next());
        assertEquals(pt3, pl.next());
        assertEquals(-1, pl.getIndexOfIteration());
    }

    @Test
    void importFromFileTest() {
        PatientList pl2 = new PatientList();
        pl2.importFromFile("data/patients1000.csv");
        assertEquals(1000, pl2.getNextAvailableIndex());
        Name nm1 = new Name("Walter", "Gomez");
        Date dob1 = new Date(106, 3, 5);
        PatientIdentity id1 = new PatientIdentity(nm1, dob1);
        Patient pt1 = new Patient(id1);
        Name nm2 = new Name("Kathleen", "Smith");
        Date dob2 = new Date(27, 7, 6);
        PatientIdentity id2 = new PatientIdentity(nm2, dob2);
        Patient pt2 = new Patient(id2);
        Name nm3 = new Name("Barbara", "Alvarez");
        Date dob3 = new Date(86, 7, 29);
        PatientIdentity id3 = new PatientIdentity(nm3, dob3);
        Patient pt3 = new Patient(id3);
        assertEquals(pt1.toString(), pl2.findPatient(id1).toString());
        assertEquals(pt2.toString(), pl2.findPatient(id2).toString());
        assertEquals(pt3.toString(), pl2.findPatient(id3).toString());
    }

    @Test
    void importFromFileFailsWithoutFile() {
        assertEquals(false, pl.importFromFile("ahsjukvdbkjahwebfr"));
    }

    @Test
    void saveToFileTest() {
        PatientList pl2 = new PatientList();
        Patient pt1 = Patient.createPatientFromCSV("Smith,John,1990-04-17");
        Patient pt2 = Patient.createPatientFromCSV("Smith,Robert,1991-07-21");
        Patient pt3 = Patient.createPatientFromCSV("Smith,Jones,1985-01-30");
        Patient pt4 = Patient.createPatientFromCSV("Smith,Noah,1972-08-3");
        Patient pt5 = Patient.createPatientFromCSV("Smith,Henry,2003-11-7");
        pl2.addPatient(pt1);
        pl2.addPatient(pt2);
        pl2.addPatient(pt3);
        pl2.addPatient(pt4);
        pl2.addPatient(pt5);
        pl2.saveToFile("data/writertest.csv");
        PatientList pl3 = new PatientList();
        pl3.importFromFile("data/writertest.csv");
        assertEquals(pt1.toString(), pl3.findPatient(pt1.getIdentity()).toString());
        assertEquals(pt2.toString(), pl3.findPatient(pt2.getIdentity()).toString());
        assertEquals(pt3.toString(), pl3.findPatient(pt3.getIdentity()).toString());
        assertEquals(pt4.toString(), pl3.findPatient(pt4.getIdentity()).toString());
        assertEquals(pt5.toString(), pl3.findPatient(pt5.getIdentity()).toString());
    }

    @Test
    void saveToFileFailsWithImproperFile() {
        assertEquals(false, pl.saveToFile("file/file.file"));
    }
}
