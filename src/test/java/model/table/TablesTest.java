package model.table;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class TablesTest {
    //Objekt der Klasse die wir testen, generieren um damit die Methoden aufzurufen
    Tables mhrt = new Tables();

    //SQL Statement fuer eine zu testende Methode
    String sql= "SELECT  ms.person_id, ms.anrede, ms.vorname, ms.zweitname,ms.nachname,ji.jobname,  ji.abteilung,ji.standort FROM mitarbeiterstamm ms, adressinfo ai, jobinfo ji  WHERE  ms.anrede LIKE '% %' AND ms.vorname LIKE '% %' AND ms.nachname LIKE '% %' AND ji.jobname LIKE '% %' AND ji.abteilung LIKE'% %' AND ji.standort LIKE '% %' AND ms.person_id=ai.Adress_ID AND person_id=ji.job_ID";

    @Test
        // Testen ob die Zurueckgegebene AbstractTableModel nicht null ist.
    void test_resultSQL_PA(){
        assertNotNull(mhrt.resultSQL_PA(sql));
    }
    @Test
        // Testen ob die Zurueckgegebene JTable nicht null ist.
    void test_filterTable() {
        assertNotNull(mhrt.filterTable(new JTable(),"","","","","",""));
    }

    @Test
        // Testen ob die Zurueckgegebene JTable nicht null ist.
    void test_defaultTableAccounts() {
        assertNotNull(mhrt.defaultTableAccounts(new JTable()));
    }

    @Test
        // Testen ob die Zurueckgegebene JTable nicht null ist.
    void test_defaultTableMlogin() {
        assertNotNull(mhrt.defaultTableMlogin(new JTable()));
    }

    @Test
    //1. Checken ob die Connection am Anfang null ist.
    //2. Checken ob die Connection nach Verbindungsaufbau nicht mehr null ist.
    void test_getCon() {
        assertAll(
                () -> assertEquals(null,mhrt.con),
                () -> assertNotEquals(null,mhrt.getCon())
        );
    }
}