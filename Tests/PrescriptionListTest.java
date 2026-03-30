import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrescriptionListTest {
    private String nom;
    private Date doi;
    private int dosage;
    private String prescriber;
    private Prescription pr;
    private PrescriptionList pl;

    @BeforeEach
    void createPrescriptionAndList() {
        nom = "bandaid";
        doi = new Date(120, 2, 2);
        dosage = 999;
        prescriber = "Clark";
        pr = new Prescription(nom, doi, dosage, prescriber);
        pl = new PrescriptionList();
    }

    //this test also proves that the comesBefore method for ListRecord also works
    @Test
    void addAndIterationFunctionalityTest() {
        String nom2 = "eyepatch";
        Date doi2 = new Date(120, 2, 2);
        int dosage2 = 999;
        String prescriber2 = "Davis";
        Prescription pr2 = new Prescription(nom2, doi2, dosage2, prescriber2);
        String nom3 = "gauze";
        Date doi3 = new Date(120, 2, 2);
        int dosage3 = 999;
        String prescriber3 = "Johnson";
        Prescription pr3 = new Prescription(nom3, doi3, dosage3, prescriber3);
        pl.add(pr);
        pl.add(pr2);
        pl.add(pr3);
        pl.initIteration();
        assertEquals(pr, pl.next());
        assertEquals(pr3, pl.next());
        assertEquals(null, pl.next());
    }
}
