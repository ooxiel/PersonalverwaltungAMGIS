
import Akteure.Mitarbeiter;
import Login.LoginGenerate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Aufbau Unit-Tests:
 *
 *  1. Input
 *  2. Expected Output
 *  3. Validation
 *
 */


public class LoginGenerateTest {

    LoginGenerate login = new LoginGenerate();
    Mitarbeiter mitarbeiter = new Mitarbeiter("Admin", "Farnz","","Whey","Geschlecht","Straße","12A","12345","Land","Job","21345","Buero","Abteilung","Bereich","Position","Vorgesetzer","Abteilung");

    @Test
    public void usernameMitarbeiter(){

        assertEquals("F_whey",login.generateUsername(mitarbeiter));

    }

    @Test
    public void usernameHR(){

        assertEquals("F_whey_adm",login.generateHRUsername(mitarbeiter));
    }

    @Test
    public void password(){

        assert login.passwordGenerate().length() == 7;

    }

}


