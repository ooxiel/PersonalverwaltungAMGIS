package VIEW.Personalakte;


import CONTROLLER.Attachments.AnlagenTree;
import CONTROLLER.Attachments.DIR;
import CONTROLLER.DefaultApperance.DefaultFraming;
import CONTROLLER.AdditionalDesignElements.IconDesign;
import CONTROLLER.Services.Personalakte;
import CONTROLLER.UserInput.DeleteInput.Delete;
import CONTROLLER.UserInput.CheckInput.DynamicInputProof;
import CONTROLLER.UserInput.PullInput;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class Personalakte_erstellen extends JFrame implements INT_PersonalakteVIEW {

    private JPanel main;
    private JButton abbrechenButton;
    private JButton personalakteErstellenButton;
    private JButton alleEingabenLoeschenButton;

    // Panels

    private JPanel personalInfoPanel;

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
    private JTextField abteilungField;
    private JTextField abteilungsLeiterField;
    private JTextField raumField;
    private JTextField standortField;

    private JButton button1;
    private JLabel logoIconLeft;
    private JLabel logoIconRight;
    private JButton setAnlagenButton;
    private JTree pendingTree;

    public Personalakte_erstellen() {

        JFrame frame = new JFrame();

        show(frame, abbrechenButton);
        design(frame);
        createAttachements();

        ArrayList<JTextField> optionalInput = createOptionalInput();
        ArrayList<JTextField> lettersOnly = createLettersOnly();
        ArrayList<JTextField> numbersOnly = createNumbersOnly();
        ArrayList<JTextField> specialChars = createSpecialChars();

        proofInputDynamic(optionalInput, lettersOnly, numbersOnly);
        deleteAll(optionalInput, lettersOnly, numbersOnly, specialChars);

        savePersonalakte(frame, lettersOnly, numbersOnly, specialChars);
    }

    @Override
    public void show(JFrame frame, JButton button) {
        AnlagenTree anlagenTree = new AnlagenTree();
        anlagenTree.show(pendingTree, main, null);

        new DefaultFraming().show(frame, main, 1000, 1000, "DISPOSE");
        new DIR().clearOnClose(frame, button);
    }

    @Override
    public ArrayList<JTextField> createOptionalInput() {
        return new PullInput().itemstoAdd(zweitNameField, hausnummerZusatzField, abteilungsLeiterField);
    }

    @Override
    public ArrayList<JTextField> createLettersOnly() {
        return new PullInput().itemstoAdd(nameField, vornameField, strasseField, landField, bundeslandField, jobnameField, standortField);
    }

    @Override
    public ArrayList<JTextField> createNumbersOnly() {
        return new PullInput().itemstoAdd(plzField, beschaeftigungField, hausnummerField);
    }

    @Override
    public ArrayList<JTextField> createSpecialChars() {
        return new PullInput().itemstoAdd(emailField, geburstagField, telefonField, abteilungField);
    }

    @Override
    public void createAttachements() {
        new AnlagenTree().addAttachements(setAnlagenButton, pendingTree, main, null);
    }

    @Override
    public void design(JFrame frame) {

        IconDesign design = new IconDesign();
        design.setIcon(frame, logoIconLeft, "src/main/resources/icons/LogoKlein80x80.png");
        design.setIcon(frame, logoIconRight, "src/main/resources/icons/noLogoKlein80x80.png");
    }

    @Override
    public void proofInputDynamic(ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly) {

        DynamicInputProof dynamicInput = new DynamicInputProof();
        dynamicInput.onlyLetterField(optionalInput);
        dynamicInput.onlyLetterField(lettersOnly);
        dynamicInput.onlyNumberField(numbersOnly);

        dynamicInput.setAmountofCharacterAllowed(telefonField, 15);
        dynamicInput.setAmountofCharacterAllowed(hausnummerZusatzField, 1);
        dynamicInput.setAmountofCharacterAllowed(plzField, 5);
        dynamicInput.setAmountofCharacterAllowed(beschaeftigungField, 3);

        dynamicInput.dateField(geburstagField);
    }

    @Override
    public void deleteAll(ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars) {
        new Delete().all(alleEingabenLoeschenButton, optionalInput, lettersOnly, numbersOnly, specialChars, raumField, geschlecht);
    }

    @Override
    public void savePersonalakte(JFrame frame, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars) {
        new Personalakte().create(frame, main, personalakteErstellenButton, geschlecht, beschaeftigungField, lettersOnly, numbersOnly, specialChars, geburstagField,
                telefonField, emailField, vornameField, zweitNameField, nameField, strasseField, hausnummerZusatzField, hausnummerZusatzField, landField, bundeslandField,
                plzField, jobnameField, abteilungField, abteilungsLeiterField, raumField, standortField);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        main = new JPanel();
        main.setLayout(new GridLayoutManager(11, 5, new Insets(0, 0, 0, 0), -1, -1));
        main.setBackground(new Color(-16446928));
        personalInfoPanel = new JPanel();
        personalInfoPanel.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        personalInfoPanel.setBackground(new Color(-1));
        personalInfoPanel.setToolTipText("");
        main.add(personalInfoPanel, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        personalInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Name*:");
        personalInfoPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        nameField = new JTextField();
        nameField.setToolTipText("");
        personalInfoPanel.add(nameField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label2 = new JLabel();
        label2.setText("Vorname*: ");
        personalInfoPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        vornameField = new JTextField();
        personalInfoPanel.add(vornameField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label3 = new JLabel();
        label3.setText("Zweitname:");
        personalInfoPanel.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        zweitNameField = new JTextField();
        zweitNameField.setText("");
        personalInfoPanel.add(zweitNameField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label4 = new JLabel();
        label4.setText("E-Mail*:");
        personalInfoPanel.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        emailField = new JTextField();
        personalInfoPanel.add(emailField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label5 = new JLabel();
        label5.setText("Geburstag*:");
        personalInfoPanel.add(label5, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        geburstagField = new JTextField();
        personalInfoPanel.add(geburstagField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label6 = new JLabel();
        label6.setText("Telefon*:");
        personalInfoPanel.add(label6, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        telefonField = new JTextField();
        personalInfoPanel.add(telefonField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        personalInfoPanel.add(panel1, new GridConstraints(0, 2, 7, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setBackground(new Color(-16777216));
        label7.setText("Anrede*:");
        personalInfoPanel.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        geschlecht = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("");
        defaultComboBoxModel1.addElement("Herr");
        defaultComboBoxModel1.addElement("Frau");
        defaultComboBoxModel1.addElement("Divers");
        geschlecht.setModel(defaultComboBoxModel1);
        personalInfoPanel.add(geschlecht, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 6, false));
        adressPanel = new JPanel();
        adressPanel.setLayout(new GridLayoutManager(4, 6, new Insets(0, 0, 0, 0), -1, -1));
        adressPanel.setBackground(new Color(-1));
        main.add(adressPanel, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        adressPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label8 = new JLabel();
        label8.setText("Hausnummerzusatz:");
        adressPanel.add(label8, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Straße*:");
        adressPanel.add(label9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JLabel label10 = new JLabel();
        label10.setText("Hausnummer*: ");
        adressPanel.add(label10, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(112, 16), null, 1, false));
        hausnummerField = new JTextField();
        hausnummerField.setText("");
        adressPanel.add(hausnummerField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        strasseField = new JTextField();
        adressPanel.add(strasseField, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        hausnummerZusatzField = new JTextField();
        adressPanel.add(hausnummerZusatzField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label11 = new JLabel();
        label11.setText("Land*:");
        adressPanel.add(label11, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        landField = new JTextField();
        adressPanel.add(landField, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label12 = new JLabel();
        label12.setText("Postleitzahl*:");
        adressPanel.add(label12, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(112, 16), null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("Bundesland*:");
        adressPanel.add(label13, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        adressPanel.add(panel2, new GridConstraints(0, 4, 4, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        plzField = new JTextField();
        adressPanel.add(plzField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        bundeslandField = new JTextField();
        adressPanel.add(bundeslandField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        jobInfoPanel = new JPanel();
        jobInfoPanel.setLayout(new GridLayoutManager(3, 9, new Insets(0, 0, 0, 0), -1, -1));
        jobInfoPanel.setBackground(new Color(-1));
        main.add(jobInfoPanel, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jobInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label14 = new JLabel();
        label14.setText("Berufsbezeichnung*: ");
        jobInfoPanel.add(label14, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        jobnameField = new JTextField();
        jobInfoPanel.add(jobnameField, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        standortField = new JTextField();
        jobInfoPanel.add(standortField, new GridConstraints(2, 3, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        abteilungField = new JTextField();
        abteilungField.setText("");
        jobInfoPanel.add(abteilungField, new GridConstraints(1, 3, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label15 = new JLabel();
        label15.setText("Abteilungsleiter:");
        jobInfoPanel.add(label15, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        abteilungsLeiterField = new JTextField();
        jobInfoPanel.add(abteilungsLeiterField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label16 = new JLabel();
        label16.setText("Raum:");
        jobInfoPanel.add(label16, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JLabel label17 = new JLabel();
        label17.setText("Abteilung*:");
        jobInfoPanel.add(label17, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        raumField = new JTextField();
        jobInfoPanel.add(raumField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label18 = new JLabel();
        label18.setText("Standort*: ");
        jobInfoPanel.add(label18, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        jobInfoPanel.add(panel3, new GridConstraints(0, 7, 3, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        label19.setText("Beschäftigungsgrad in %*:");
        jobInfoPanel.add(label19, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        beschaeftigungField = new JTextField();
        beschaeftigungField.setText("");
        jobInfoPanel.add(beschaeftigungField, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        abbrechenButton = new JButton();
        abbrechenButton.setBackground(new Color(-1));
        abbrechenButton.setText("Abbrechen");
        main.add(abbrechenButton, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        alleEingabenLoeschenButton = new JButton();
        alleEingabenLoeschenButton.setBackground(new Color(-1));
        alleEingabenLoeschenButton.setText("Alle Eingaben loeschen");
        main.add(alleEingabenLoeschenButton, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        personalakteErstellenButton = new JButton();
        personalakteErstellenButton.setBackground(new Color(-1));
        personalakteErstellenButton.setText("Personalakte erstellen");
        main.add(personalakteErstellenButton, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-16446928));
        main.add(panel4, new GridConstraints(1, 0, 10, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setBackground(new Color(-16446928));
        main.add(panel5, new GridConstraints(1, 4, 10, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        logoIconRight = new JLabel();
        logoIconRight.setText("");
        panel5.add(logoIconRight, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setBackground(new Color(-1));
        main.add(panel6, new GridConstraints(8, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setText("Letzte Änderung:");
        panel6.add(label20, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setText("Erstellt:");
        panel6.add(label21, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        label22.setText("");
        panel6.add(label22, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
        final Spacer spacer1 = new Spacer();
        panel6.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label23 = new JLabel();
        label23.setText("");
        panel6.add(label23, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
        final JLabel label24 = new JLabel();
        Font label24Font = this.$$$getFont$$$("Arial Black", Font.ITALIC, 28, label24.getFont());
        if (label24Font != null) label24.setFont(label24Font);
        label24.setForeground(new Color(-1));
        label24.setText("Personalakte erstellen");
        main.add(label24, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logoIconLeft = new JLabel();
        logoIconLeft.setText("");
        main.add(logoIconLeft, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        main.add(panel7, new GridConstraints(6, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        setAnlagenButton = new JButton();
        setAnlagenButton.setBackground(new Color(-1));
        setAnlagenButton.setText("Anlagen anfügen");
        panel7.add(setAnlagenButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pendingTree = new JTree();
        panel7.add(pendingTree, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return main;
    }

}
