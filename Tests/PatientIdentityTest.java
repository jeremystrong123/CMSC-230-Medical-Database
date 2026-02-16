import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class PatientIdentityTest {

    private Name name;
    private Date dob;
    private PatientIdentity id;

    @BeforeEach
    void createIdentity() {
        name = new Name("John", "Smith");
        dob = new Date(90, 2, 2);
        id = new PatientIdentity(name, dob);
    }

    @Test
    void matchReturnsTrueForMatchingIdentities() {
        PatientIdentity id2 = new PatientIdentity(name, dob);
        assertEquals(true, id.match(id2));
    }

    @Test
    void matchReturnsFalseForNonMatchingIdentities() {
        Name name2 = new Name("John", "Pork");
        PatientIdentity id2 = new PatientIdentity(name2, dob);
        assertEquals(false, id.match(id2));
        Date dob2 = new Date(91, 2, 2);
        PatientIdentity id3 = new PatientIdentity(name, dob2);
        assertEquals(false, id.match(id3));
    }

    @Test
    void isLessThanReturnsTrueForLesserName() {
        Name name2 = new Name("Zorg", "Smith");
        PatientIdentity id2 = new PatientIdentity(name2, dob);
        assertEquals(true, id.isLessThan(id2));
    }

    @Test
    void isLessThanReturnsTrueForLesserDateOfBirthAndMatchingName() {
        Date dob2 = new Date(91, 2, 2);
        PatientIdentity id2 = new PatientIdentity(name, dob2);
        assertEquals(true, id.isLessThan(id2));
    }

    @Test
    void isLessThanReturnsFalseForGreaterDateOfBirthAndMatchingName() {
        Date dob2 = new Date(89, 2, 2);
        PatientIdentity id2 = new PatientIdentity(name, dob2);
        assertEquals(false, id.isLessThan(id2));
    }

    @Test
    void isLessThanReturnsFalseForGreaterName() {
        Name name2 = new Name("Amber", "Smith");
        PatientIdentity id2 = new PatientIdentity(name2, dob);
        assertEquals(false, id.isLessThan(id2));
    }

    @Test
    void getNameTest() {
        assertEquals(name, id.getName());
    }

    @Test
    void getDateOfBirthTest() {
        assertEquals(dob, id.getDateOfBirth());
    }

    @Test
    void toStringTest() {
        assertEquals("name: Smith John dob: Fri Mar 02 00:00:00 GMT 1990", id.toString());
    }
}
