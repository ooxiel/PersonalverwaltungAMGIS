package Login;
import com.AMGIS.Login.LoginProof;
import org.junit.jupiter.api.Test;

public class LoginTest {

    LoginProof login = new LoginProof();


    @Test
    public void userName(){

        login.username("farnz");


    }

    public void password(){
        login.passwordProof("password");
    }
    public void personalID(){
        login.personalIDProof("farnz","f");
    }
}
