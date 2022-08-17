package controller.login;

import controller.tablemodel.mitarbeiter.HRMitarbeiter;
import controller.tablemodel.mitarbeiter.Mitarbeiter;
import model.login.check.LoginCheck;
import view.mainview.HRView;
import view.mainview.MitarbeiterView;
import view.mainview.RootView;

import javax.swing.*;
import java.sql.SQLException;


public class LoginController {

    public void singIn(JFrame frame, JPanel main, JTextField usernameField, JPasswordField passwordField){
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
                    new RootView();//Hauptbildschirm öffnen
                } else {
                    //HR_Mitarbeiter Objekt erzeugen
                    HRMitarbeiter hrMitarbeiter = new HRMitarbeiter(Integer.parseInt(lc.searchIDwithKN_HR(usernameField.getText())), usernameField.getText(), String.valueOf(passwordField.getPassword()), true);
                    frame.dispose();//Loginscreen schließen
                    new HRView();//Hauptbildschirm öffnen
                }
            //Prüfen des Kontonamen und Passwort in Mitarbeiter Accounts Tabelle
            } else if (lc.validateKontoname_M(usernameField.getText()) && lc.validatePasswort_M(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
                frame.dispose();//Loginscreen schließen
                //Mitarbeiter Objekt erzeugen
                Mitarbeiter m = new Mitarbeiter(Integer.parseInt(lc.searchIDwithKN_M(usernameField.getText())), usernameField.getText(), String.valueOf(passwordField.getPassword()));
                //MitarbeiterObjekt wird dem Hauptbildschirm übergeben.
                new MitarbeiterView(m);
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
