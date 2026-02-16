import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class PatientTest {

    private Name name;
    private Date dob;
    private PatientIdentity id;
    private Patient pt;

    @BeforeEach
    void createPatient() {
        name = new Name("John", "Smith");
        dob = new Date(90, 2, 2);
        id = new PatientIdentity(name, dob);
        pt = new Patient(id);
    }

    @Test
    void getIdentityTest() {
        assertEquals(id, pt.getIdentity());
    }

    @Test
    void toStringTest() {
        assertEquals("identity: name: Smith John dob: Fri Mar 02 00:00:00 GMT 1990", pt.toString());
    }
}
