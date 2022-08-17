package model.login.create;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountErzeugenTest {
    //Objekt der zu testenden Klasse erzeugen
    AccountErzeugen ae = new AccountErzeugen();


    //Pruefen ob die erwarteten Kontonamen mit dem Output der Methode uebereinstimmen
    @Test
    void test_kontoname_erzeugen() {
        assertAll(
                () -> assertEquals("t_test10",ae.kontoname_erzeugen(1,"Test","Test")),
                () -> assertEquals("m_achim4320",ae.kontoname_erzeugen(432,"Achim","Meier"))
        );
    }

    @Test
    void test_hr_kontoname_erzeugen() {
        assertAll(
                () -> assertEquals("HR_T_TEST1",ae.hr_kontoname_erzeugen(1,"Test","Test")),
                () -> assertEquals("HR_M_ACHIM432",ae.hr_kontoname_erzeugen(432,"Achim","Meier"))
        );
    }

    @Test
    //erzeugte Passwort sollte nicht null sein
    void test_passwort_erzeugen() {
        assertAll(
                () -> assertNotNull(ae.passwort_erzeugen()),
                () -> assertNotNull(ae.passwort_erzeugen())
        );
    }
}