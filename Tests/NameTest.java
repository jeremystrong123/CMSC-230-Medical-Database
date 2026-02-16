import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NameTest {

    private Name name;

    @BeforeEach
    void constructName() {
        name = new Name("John", "Smith");
    }

    @Test
    void getLastNameTest() {
        assertEquals("Smith", name.getLastName());
    }

    @Test
    void getFirstNameTest() {
        assertEquals("John", name.getFirstName());
    }

    @Test
    void getFullNameTest() {
        assertEquals("Smith John", name.getFullName());
    }

    @Test
    void matchReturnsTrueForMatchingNames() {
        Name name2 = new Name("John", "Smith");
        assertEquals(true, name.match(name2));
    }

    @Test
    void matchReturnsFalseForDifferentNames() {
        Name name2 = new Name("John", "Pork");
        assertEquals(false, name.match(name2));
    }

    @Test
    void isLessThanReturnsTrueForLesserLastName() {
        Name name2 = new Name("John", "Zorg");
        assertEquals(true, name.isLessThan(name2));
    }

    @Test
    void isLessThanReturnsTrueForLesserFirstNameAndMatchingLastName() {
        Name name2 = new Name("Zorg", "Smith");
        assertEquals(true, name.isLessThan(name2));
    }

    @Test
    void isLessThanReturnsFalseForGreaterLastName() {
        Name name2 = new Name("John", "Amber");
        assertEquals(false, name.isLessThan(name2));
    }

    @Test
    void isLessThanReturnsFalseForGreaterFirstNameAndMatchingLastName() {
        Name name2 = new Name("Amber", "Smith");
        assertEquals(false, name.isLessThan(name2));
    }

    @Test
    void toStringTest() {
        assertEquals("name: Smith John", name.toString());
    }
}
