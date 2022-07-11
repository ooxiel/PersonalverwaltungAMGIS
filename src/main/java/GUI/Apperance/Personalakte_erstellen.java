package GUI.Apperance;


import GUI.ProofServices.txtFieldProof;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class Personalakte_erstellen extends JFrame {

    // Main

    private JPanel main;
    private JButton abbrechenButton;
    private JButton personalakteErstellenButton;
    private JButton eingabenLoeschenButton;

    // Panel

    private JPanel personalInfoPanel;
    private JComboBox geschlecht;
    private JLabel anredeField;

    private JTextField zweitNameField;
        private JTextField vornameField;
        private JTextField geburstagField;
        private JTextField telefonField;
        private JTextField emailField;
        private JTextField nameField;

    private JPanel adressPanel;
        private JTextField strasseField;
        private JTextField hausnummerField;
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

        private JLabel requiredInputLabel;
        private JLabel hintAnrede;
        private JButton button1;
        private JLabel falseInputName;
        private JLabel falseInputVorname;
        private JLabel falseInputGeburstag;
        private JList list1;
        private JLabel labeltest;


    public static void main(String[] args) {
        new Personalakte_erstellen();
    }

    public Personalakte_erstellen() {

     /*
        Initialisierung Main-Frame
      */

        JFrame frame        = new JFrame();
        Frame centerFrame   = new Frame();

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

        txtFieldProof proof = new txtFieldProof();

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

        ArrayList<JTextField> specialSigns      = new ArrayList<>();

            specialSigns.add(emailField);
            specialSigns.add(telefonField);
            specialSigns.add(geburstagField);
            specialSigns.add(raumField);
            specialSigns.add(hausnummerField);

            proof.onlyLetterField(lettersOnly);

            // hier müssen noch die Funktionen für numbersOnly und specialSigns implementiert werden
            // des weiteren sollte nach einer angemessenen Möglichkeit der User-Kommunikation geschaut werden


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

        personalakteErstellenButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                    proof.inputNotNull(lettersOnly);
                    proof.inputNotNull(numbersOnly);
                    proof.inputNotNull(specialSigns);

            }
        });


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appdata = System.getenv("APPDATA");

                File appDataDir = new File(appdata);

                JFileChooser fileChooser = new JFileChooser(appdata);
                fileChooser.showOpenDialog(new JFrame());
            }
        });

/**
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------
 */

    /*
        Idee für die Umsetzung einer Input-Control, um bestimmte Character Eingaben nicht zuzulassen und somit fehler zu minimieren
     */

        geburstagField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                char input = e.getKeyChar();

            // Realisierung Datumformat

                if(geburstagField.getText().length() == 2 || geburstagField.getText().length() == 5){
                    e.setKeyChar('.');  // 3. und 6. Input wird auf einen "." gesetzt
                }

            // Es können in diesem Textfeld nur Änderungen bis 10 Zeichen vorgenommen werden, ausgenommen davon sind Back-Space, Delete und Enter
                // Falls die 10 Zeichen überschritten werden, wird das Textfeld gesperrt
                // Wenn nicht, Textfeld offen

                if(geburstagField.getText().length() >= 10 && ((input != KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE) || (input == KeyEvent.VK_ENTER))){

                    geburstagField.setEditable(false);
                    falseInputGeburstag.setText("Datum hat maximale Länge erreicht!");
                    falseInputName.setVisible(true);

                }else{
                    geburstagField.setEditable(true);
                    falseInputGeburstag.setVisible(false);
                }

            // Überprüfung

                if (!Character.isDigit(input) || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE) || (input == KeyEvent.VK_ENTER)) {
                    e.consume();

                    falseInputGeburstag.setText("Keine Eingabe von Buchstaben oder Sonderzeichen!");
                    falseInputGeburstag.setVisible(true);

                }else{
                    nameField.setEditable(true);
                    falseInputName.setVisible(false);

                }
            }
        });

    }
}



