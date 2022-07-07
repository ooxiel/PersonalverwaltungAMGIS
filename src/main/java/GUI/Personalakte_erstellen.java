package GUI;


import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.JarEntry;

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





        personalakteErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            // ES FEHLT NOCH DIE ÜBERPRÜFUNG FÜR DAS GESCHLECHT

            // ArrayList wird erstellt und beinhaltet alle Felder der Form

                ArrayList <JTextField> parameters = new ArrayList<>();

                // Felder des personalInfo-Panels

                    parameters.add(nameField);
                    parameters.add(vornameField);
                    // Zweitname nicht vor
                    parameters.add(geburstagField);
                    parameters.add(emailField);
                    parameters.add(telefonField);

                // Felder des adress-Panels

                    parameters.add(strasseField);
                    parameters.add(hausnummerField);
                    parameters.add(landField);
                    parameters.add(bundeslandField);
                    parameters.add(plzField);

                // Felder des jobInformation-Panels

                    parameters.add(jobnameField);
                    parameters.add(beschaeftigungField);
                    parameters.add(positionField);
                    parameters.add(abteilungField);
                    parameters.add(abteilungsLeiterField);
                    parameters.add(raumField);
                    parameters.add(regionField);

            // Ueberpruefung, ob eine Eingabe vorgenommen wurde

                for (JTextField k: parameters) {


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

    }

}

