package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
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
    private JTextArea textArea1;
    private JLabel labeltest;


    public static void main(String[] args) {
        new Personalakte_erstellen();
    }

    public Personalakte_erstellen() {

        JFrame frame = new JFrame();

        frame.add(main);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.setVisible(true);


    // Form wird geschlossen

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


    // Ueberprüfung der Angaben sowie Erstellung eines neuen Mitarbeiter-Objektes

        personalakteErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            // ArrayList wird erstellt und beinhaltet alle Felder der Form

                ArrayList <JTextField> parametersNotNull = new ArrayList<>();


                // Felder des personalInfo-Panels

                    parametersNotNull.add(nameField);
                    parametersNotNull.add(vornameField);
                    parametersNotNull.add(geburstagField);
                    parametersNotNull.add(emailField);
                    parametersNotNull.add(telefonField);

                // Felder des adress-Panels

                    parametersNotNull.add(strasseField);
                    parametersNotNull.add(hausnummerField);
                    parametersNotNull.add(landField);
                    parametersNotNull.add(bundeslandField);
                    parametersNotNull.add(plzField);

                // Felder des jobInformation-Panels

                    parametersNotNull.add(jobnameField);
                    parametersNotNull.add(beschaeftigungField);
                    parametersNotNull.add(positionField);
                    parametersNotNull.add(abteilungField);
                    parametersNotNull.add(abteilungsLeiterField);
                    parametersNotNull.add(raumField);
                    parametersNotNull.add(regionField);

            // Ueberpruefung, ob eine Eingabe vorgenommen wurde

                if(geschlecht.getSelectedIndex() == 0){
                    hintAnrede.setText("Notwendige Angabe fehlt!!!");

                    geschlecht.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            hintAnrede.setVisible(false);
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            geschlecht.removeFocusListener(this);
                        }
                    });
                }

            // Ueberpruefung, ob eine Eingabe vorgenommen wurde

                for (JTextField k: parametersNotNull) {

                    if(k.getText().isEmpty()){                          // False: keine Fehlermeldung
                                                                        // True: Fehlermeldung wird geworfen
                        k.setText("Notwendige Angabe fehlt!!!");        // Fehlermeldung = "Notwendige Angabe fehlt"

                        k.addFocusListener(new FocusListener() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                k.setText(null);                        // Fehlermeldung wird gelöscht, sobald das Feld angeklickt wird
                            }

                            @Override
                            public void focusLost(FocusEvent e) {
                                k.removeFocusListener(this);          // Sobald eine neue Eingabe erfolgt ist, kann diese "normal" bearbeteitet werden, ohne, dass das komplette Field gelöscht wird

                            }
                        });
                    }
                }
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


        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                char typed = e.getKeyChar();

                if(!Character.isLetter(typed)){
                    labeltest.setText("FALSCH!!!");

                }else{
                    labeltest.setText("Richtig");
                }
            }
        });
    }
}
