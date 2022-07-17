package GUI.Funktionaliteat.Personalakte;


import GUI.Ueberpruefung.Loeschern.Delete;
import GUI.Ueberpruefung.Eingaben.DynamicInputProof;
import GUI.Ueberpruefung.Eingaben.StaticInputProof;
import com.AMGIS.Data_Handling.PA_erstellen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
    private JLabel logoIconLeft;
    private JLabel logoIconRight;
    private JLabel erstelltDate;
    private JLabel geandertDate;


    public static void main(String[] args) {
        new Personalakte_erstellen();
    }

    public Personalakte_erstellen() {

        JFrame              frame           = new JFrame();

        DynamicInputProof   dynamicInput    = new DynamicInputProof();
        StaticInputProof    staticInput     = new StaticInputProof();

        ArrayList<JTextField> optionalInput     = new ArrayList<>();
        ArrayList<JTextField> lettersOnly      = new ArrayList<>();
        ArrayList<JTextField> numbersOnly       = new ArrayList<>();
        ArrayList<JTextField> specialChars      = new ArrayList<>();

        show(frame);
        disposeButton(frame);
        deleteAll(optionalInput, lettersOnly, numbersOnly, specialChars);

            addOptionalInput(optionalInput);
            addLettersOnly(lettersOnly);
            addNumbersOnly(numbersOnly);
            addSpecialChars(specialChars);

            checkInputDynamicStandard(dynamicInput, optionalInput,lettersOnly,numbersOnly);
            checkInputDynamicSpecial(dynamicInput, specialChars);

            userInputPruefungStatisch(frame,staticInput, lettersOnly, numbersOnly, specialChars);
    }

/*
 *                      // GUI-Funktionen-Implementierung \\
 */

    private void show(JFrame frame){

        frame.add(main);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000,1300);
        frame.setLocationRelativeTo(null);

        Image logo = null;
        try{
            logo = ImageIO.read(new File("src/main/resources/icons/LogoKlein.png"));
        }catch (IOException ex){
            ex.printStackTrace();
        }
        ImageIcon logoToIcon = new ImageIcon(logo);

        logoIconLeft.setIcon(logoToIcon);
        //logoIconRight.setIcon(logoToIcon);
    }

    private void disposeButton(JFrame frame){

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    private void deleteAll(ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars){

        alleEingabenLoeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete delete = new Delete();

                delete.setListNull(optionalInput);
                delete.setListNull(lettersOnly);
                delete.setListNull(numbersOnly);
                delete.setListNull(specialChars);

                delete.setFieldNull(raumField);
                delete.setComboBoxNull(geschlecht);
            }
        });
    }

    private void getAttachements(){

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

/*
 *                  // dynamische Feld-Ueberpruefungen-Implementierung \\
 */

    private ArrayList<JTextField> addOptionalInput(ArrayList<JTextField> optionalInput) {

            optionalInput.add(zweitNameField);
            optionalInput.add(hausnummerZusatzField);
            optionalInput.add(abteilungsLeiterField);

        return optionalInput;
    }

    private ArrayList<JTextField> addLettersOnly(ArrayList<JTextField> lettersOnly) {

            lettersOnly.add(nameField);
            lettersOnly.add(vornameField);
            lettersOnly.add(strasseField);
            lettersOnly.add(landField);
            lettersOnly.add(bundeslandField);
            lettersOnly.add(jobnameField);
            lettersOnly.add(positionField);
            lettersOnly.add(standortField);

        return lettersOnly;
    }

    private ArrayList<JTextField> addNumbersOnly( ArrayList<JTextField> numbersOnly){

            numbersOnly.add(plzField);
            numbersOnly.add(beschaeftigungField);
            numbersOnly.add(hausnummerField);

        return numbersOnly;
    }

    private ArrayList<JTextField> addSpecialChars(ArrayList<JTextField> specialChars){

            specialChars.add(emailField);
            specialChars.add(geburstagField);
            specialChars.add(telefonField);
            specialChars.add(abteilungField);

        return specialChars;
    }

    private void checkInputDynamicStandard(DynamicInputProof dynamicInput, ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly){

        dynamicInput.onlyLetterField(optionalInput);
        dynamicInput.onlyLetterField(lettersOnly);
        dynamicInput.onlyNumberField(numbersOnly);
    }
    private void checkInputDynamicSpecial(DynamicInputProof dynamicInput,ArrayList<JTextField> specialChars){

        dynamicInput.setAmountofCharacterAllowed(telefonField, 15);
        dynamicInput.setAmountofCharacterAllowed(hausnummerZusatzField,1);
        dynamicInput.setAmountofCharacterAllowed(plzField,5);
        dynamicInput.setAmountofCharacterAllowed(beschaeftigungField,3);

        dynamicInput.dateField(geburstagField);
    }

/*
*                  // statische, finale Feld-Ueberpruefungen-Implementierung \\
*/

    private void userInputPruefungStatisch(JFrame frame, StaticInputProof staticInput,ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars){

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
                    createMitarbeiter(frame, staticInput);
                }

            }
        });
    }

    private void createMitarbeiter(JFrame frame, StaticInputProof staticInput){

        boolean testGeburstag   = staticInput.dateValid(geburstagField);
        boolean testTelefon     = staticInput.telefonValide(telefonField);
        boolean testMail        = staticInput.mailValide(emailField);

        if(testGeburstag && testTelefon && testMail){

            PA_erstellen pae = new PA_erstellen();

            pae.einfuegenPA(geschlecht.getSelectedItem().toString(), vornameField.getText(), zweitNameField.getText(), nameField.getText(),
                    geburstagField.getText(), telefonField.getText(), emailField.getText(), strasseField.getText(), hausnummerField.getText(),
                    hausnummerZusatzField.getText(), landField.getText(), bundeslandField.getText(), plzField.getText(), jobnameField.getText(),
                    beschaeftigungField.getText(), abteilungField.getText(), abteilungsLeiterField.getText(), raumField.getText(), standortField.getText());


            //FELD MUSS NOCH EINGEFÜGT WERDEN
            erstelltDate.setText(String.valueOf(LocalDateTime.now()));

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
