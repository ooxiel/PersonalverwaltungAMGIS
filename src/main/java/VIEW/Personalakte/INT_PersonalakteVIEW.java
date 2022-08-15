package VIEW.Personalakte;

import CONTROLLER.Functions.Personalakte;

import javax.swing.*;
import java.util.ArrayList;

public interface INT_PersonalakteVIEW {

    void show(JFrame frame, JButton button);
    ArrayList<JTextField> createOptionalInput();
    ArrayList<JTextField> createLettersOnly();
    ArrayList<JTextField> createNumbersOnly();
    ArrayList<JTextField> createSpecialChars();
    void createAttachements();
    void design(JFrame frame);
    void proofInputDynamic(ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly);
    void deleteAll(ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars);

    void savePersonalakte(JFrame frame, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars);
    default void deletePersonalakte(JFrame frame, JPanel main, JButton button, String pid){
        new Personalakte().delete(frame, main, button, pid);
    };

}
