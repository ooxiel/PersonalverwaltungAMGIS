package MODEL.Login.Check;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginCheckTest {
    LoginCheck lc = new LoginCheck();
    @Test
    void test_validatePasswort_M() {
        assertAll(
                ()-> assertTrue(lc.validatePasswort_M("amgis","amgis")),
                ()-> assertFalse(lc.validatePasswort_M("amgis","ich bin falsch")),
                ()-> assertFalse(lc.validatePasswort_M("ich bin falsch","amgis")),
                ()-> assertFalse(lc.validatePasswort_M("ich bin falsch","ich bin auch falsch"))
        );
    }

    @Test
    void test_validatePasswort_HR() {
        assertAll(
                ()-> assertTrue(lc.validatePasswort_HR("root","root")),
                ()-> assertFalse(lc.validatePasswort_HR("root","ich bin falsch")),
                ()-> assertFalse(lc.validatePasswort_HR("ich bin falsch","root")),
                ()-> assertFalse(lc.validatePasswort_HR("ich bin falsch","ich bin auch falsch"))

        );
    }

    @Test
    void test_isRoot() {
        assertAll(
                () -> assertTrue(lc.isRoot(0)),
                () -> assertFalse(lc.isRoot(-1)),
                () -> assertFalse(lc.isRoot(1)),
                () -> assertFalse(lc.isRoot(-2))
        );
    }
}