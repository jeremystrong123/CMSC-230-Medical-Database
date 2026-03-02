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
}
