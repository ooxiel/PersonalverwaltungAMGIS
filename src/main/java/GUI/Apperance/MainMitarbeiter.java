package GUI.Apperance;

import javax.swing.*;

public class MainMitarbeiter {

    private JPanel main;
    private JPanel personalInfoPanel;
    private JLabel anredeField;
    private JPanel adressPanel;
    private JPanel jobInfoPanel;
    private JLabel geschlechtLabel;
    private JLabel nameLabel;
    private JLabel vornameLabel;
    private JLabel zweitNameLabel;
    private JLabel emailLabel;
    private JLabel geburstagLabel;
    private JLabel telefonLabel;
    private JLabel strasseLabel;
    private JLabel hausnummerLabel;
    private JLabel landLabel;
    private JLabel hausnummerbuchstabeLabel;
    private JLabel plzLabel;
    private JLabel bundeslandLabel;
    private JLabel jobnameLabel;
    private JLabel beschaetigungLabel;
    private JLabel positionLabel;
    private JLabel abteilungLabel;
    private JLabel abteilungsLeiterLabel;
    private JLabel raumLabel;
    private JLabel standortLabel;
    private JComboBox comboBox1;
    private JLabel welcomeLabel;

    public static void main(String[] args) {
        new MainMitarbeiter();
    }

    public MainMitarbeiter() {
        JFrame frame           = new JFrame();

        frame.add(main);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600,900);
        frame.setLocationRelativeTo(null);
    }
}
