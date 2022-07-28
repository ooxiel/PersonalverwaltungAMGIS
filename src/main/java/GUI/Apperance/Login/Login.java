package GUI.Apperance.Login;


import GUI.Apperance.Hauptbildschirm.HR.MainHR;
import GUI.Apperance.Hauptbildschirm.Mitarbeiter.MainMitarbeiter;
import GUI.Apperance.Hauptbildschirm.Root.MainRoot;
import com.AMGIS.Akteure.HR_Mitarbeiter;
import com.AMGIS.Akteure.Mitarbeiter;
import com.AMGIS.Login.LoginCheck;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

public class Login {

    // Main, Variablendeklarierung

    private JPanel main;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton besteatigenButton;
    private JButton abbrechenButton;

    private JLabel usernameImage;
    private JLabel passwordImage;
    private JLabel loginLabel;
    private JLabel logoImage;
    private ImageIcon userImage;
    private ImageIcon passImage;

    public static void main(String[] args) throws IOException {
        new Login();
    }

    public Login() throws IOException {
    /*
        Login-Panel wird geoeffnet
     */
        JFrame frame = new JFrame();
        frame.add(main);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


    /*
        Zentrierung Login-Panel in Abhängigkeit der Monitorauflösung
     */



    /*
        Login-Frame wird geschlossen
     */

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

    /*
        Design
     */

        // Icons werden im JLabel

        Image imgUser = ImageIO.read(new File("src/main/resources/icons/user.png"));
        ImageIcon userIcon = new ImageIcon(imgUser);
        usernameImage.setIcon(userIcon);

        Image imgPass = ImageIO.read(new File("src/main/resources/icons/password.png"));
        ImageIcon passwordIcon = new ImageIcon(imgPass);
        passwordImage.setIcon(passwordIcon);

        Image imgLogo = ImageIO.read(new File("src/main/resources/icons/LogoGross.png"));
        ImageIcon logoIcon = new ImageIcon(imgLogo);
        logoImage.setIcon(logoIcon);


        // Border-Types werden geändert

        Border border = new BevelBorder(0, Color.white, Color.decode("#050a30"));

        usernameField.setBorder(border);
        passwordField.setBorder(border);

    /*
        Username und Password-Eingabe wird zur Ueberpruefung weitergeleitet
     */

        besteatigenButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();

                if ((input == KeyEvent.VK_ENTER)) {
                    anmeldungAusfuehren(frame);
                }
            }
        });

        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();

                if ((input == KeyEvent.VK_ENTER)) {
                    anmeldungAusfuehren(frame);
                }

            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();

                if ((input == KeyEvent.VK_ENTER)) {
                    anmeldungAusfuehren(frame);
                }

            }
        });

        abbrechenButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();

                if ((input == KeyEvent.VK_ENTER)) {
                    frame.dispose();
                }

            }
        });

        besteatigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anmeldungAusfuehren(frame);
            }
        });
    }

    public void anmeldungAusfuehren(JFrame frame) {
        if (usernameField.getText().isEmpty() && passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(main, "Username oder Passwort nicht aufgefuellt!");
        } else {
            LoginCheck lc = new LoginCheck();
            //System.out.println("amgis: "+lc.isHR_User("amgis"));
            //System.out.println("admin: "+lc.isHR_User("admin"));
            if (lc.validateKontoname_HR(usernameField.getText()) && lc.validatePasswort_HR(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
                if (lc.isRoot(Integer.parseInt(lc.searchIDwithKN_HR(usernameField.getText())))) {
                    MainRoot r = new MainRoot();
                } else {
                    //Mitarbeiter Objekt erzeugen
                    HR_Mitarbeiter hrMitarbeiter = new HR_Mitarbeiter(Integer.parseInt(lc.searchIDwithKN_HR(usernameField.getText())), usernameField.getText(), String.valueOf(passwordField.getPassword()), true);
                    frame.dispose();
                    new MainHR(/*hrMitarbeiter*/);
                }
            } else if (lc.validateKontoname_M(usernameField.getText()) && lc.validatePasswort_M(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
                //Mitarbeiter Objekt erzeugen
                Mitarbeiter mitarbeiter = new Mitarbeiter(Integer.parseInt(lc.searchIDwithKN_M(usernameField.getText())), usernameField.getText(), String.valueOf(passwordField.getPassword()), false);
                frame.dispose();
                new MainMitarbeiter();
            } else {
                JOptionPane.showMessageDialog(main, "Username oder Passwort ist falsch!");
                passwordField.setText("");
                try {
                    lc.c.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
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
