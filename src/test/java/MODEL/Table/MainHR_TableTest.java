package MODEL.Table;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class MainHR_TableTest {
    MainHR_Table mhrt = new MainHR_Table();
    String sql= "SELECT  ms.person_id, ms.anrede, ms.vorname, ms.zweitname,ms.nachname,ji.jobname,  ji.abteilung,ji.standort FROM mitarbeiterstamm ms, adressinfo ai, jobinfo ji  WHERE  ms.anrede LIKE '% %' AND ms.vorname LIKE '% %' AND ms.nachname LIKE '% %' AND ji.jobname LIKE '% %' AND ji.abteilung LIKE'% %' AND ji.standort LIKE '% %' AND ms.person_id=ai.Adress_ID AND person_id=ji.job_ID";

    @Test
    void test_resultSQL_PA(){
        assertNotNull(mhrt.resultSQL_PA(sql));
    }
    @Test
    void test_filterTable() {
        assertNotNull(mhrt.filterTable(new JTable(),"","","","","",""));
    }

    @Test
    void test_defaultTableAccounts() {
        assertNotNull(mhrt.defaultTableAccounts(new JTable()));
    }

    @Test
    void test_defaultTableMlogin() {
        assertNotNull(mhrt.defaultTableMlogin(new JTable()));
    }

    @Test
    void test_getCon() {
        assertAll(
                () -> assertEquals(null,mhrt.con),
                () -> assertFalse(mhrt.getCon().isClosed())
        );
    }
}