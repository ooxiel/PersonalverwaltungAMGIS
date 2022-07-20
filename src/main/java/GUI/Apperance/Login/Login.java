package GUI.Apperance.Login;


import GUI.Apperance.Hauptbildschirm.MainHR;
import GUI.Apperance.Hauptbildschirm.MainMitarbeiter;
import com.AMGIS.Login.LoginCheck;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Login{

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
        JFrame          frame       = new JFrame();
            frame.add(main);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(500,300);
            frame.setResizable(true);
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

        Image       imgUser             = ImageIO.read(new File("src/main/resources/icons/user.png"));
            ImageIcon   userIcon        = new ImageIcon(imgUser);
            usernameImage.setIcon(userIcon);

        Image       imgPass             = ImageIO.read(new File("src/main/resources/icons/password.png"));
            ImageIcon   passwordIcon    = new ImageIcon(imgPass);
            passwordImage.setIcon(passwordIcon);

        Image       imgLogo             = ImageIO.read(new File("src/main/resources/icons/LogoGross.png"));
            ImageIcon   logoIcon        = new ImageIcon(imgLogo);
            logoImage.setIcon(logoIcon);


       // Border-Types werden geändert

        Border border = new BevelBorder(0, Color.white,Color.decode("#050a30"));

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

                if((input == KeyEvent.VK_ENTER)){
                    anmeldungAusfuehren(frame);
                }
            }
        });

        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();

                if((input == KeyEvent.VK_ENTER)){
                    anmeldungAusfuehren(frame);
                }

            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();

                if((input == KeyEvent.VK_ENTER)){
                    anmeldungAusfuehren(frame);
                }

            }
        });

        abbrechenButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();

                if((input == KeyEvent.VK_ENTER)){
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
    public void anmeldungAusfuehren(JFrame frame){
        if(usernameField.getText().isEmpty() && passwordField.getPassword().length==0){
            JOptionPane.showMessageDialog(main,"Username oder Passwort nicht aufgefuellt!");
        }else{
            LoginCheck lc = new LoginCheck();
            //System.out.println("amgis: "+lc.isHR_User("amgis"));
            //System.out.println("admin: "+lc.isHR_User("admin"));
            if(lc.validateKontoname(usernameField.getText()) && lc.validatePasswort(usernameField.getText(), String.valueOf(passwordField.getPassword())) && lc.isHR_User(usernameField.getText())){
                frame.dispose();
                new MainHR();
            }else if(lc.validateKontoname(usernameField.getText()) && lc.validatePasswort(usernameField.getText(), String.valueOf(passwordField.getPassword())) && !(lc.isHR_User(usernameField.getText()))){
                frame.dispose();
                new MainMitarbeiter();
            }else{
                JOptionPane.showMessageDialog(main,"Username oder Passwort ist falsch!");
                passwordField.setText("");
                try {
                    lc.c.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
