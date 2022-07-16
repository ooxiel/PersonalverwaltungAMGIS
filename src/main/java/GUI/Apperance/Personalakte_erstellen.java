package GUI.Apperance;


import GUI.ProofServices.Delete;
import GUI.ProofServices.DynamicInputProof;
import GUI.ProofServices.StaticInputProof;
import com.AMGIS.Data_Handling.PA_erstellen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class Personalakte_erstellen extends JFrame {

    // Main

    private JPanel main;
    private JButton abbrechenButton;
    private JButton personalakteErstellenButton;
    private JButton alleEingabenLoeschenButton;

    // Panels

    private JPanel personalInfoPanel;


        private JLabel anredeField;
            private JComboBox geschlecht;
            private JTextField zweitNameField;
            private JTextField vornameField;
            private JTextField geburstagField;
            private JTextField telefonField;
            private JTextField emailField;
            private JTextField nameField;

        private JPanel adressPanel;
            private JTextField strasseField;
            private JTextField hausnummerField;
            private JTextField hausnummerZusatzField;
            private JTextField landField;
            private JTextField bundeslandField;
            private JTextField plzField;

        private JPanel jobInfoPanel;
            private JTextField jobnameField;
            private JTextField beschaeftigungField;
            private JTextField positionField;
            private JTextField abteilungField;
            private JTextField abteilungsLeiterField;
            private JTextField raumField;
            private JTextField standortField;

    private JButton button1;


    public static void main(String[] args) {
        new Personalakte_erstellen();
    }

    public Personalakte_erstellen() {

     /*
        Initialisierung Main-Frame
      */

        JFrame          frame       = new JFrame();

        frame.add(main);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000,1300);
        frame.setLocationRelativeTo(null);

    /*
        Ueberpruefung User-Eingaben während dem Input
        -> z.B. Namen, Strassen etc. duerfen keine Zahlen enthalten
        -> z.B. Postleitzahl darf keine Buchstaben enthalten
     */

        // Zuordnung der in der Form stehen Felder in die Kategorien
            // - optionale Input    -> optionalInput
            // - nur Buchstaben     -> lettersonly
            // - nur Zahlen         -> numbersonly
            // - spezial Zeichen    -> specialChars

            DynamicInputProof   dynamicInput    = new DynamicInputProof();                // Klasse, in welcher sich die einfachen Überprüfungen (Null, Zahl und Buchstaben) wiederfinden
            StaticInputProof    staticInput     = new StaticInputProof();             // Klasse, in welcher spezielle Ueberpruefungen vorgenommen werden, wie von DateFields, E-Mail


        ArrayList<JTextField> optionalInput         = new ArrayList<>();

            optionalInput.add(zweitNameField);
            optionalInput.add(hausnummerZusatzField);
            optionalInput.add(abteilungsLeiterField);

        ArrayList<JTextField> lettersOnly       = new ArrayList<>();

            lettersOnly.add(nameField);
            lettersOnly.add(vornameField);
            lettersOnly.add(strasseField);
            lettersOnly.add(landField);
            lettersOnly.add(bundeslandField);
            lettersOnly.add(jobnameField);
            lettersOnly.add(positionField);
            lettersOnly.add(standortField);

        ArrayList<JTextField> numbersOnly       = new ArrayList<>();

            numbersOnly.add(plzField);
            numbersOnly.add(beschaeftigungField);
            numbersOnly.add(hausnummerField);

        ArrayList<JTextField> specialChars      = new ArrayList<>();

            specialChars.add(emailField);
            specialChars.add(geburstagField);
            specialChars.add(telefonField);
            specialChars.add(abteilungField);

        // Methodenaufruf fuer: Es befinden sich nur Buchstaebn in diesem Feld

            dynamicInput.onlyLetterField(lettersOnly);
            dynamicInput.onlyLetterField(optionalInput);

        // Methodenaufruf fuer: Es befinden sich nur Zahlen in diesem Feld

            dynamicInput.onlyNumberField(numbersOnly);

        // Methodenaufruf fuer: Die Felder erfuellen ein bestimmtes Format

            dynamicInput.dateField(geburstagField);
            dynamicInput.telefonField(telefonField);

        // Methodenaufruf fuer: Die Felder duerfen nur eine bestimmte Anzahl an Charactern aufweisen

            dynamicInput.setAmountofCharacterAllowed(hausnummerZusatzField, 1);
            dynamicInput.setAmountofCharacterAllowed(plzField,5);
            dynamicInput.setAmountofCharacterAllowed(beschaeftigungField,3);

    /*
        Alle Eingaben in der Form werden ausnahmslos geloescht
    */
        personalakteErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                staticInput.setMaxInteger(beschaeftigungField, 100);

                if (staticInput.inputNotNull(lettersOnly) &&
                        staticInput.inputNotNull(numbersOnly) &&
                        staticInput.inputNotNull(specialChars) &&
                        staticInput.comboBoxFieldisEmpty(geschlecht)){

                            JOptionPane.showMessageDialog(main, "Es fehlen notwendige Eingaben!");

                    }else{

                            boolean testGeburstag   = staticInput.dateValid(geburstagField);
                            boolean testTelefon     = staticInput.telefonValide(telefonField);
                            boolean testMail        = staticInput.mailValide(emailField);

                                if(testGeburstag && testTelefon && testMail){
                                    PA_erstellen pae = new PA_erstellen();
                                    pae.einfuegenPA(geschlecht.getSelectedItem().toString(), vornameField.getText(), zweitNameField.getText(), nameField.getText(),
                                            geburstagField.getText(), telefonField.getText(), emailField.getText(), strasseField.getText(), hausnummerField.getText(),
                                            hausnummerZusatzField.getText(), landField.getText(), bundeslandField.getText(), plzField.getText(), jobnameField.getText(),
                                            beschaeftigungField.getText(), abteilungField.getText(), abteilungsLeiterField.getText(), raumField.getText(), standortField.getText());

                                    try {
                                        pae.con.close();
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }

                                    JOptionPane.showMessageDialog(main, "Eingabe Erfolgreich!");
                                    frame.dispose();
                                }else{
                                    JOptionPane.showMessageDialog(main,"Ungueltige Angaben!");
                                }
                    }

                }
            });

        alleEingabenLoeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete delete = new Delete();

                delete.setListNull(optionalInput);
                delete.setListNull(lettersOnly);
                delete.setListNull(numbersOnly);
                delete.setListNull(specialChars);

                delete.setComboBoxNull(geschlecht);
            }
        });

        /*
        Form wird kann über Abbrechen-Button geschlossen werden
    */

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

    /*
        Ueberpruefung der Angaben sowie Erstellung eines neuen Mitarbeiter-Objektes
     */


// ------------------------------------------------------------------------------------------
/*
    möglicher Anhang von Dateien (Ansatz)
 */
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appdata = System.getenv("APPDATA");

                File appDataDir = new File(appdata);

                JFileChooser fileChooser = new JFileChooser(appdata);
                fileChooser.showOpenDialog(new JFrame());
            }
        });
    }
}
