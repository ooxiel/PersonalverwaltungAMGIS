package view.personalakte;

import controller.appearance.DefaultFraming;
import controller.appearance.IconDesign;
import controller.attachments.AnlagenTree;
import controller.attachments.PendingDirectory;
import controller.functions.PersonalakteController;
import controller.userinput.checkinput.DynamicInputProof;
import controller.userinput.deleteinput.DeleteUserInput;
import controller.userinput.convertinput.ConvertUserInputToArrayList;

import javax.swing.*;
import java.util.ArrayList;


abstract class AbstractPersonalakte {

    public void show(JFrame frame, JPanel main, JTree pendingTree, JTree fileTree, JButton button, String id) {

        new DefaultFraming().show(frame, main, 1000, 1000, "DISPOSE");
        new PendingDirectory().clearOnClose(frame, button);

        AnlagenTree anlagenTree = new AnlagenTree();

        System.out.println(id);

        if(fileTree == null){
            anlagenTree.show(pendingTree, main, null, "HR");
        }else{
            anlagenTree.show(pendingTree, main, null, "HR");
            anlagenTree.show(fileTree, main, id, "HR");
        }
    }

    public ArrayList<JTextField> createOptionalInput(JTextField zweitNameField, JTextField hausnummerZusatzField, JTextField abteilungsLeiterField) {
        return new ConvertUserInputToArrayList().itemstoAdd(zweitNameField, hausnummerZusatzField, abteilungsLeiterField);
    }

    public ArrayList<JTextField> createLettersOnly(JTextField nameField, JTextField vornameField, JTextField strasseField, JTextField landField, JTextField bundeslandField, JTextField jobnameField, JTextField standortField){
        return new ConvertUserInputToArrayList().itemstoAdd(nameField, vornameField, strasseField, landField, bundeslandField, jobnameField, standortField);
    }

    public ArrayList<JTextField> createNumbersOnly(JTextField plzField, JTextField beschaeftigungField, JTextField hausnummerField) {
        return new ConvertUserInputToArrayList().itemstoAdd(plzField, beschaeftigungField, hausnummerField);
    }

    public ArrayList<JTextField> createSpecialChars(JTextField emailField, JTextField geburstagField, JTextField telefonField, JTextField abteilungField) {
        return new ConvertUserInputToArrayList().itemstoAdd(emailField, geburstagField, telefonField, abteilungField);
    }

    public void createAttachements(JButton button, JPanel main, JTree pendingTree) {
        new AnlagenTree().addAttachements(button, pendingTree, main, null, "HR");
    }

    public void design(JFrame frame, JLabel logoIconLeft, JLabel logoIconRight) {

        IconDesign design = new IconDesign();
        design.setIcon(frame, logoIconLeft, "src/main/resources/icons/LogoKlein80x80.png");
        design.setIcon(frame, logoIconRight, "src/main/resources/icons/noLogoKlein80x80.png");
    }

    public void proofInputDynamic(ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly,
                                  JTextField telefonField, JTextField hausnummerZusatzField, JTextField plzField, JTextField beschaeftigungField,
                                  JTextField geburstagField) {

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

    public void deleteAll(ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                          JButton button, JTextField raumField, JComboBox geschlecht) {

        new DeleteUserInput().all(button, optionalInput, lettersOnly, numbersOnly, specialChars, raumField, geschlecht);
    }

    public void createPersonalakte(JFrame frame, JPanel main, JButton button, JComboBox geschlecht, JTextField beschaeftigungField, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                                 JTextField geburstagField, JTextField telefonField, JTextField emailField, JTextField vornameField, JTextField zweitNameField, JTextField nameField, JTextField strasseField, JTextField hausnummerField,
                                 JTextField hausnummerZusatzField, JTextField landField, JTextField bundeslandField, JTextField plzField, JTextField jobnameField, JTextField abteilungField, JTextField abteilungsLeiterField, JTextField raumField, JTextField standortField) {

        new PersonalakteController().create(frame, main, button, geschlecht, beschaeftigungField, lettersOnly, numbersOnly, specialChars, geburstagField,
                telefonField, emailField, vornameField, zweitNameField, nameField, strasseField, hausnummerField, hausnummerZusatzField, landField, bundeslandField,
                plzField, jobnameField, abteilungField, abteilungsLeiterField, raumField, standortField);
    }
    public void savePersonalakte(JFrame frame, JPanel main, JButton button, JComboBox geschlecht, JLabel pidField, JTextField beschaeftigungField, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                                 JTextField geburstagField, JTextField telefonField, JTextField emailField, JTextField vornameField, JTextField zweitNameField, JTextField nameField, JTextField strasseField, JTextField hausnummerField,JTextField hausnummerZusatzField,
                                 JTextField landField, JTextField bundeslandField, JTextField plzField, JTextField jobnameField, JTextField abteilungField, JTextField abteilungsLeiterField, JTextField raumField, JTextField standortField, JLabel erstelltDate) {

        new PersonalakteController().save(frame, main, button, geschlecht, pidField, beschaeftigungField, lettersOnly, numbersOnly, specialChars, geburstagField,
                telefonField, emailField, vornameField, zweitNameField, nameField, strasseField, hausnummerField, hausnummerZusatzField, landField, bundeslandField,
                plzField, jobnameField, abteilungField, abteilungsLeiterField, raumField, standortField, erstelltDate);
    }
    public void deletePersonalakte(JFrame frame, JPanel main, JButton button, String pid){
        new PersonalakteController().delete(frame, main, button, pid);
    }
}
