
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


public class LoginTest {

    @Test
    public void test(){

        LoginGenerate login = new LoginGenerate();

        assertEquals("4",login.passwordGenerate());

    }

    @Test
    public void userTest(){
        LoginGenerate login = new LoginGenerate();

        assertNull(login.usernameGenerate());
    }

}


