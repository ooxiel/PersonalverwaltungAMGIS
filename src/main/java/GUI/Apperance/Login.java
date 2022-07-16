package GUI.Apperance;

import com.AMGIS.Login.AnmeldungAusfuehren;
import com.AMGIS.Login.LoginCheck;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class Login{

    // Main, Variablendeklarierung

        private JPanel main;
            private JPasswordField passwordField;
            private JTextField usernameField;
            private JButton besteatigenButton;
            private JButton abbrechenButton;

            private JLabel usernameImage;
            private JLabel passwordImage;
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

    /*
        Zentrierung Login-Panel in Abhängigkeit der Monitorauflösung
     */

        FrameLocation   location    = new FrameLocation();
            frame.setSize(500,250);
            frame.setLocation(location.center(frame.getWidth(), frame.getHeight()));
            frame.setResizable(false);

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

        Image       imgUser      = ImageIO.read(new File("src\\main\\resources\\icons\\user.png"));
            ImageIcon   userIcon     = new ImageIcon(imgUser);
            usernameImage.setIcon(userIcon);

        Image       imgPass      = ImageIO.read(new File("src\\main\\resources\\icons\\password.png"));
            ImageIcon   passwordIcon = new ImageIcon(imgPass);
            passwordImage.setIcon(passwordIcon);


       // Border-Types werden geändert

        Border border = BorderFactory.createLoweredSoftBevelBorder();

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
                System.out.println(input);
                if((input == KeyEvent.VK_ENTER)){
                    new AnmeldungAusfuehren().anmeldungAusfuehren(frame, main, usernameField, passwordField);
                }

            }
        });

        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();
                System.out.println(input);
                if((input == KeyEvent.VK_ENTER)){
                    new AnmeldungAusfuehren().anmeldungAusfuehren(frame, main, usernameField, passwordField);
                }

            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();
                System.out.println(input);
                if((input == KeyEvent.VK_ENTER)){
                    new AnmeldungAusfuehren().anmeldungAusfuehren(frame, main, usernameField, passwordField);
                }

            }
        });

        abbrechenButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();
                System.out.println(input);
                if((input == KeyEvent.VK_ENTER)){
                    frame.dispose();
                }

            }
        });

        besteatigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AnmeldungAusfuehren().anmeldungAusfuehren(frame, main, usernameField, passwordField);
            }
        });
    }
}
