package controller.login;

import controller.functions.MitarbeiterController;
import model.login.check.LoginCheck;
import view.mainview.HRView;
import view.mainview.MitarbeiterView;
import view.mainview.RootView;

import javax.swing.*;
import java.sql.SQLException;

/** ====================================================================================================================
 * Mit der LoginController-Klasse enthaelt die Datebankabfrage fuer die Validierung der eingegeben Benutzerdaten im
 * LoginView.
 *
 * ====================================================================================================================
 */
public class LoginController {

    /** ================================================================================================================
     * Die 'singIn'-Methode fuehrt die Abfragen und Vergleiche des eingeben Usernamen und Passwort mit der Datenbank
     * aus.
     *
     * @param frame             Anzeigerahmen
     * @param main              Anzeigeinhalte
     * @param usernameField     Feld, welches den Usernamen enthaelt
     * @param passwordField     Feld, welches das Passwort enthaelt
     */

    public void singIn(JFrame frame, JPanel main, JTextField usernameField, JPasswordField passwordField){
        //Checken ob die Eingabefelder ausgefüllt sind -> JA, dann wird ein PopUp-Fenster geöffnet
        if (usernameField.getText().isEmpty() && passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(main, "Username oder Passwort nicht aufgefuellt!");
        } else {

            LoginCheck lc = new LoginCheck();       //Objekt erzeugen um die Eingaben zu prüfen

            /*
            Als erstes wird in der HRLogin Tabelle geprüft, ob die Eingaben vorhanden sind und richtig sind.
            Außerdem wird geprueft ob es sich um einen Root Account handelt oder nicht.

            Wenn beides nicht eintrifft, wird geprueft ob es sich um einen Mitarbeiter handelt.
             */

            if (lc.validateKontoname_HR(usernameField.getText()) && lc.validatePasswort_HR(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {

                //if-B erfuellt wenn boolean 'Root' true ist

                if (lc.isRoot(Integer.parseInt(lc.searchIDwithKN_HR(usernameField.getText())))) {
                    frame.dispose();    //Loginscreen schliessen
                    new RootView();     //Hauptbildschirm oeffnen
                } else {

                    //HR_Mitarbeiter Objekt erzeugen

                    frame.dispose();    //Loginscreen schließen
                    new HRView();       //Hauptbildschirm öffnen
                }

            //Pruefen des Kontonamen und Passwort in Mitarbeiter Accounts Tabelle
            } else if (lc.validateKontoname_M(usernameField.getText()) && lc.validatePasswort_M(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
                frame.dispose();        //Loginscreen schließen

                //Mitarbeiter Objekt erzeugen
                MitarbeiterController m = new MitarbeiterController(Integer.parseInt(lc.searchIDwithKN_M(usernameField.getText())), usernameField.getText(), String.valueOf(passwordField.getPassword()));
                //MitarbeiterObjekt wird dem Hauptbildschirm übergeben.
                new MitarbeiterView(m);
            } else {

                //Eingaben haben mit keinem Datenbankeintrag übereingestimmt
                JOptionPane.showMessageDialog(main, "Username oder Passwort ist falsch!");
                passwordField.setText("");      //Passwortfeld zurücksetzen
                try {
                    lc.c.close();               //Datenbankverbindung zurücksetzen
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
