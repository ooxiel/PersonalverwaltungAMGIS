package CONTROLLER.Services;

import javax.swing.*;
import java.util.ArrayList;

public interface INTPersonalakte {

    void create(JFrame frame, JPanel main, JButton button, JComboBox geschlecht, JTextField beschaeftigungField, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                JTextField geburstagField, JTextField telefonField, JTextField emailField, JTextField vornameField, JTextField zweitNameField, JTextField nameField, JTextField strasseField, JTextField hausnummerField,
                JTextField hausnummerZusatzField, JTextField landField, JTextField bundeslandField, JTextField plzField, JTextField jobnameField, JTextField abteilungField, JTextField abteilungsLeiterField, JTextField raumField, JTextField standortField);
    void save(JFrame frame, JPanel main, JButton button, JComboBox geschlecht, JLabel pidField, JTextField beschaeftigungField, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
              JTextField geburstagField, JTextField telefonField, JTextField emailField, JTextField vornameField, JTextField zweitNameField, JTextField nameField, JTextField strasseField, JTextField hausnummerField,JTextField hausnummerZusatzField,
              JTextField landField, JTextField bundeslandField, JTextField plzField, JTextField jobnameField, JTextField abteilungField, JTextField abteilungsLeiterField, JTextField raumField, JTextField standortField, JLabel erstelltDate);
    void edit(JTable table, String caller);
    void delete(JFrame frame, JPanel main, JButton button, String pid);

}
