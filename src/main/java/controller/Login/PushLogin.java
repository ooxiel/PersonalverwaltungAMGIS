package controller.Login;

import controller.TableModel.Mitarbeiter.HR_Mitarbeiter;
import controller.TableModel.Mitarbeiter.Mitarbeiter;
import view.Personalakte.Login.Check.LoginCheck;
import view.MainScreen.DefaultHR;
import view.MainScreen.DefaultMitarbeiter;
import view.MainScreen.MainRoot;

import javax.swing.*;
import java.sql.SQLException;


public class PushLogin {

    public void anmelden(JFrame frame, JPanel main, JTextField usernameField, JPasswordField passwordField){
        //Checken ob die Eingabefelder ausgefüllt sind -> JA, dann wird ein PopUp-Fenster geöffnet
        if (usernameField.getText().isEmpty() && passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(main, "Username oder Passwort nicht aufgefuellt!");
        } else {
            //Objekt erzeugen um die Eingaben zu prüfen
            LoginCheck lc = new LoginCheck();
            /*
            Als erstes wird in der HRLogin Tabelle geprüft, ob die Eingaben vorhanden sind und richtig sind.
            Außerdem wird geprüft ob es sich um einen Root Account handelt oder nicht.
            Wenn beides nicht eintrifft, wird geprüft ob es sich um einen Mitarbeiter handelt.
             */
            //Prüfen des Kontonamen und Passwort in HR Accounts Tabelle
            if (lc.validateKontoname_HR(usernameField.getText()) && lc.validatePasswort_HR(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
                //if-B erfüllt wenn boolean 'Root' true ist
                if (lc.isRoot(Integer.parseInt(lc.searchIDwithKN_HR(usernameField.getText())))) {
                    frame.dispose();//Loginscreen schließen
                    new MainRoot();//Hauptbildschirm öffnen
                } else {
                    //HR_Mitarbeiter Objekt erzeugen
                    HR_Mitarbeiter hrMitarbeiter = new HR_Mitarbeiter(Integer.parseInt(lc.searchIDwithKN_HR(usernameField.getText())), usernameField.getText(), String.valueOf(passwordField.getPassword()), true);
                    frame.dispose();//Loginscreen schließen
                    new DefaultHR();//Hauptbildschirm öffnen
                }
            //Prüfen des Kontonamen und Passwort in Mitarbeiter Accounts Tabelle
            } else if (lc.validateKontoname_M(usernameField.getText()) && lc.validatePasswort_M(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
                frame.dispose();//Loginscreen schließen
                //Mitarbeiter Objekt erzeugen
                Mitarbeiter m = new Mitarbeiter(Integer.parseInt(lc.searchIDwithKN_M(usernameField.getText())), usernameField.getText(), String.valueOf(passwordField.getPassword()));
                //MitarbeiterObjekt wird dem Hauptbildschirm übergeben.
                new DefaultMitarbeiter(m);
            } else {
                //Eingaben haben mit keinem Datenbankeintrag übereingestimmt
                JOptionPane.showMessageDialog(main, "Username oder Passwort ist falsch!");
                passwordField.setText("");//Passwortfeld zurücksetzen
                try {
                    lc.c.close();//Datenbankverbindung zurücksetzen
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
