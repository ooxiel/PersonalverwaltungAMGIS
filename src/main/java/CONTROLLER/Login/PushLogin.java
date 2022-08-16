package CONTROLLER.Login;

import CONTROLLER.TableModel.Mitarbeiter.HR_Mitarbeiter;
import CONTROLLER.TableModel.Mitarbeiter.Mitarbeiter;
import MODEL.Login.Check.LoginCheck;
import VIEW.MainScreen.DefaultHR;
import VIEW.MainScreen.DefaultMitarbeiter;
import VIEW.MainScreen.MainRoot;

import javax.swing.*;
import java.sql.SQLException;

public class PushLogin {

    public void anmelden(JFrame frame, JPanel main, JTextField usernameField, JPasswordField passwordField){
        if (usernameField.getText().isEmpty() && passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(main, "Username oder Passwort nicht aufgefuellt!");
        } else {
            LoginCheck lc = new LoginCheck();

            if (lc.validateKontoname_HR(usernameField.getText()) && lc.validatePasswort_HR(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
                if (lc.isRoot(Integer.parseInt(lc.searchIDwithKN_HR(usernameField.getText())))) {
                    frame.dispose();
                    new MainRoot();
                } else {
                    //Mitarbeiter Objekt erzeugen
                    HR_Mitarbeiter hrMitarbeiter = new HR_Mitarbeiter(Integer.parseInt(lc.searchIDwithKN_HR(usernameField.getText())), usernameField.getText(), String.valueOf(passwordField.getPassword()), true);
                    frame.dispose();
                    new DefaultHR(/*hrMitarbeiter*/);
                }
            } else if (lc.validateKontoname_M(usernameField.getText()) && lc.validatePasswort_M(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
                //Mitarbeiter Objekt erzeugen
                frame.dispose();
                Mitarbeiter m = new Mitarbeiter(Integer.parseInt(lc.searchIDwithKN_M(usernameField.getText())), usernameField.getText(), String.valueOf(passwordField.getPassword()));
                new DefaultMitarbeiter(m);
            } else {
                JOptionPane.showMessageDialog(main, "Username oder Passwort ist falsch!");
                passwordField.setText("");
                try {
                    lc.c.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
