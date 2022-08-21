package view.login;


import controller.appearance.DefaultFraming;
import controller.appearance.IconDesign;
import controller.login.LoginController;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Locale;

/**
 * =====================================================================================================================
 * Klasse kontruiert den Login-Bildschirm mit allen funktionalen und nicht-funktionalen Elementen
 * =====================================================================================================================
 */

public class LoginView {

    private JPanel main;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton besteatigenButton;
    private JButton abbrechenButton;

    private JLabel usernameImage;
    private JLabel passwordImage;
    private JLabel loginLabel;
    private JLabel logoImage;


    /**
     * ================================================================================================================
     * Startpunkt des gesamten Programms
     *
     * @param args Argumenten
     */
    public static void main(String[] args) {
        new LoginView();
    }

    /**
     * ================================================================================================================
     * Konstruktor der LoginView
     */
    public LoginView() {

        JFrame frame = new JFrame();

        show(frame);
        design(frame);
        check(frame);

    }

    /**
     * ================================================================================================================
     * Methode ruft alle notwendigen Methoden gebuendelt auf, damit der Login-Bildschirm erscheinen kann. Der
     * Login-Bilschirm hat speziellere Anforderungen als die anderen Bildschirme, weshalb dieser eine eigene
     * Implementierung besitzt
     *
     * @param frame Anzeigefenster
     */
    private void show(JFrame frame) {

        frame.add(main);                                                    // Inhalte werden Fenster hinzugefuegt
        frame.setSize(500, 300);                                // Fenstsergroesse auf Weite = 500 und Hoehe = 300 fixiert
        frame.setResizable(false);                                          // Fenstergroesse unveraenderbar
        frame.setVisible(true);                                             // Fenster ist Sichtbar
        frame.setLocationRelativeTo(null);                                  // Platzierung Fenster in Bildschirm-Mitte
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);      // Programm wird beim Schließen geschlossen

        new DefaultFraming().defaultDispose(frame, abbrechenButton);        // Fenster wird ueber Abbrechen-Button geschlossen
    }

    /**
     * ================================================================================================================
     * Methode ruft alle notwendigen Methoden gebuendelt auf, damit die Icons geladen werden koennen. Der
     * Login-Bilschirm hat speziellere Anforderungen als die anderen Bildschirme, weshalb dieser eine eigene
     * Implementierung besitzt
     *
     * @param frame Anzeigefenster
     */
    private void design(JFrame frame) {

        /*
            Username, Passwort und Logo werden dem jeweiligen Label zugewiesen
         */

        IconDesign icon = new IconDesign();
        icon.setIcon(frame, usernameImage, "src/main/resources/icons/user.png");
        icon.setIcon(frame, passwordImage, "src/main/resources/icons/password.png");
        icon.setIcon(frame, logoImage, "src/main/resources/icons/LogoGross.png");

        /*
            Rand-LookAndFeel von Username und Passwort-Feld wird personalisiert
         */

        Border border = new BevelBorder(0, Color.white, Color.decode("#050a30"));
        usernameField.setBorder(border);
        passwordField.setBorder(border);
    }

    /**
     * ================================================================================================================
     * Methode ruft den Controller zum Validieren der User-Eingaben auf. Hierfuer muss der Anmelden-Button gedrueckt
     * werden oder Alternativ die Enter-Taste in einem der Textfelder.
     *
     * @param frame Anzeigefenster
     */

    private void check(JFrame frame) {

        LoginController login = new LoginController();      // Generierung des LoginController-Objekts zur Validierung

        /*
            Anmelden-Button gibt Eingaben an LoginController ueber Enter-Taste weiter
         */

        besteatigenButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();

                if ((input == KeyEvent.VK_ENTER)) {
                    login.singIn(frame, main, usernameField, passwordField);
                }
            }
        });

        /*
            Betaetigen der Enter-Taste im Usernamefeld gibt Eingaben an LoginController weiter
         */

        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();

                if ((input == KeyEvent.VK_ENTER)) {
                    login.singIn(frame, main, usernameField, passwordField);
                }

            }
        });

        /*
            Betaetigen der Enter-Taste im Passwortfeld gibt Eingaben an LoginController weiter
         */

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();

                if ((input == KeyEvent.VK_ENTER)) {
                    login.singIn(frame, main, usernameField, passwordField);
                }

            }
        });

        /*
            Anmelden-Button gibt Eingaben an LoginController betaetigen des 'Anmelden'-Buttons weiter
         */

        besteatigenButton.addActionListener(e -> login.singIn(frame, main, usernameField, passwordField));
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
        main.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        main.setAutoscrolls(false);
        main.setBackground(new Color(-1));
        main.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(-1));
        Font passwordFieldFont = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.PLAIN, 16, passwordField.getFont());
        if (passwordFieldFont != null) passwordField.setFont(passwordFieldFont);
        passwordField.setText("");
        main.add(passwordField, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        usernameField = new JTextField();
        usernameField.setBackground(new Color(-1));
        Font usernameFieldFont = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.PLAIN, 16, usernameField.getFont());
        if (usernameFieldFont != null) usernameField.setFont(usernameFieldFont);
        main.add(usernameField, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        besteatigenButton = new JButton();
        besteatigenButton.setAutoscrolls(false);
        besteatigenButton.setBackground(new Color(-16446928));
        besteatigenButton.setFocusTraversalPolicyProvider(false);
        Font besteatigenButtonFont = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD, 16, besteatigenButton.getFont());
        if (besteatigenButtonFont != null) besteatigenButton.setFont(besteatigenButtonFont);
        besteatigenButton.setForeground(new Color(-1));
        besteatigenButton.setText("Anmelden");
        main.add(besteatigenButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        abbrechenButton = new JButton();
        abbrechenButton.setBackground(new Color(-16446928));
        abbrechenButton.setFocusable(true);
        Font abbrechenButtonFont = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD, 16, abbrechenButton.getFont());
        if (abbrechenButtonFont != null) abbrechenButton.setFont(abbrechenButtonFont);
        abbrechenButton.setForeground(new Color(-1));
        abbrechenButton.setText("Abbrechen");
        main.add(abbrechenButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordImage = new JLabel();
        passwordImage.setText("");
        main.add(passwordImage, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usernameImage = new JLabel();
        usernameImage.setText("");
        main.add(usernameImage, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        main.add(panel1, new GridConstraints(0, 3, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        logoImage = new JLabel();
        logoImage.setText("");
        main.add(logoImage, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loginLabel = new JLabel();
        loginLabel.setBackground(new Color(-1));
        Font loginLabelFont = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD, 26, loginLabel.getFont());
        if (loginLabelFont != null) loginLabel.setFont(loginLabelFont);
        loginLabel.setForeground(new Color(-16446928));
        loginLabel.setText("Login");
        main.add(loginLabel, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
