import Login.Login;
import org.junit.jupiter.api.Test;

public class LoginTest {

    Login login = new Login();


    @Test
    public void userName(){

        login.userNameProof("farnz");


    }

    public void password(){
        login.passwordProof("password");
    }
    public void personalID(){
        login.personalIDProof("farnz","f");
    }
}
