package MODEL.Table;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainHR_TableTest {
    MainHR_Table mhrt = new MainHR_Table();
    @Test
    void test_filterTable() {
    }

    @Test
    void test_defaultTableAccounts() {
    }

    @Test
    void test_defaultTableMlogin() {
    }

    @Test
    void test_getCon() {
        assertAll(
                () -> assertEquals(null,mhrt.con),
                () -> assertFalse(mhrt.getCon().isClosed())
        );
    }
}