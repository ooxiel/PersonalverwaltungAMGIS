package controller.functions;

import model.personalakten.PersonalakteEditModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** ====================================================================================================================
 *  Die Klasse wird allgemein genutzt, um einen neuen HR-Mitarbeiter Zugang zu erstellen. Dabei kann unterschieden
 *  werden in
 *
 *  - HR mit Root
 *  - HR ohne Root
 *  ====================================================================================================================
 */
public class HRController {
    /** ================================================================================================================
     * Mit dieser Methoden kann ein neuer HR-Zugang mit unterschiedlichen Berechtigungen erstellt werden und direkt in
     * die Datenbank uebernommen werden.
     *
     * @param main              angezeigte Inhalte
     * @param button            Button, um einen HR-Zugang zu erstellen
     * @param pidField          Feld, in welchem die Personal-ID steht
     * @param vornameField      Feld, in welchem der Name der Personalakte steht
     * @param zweitNameField    Feld, in welchem der Zweitname der Personalakte steht
     * @param nameField         Feld, in welchem der Nachname der Personalakte steht
     */
    public void create(JPanel main, JButton button, JLabel pidField, JTextField vornameField, JTextField zweitNameField, JTextField nameField){

        /*
            "HR-Zugang erstellen"-Button erhaelt einen Aktionslistener um beim Betaetigen Aktionen ausfuehren zu koennen
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                    Pop-Up-Auswahl-Fenster wird generiert.
                    Das Ergebnis der Auswahl aus dem Pop-Up-Fenster wird in der Variable res gespeichert. Mit der
                    switch-case Anwendung werden die verschiedenen Ergebnisse unterschieden:

                    - Ja -> zweites Auswahl-Pop-Up öffnet sich mit der Frage, ob eine Root Berechtigung existieren soll
                    - Nein -> Auswahl wird abgebrochen

                 */
                JOptionPane confirmCreate = new JOptionPane();
                int res = confirmCreate.showConfirmDialog(main, "Wollen Sie einen neuen HR-Zugang für " + vornameField.getText() + " " + zweitNameField.getText() + " " + nameField.getText() + " erstellen?");
                switch (res) {

                    // Yes
                    case 0:
                        //Wenn ein HR Zugang erstellt werden soll, wird gefragt ob dieser Adminrechte haben soll
                        // Zweites PopUp-Fenster

                        int res2 = confirmCreate.showConfirmDialog(main, "Wollen Sie dem Account Adminrechte geben?");
                        switch (res2) {

                            // Yes
                            case 0:
                                //HR Zugang mit Adminrechten erzeugt
                                PersonalakteEditModel pab = new PersonalakteEditModel();
                                pab.generateHR(Integer.parseInt(pidField.getText()), vornameField.getText(), nameField.getText(), true, main);
                                break;

                                // No
                            case 1:
                                //HR Zugang ohne Adminrechte erzeugt
                                PersonalakteEditModel p = new PersonalakteEditModel();
                                p.generateHR(Integer.parseInt(pidField.getText()), vornameField.getText(), nameField.getText(), false, main);
                                break;
                        }

                        // No
                    case 1:
                        confirmCreate.setVisible(false);
                        break;
                }
            }
        });
    }
}
