package GUI.Apperance;


import GUI.ProofServices.Delete;
import GUI.ProofServices.DynamicInputProof;
import GUI.ProofServices.StaticInputProof;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
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
            private JTextField regionField;

    private JButton button1;
    private JLabel labelGeschlecht;


    public static void main(String[] args) {
        new Personalakte_erstellen();
    }

    public Personalakte_erstellen() {

     /*
        Initialisierung Main-Frame
      */

        JFrame          frame           = new JFrame();
        FrameLocation   centerFrame     = new FrameLocation();

        frame.add(main);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setSize(1000,1300);
            int widthLayout     = (int) frame.getSize().getWidth();
            int heightLayout    = (int) frame.getSize().getHeight();

        frame.setLocation(centerFrame.center(widthLayout,heightLayout));
        frame.setVisible(true);

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

        ArrayList<JTextField> lettersOnly       = new ArrayList<>();

            lettersOnly.add(nameField);
            lettersOnly.add(vornameField);
            lettersOnly.add(strasseField);
            lettersOnly.add(landField);
            lettersOnly.add(bundeslandField);
            lettersOnly.add(jobnameField);
            lettersOnly.add(positionField);
            lettersOnly.add(abteilungField);
            lettersOnly.add(abteilungsLeiterField);
            lettersOnly.add(regionField);


        ArrayList<JTextField> numbersOnly       = new ArrayList<>();

            numbersOnly.add(plzField);
            numbersOnly.add(beschaeftigungField);
            numbersOnly.add(raumField);
            numbersOnly.add(hausnummerField);

        ArrayList<JTextField> specialChars      = new ArrayList<>();

            specialChars.add(emailField);
            specialChars.add(geburstagField);
            specialChars.add(telefonField);


        // Methodenaufruf fuer: Es befinden sich nur Buchstaebn in diesem Feld

            dynamicInput.onlyLetterField(lettersOnly);
            dynamicInput.onlyLetterField(optionalInput);

        // Methodenaufruf fuer: Es befinden sich nur Zahlen in diesem Feld

            dynamicInput.onlyNumberField(numbersOnly);

        // Methodenaufruf fuer: Es sind bestimtme

            dynamicInput.dateField(geburstagField);
            dynamicInput.telefonField(telefonField);




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
        Alle Eingaben in der FOrm werden ausnahmslos geloescht
    */

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
        Ueberpruefung der Angaben sowie Erstellung eines neuen Mitarbeiter-Objektes
     */

        personalakteErstellenButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                staticInput.inputNotNull(lettersOnly);
                staticInput.inputNotNull(numbersOnly);
                staticInput.inputNotNull(specialChars);

                staticInput.comboBoxFieldEmpty(geschlecht, labelGeschlecht);
                staticInput.telefonValide(telefonField);
                staticInput.mailValide(emailField);

            }
        });

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
