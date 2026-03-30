import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrescriptionTest {
    
    private String nom;
    private Date doi;
    private int dosage;
    private String prescriber;
    private Prescription pr;

    @BeforeEach
    void createPrescription() {
        nom = "bandaid";
        doi = new Date(120, 2, 2);
        dosage = 999;
        prescriber = "Clark";
        pr = new Prescription(nom, doi, dosage, prescriber);
    }

    @Test
    void getNameOfMedicineTest() {
        assertEquals("bandaid", pr.getNameOfMedicine());
    }

    @Test
    void getDateOfIssueTest() {
        assertEquals(doi, pr.getDateOfIssue());
    }

    @Test
    void getNameOfPrescriberTest() {
        assertEquals("Clark", pr.getNameOfPrescriber());
    }

    @Test
    void createFromCSVTest() {
        Prescription pr1 = Prescription.createPrescriptionFromCSV("Smith,John,1990-03-02,bandaid,2020-03-02,999,Clark");
        assertEquals("bandaid", pr1.getNameOfMedicine());
        assertEquals(doi, pr1.getDateOfIssue());
        assertEquals("Clark", pr1.getNameOfPrescriber());
    }
}
