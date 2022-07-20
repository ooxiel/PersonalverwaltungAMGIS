package GUI.Apperance.Personalakte;

import com.AMGIS.Services.InputCheck.DynamicInputProof;
import com.AMGIS.Services.InputCheck.StaticInputProof;
import com.AMGIS.Services.InputCheck.Delete;
import com.AMGIS.Data_Handling.PA_erstellen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Personalakte_bearbeiten {

    private JPanel main;
    private JPanel personalInfoPanel;
        private JTextField nameField;
        private JTextField vornameField;
        private JTextField zweitNameField;
        private JTextField emailField;
        private JTextField geburstagField;
        private JTextField telefonField;
        private JLabel anredeField;
        private JComboBox geschlecht;

    private JPanel adressPanel;
        private JTextField plzField;
        private JTextField hausnummerField;
        private JTextField strasseField;
        private JTextField hausnummerZusatzField;
        private JTextField landField;
        private JTextField bundeslandField;
    private JPanel jobInfoPanel;
        private JTextField jobnameField;
        private JTextField positionField;
        private JTextField standortField;
        private JTextField abteilungField;
        private JTextField abteilungsLeiterField;
        private JTextField beschaeftigungField;
        private JTextField raumField;

    private JButton button1;
    private JButton abbrechenButton;
    private JButton alleEingabenLoeschenButton;
    private JButton aenderungenUebernehmenButton;
    private JLabel logoIconLeft;
    private JLabel logoIconRight;
    private JLabel erstelltDate;
    private JLabel geandertDate;

    public static void main(String[] args) {
        new Personalakte_bearbeiten();
    }

    public Personalakte_bearbeiten(){

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

//---/ GUI-Funktionen-Implementierung /---//

    /** Frame erscheint
     *
     * @param frame
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
    }

    /** Frame wird geschlossen
     *
     * 'Personalakte bearbeiten'-Form wird beim *Klick* auf den Abbrechen-Button geschlossen.
     *
     * @param frame
     */

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

    private void userInputPruefungStatisch(JFrame frame, StaticInputProof staticInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars){

        aenderungenUebernehmenButton.addActionListener(new ActionListener() {

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

            /*
                HIER EINFÜGEN FÜR EINE AKTUALISIERUNG
             */


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
