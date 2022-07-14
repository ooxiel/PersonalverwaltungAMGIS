package GUI.Apperance;

import com.AMGIS.Login.LoginCheck;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class Login {

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

    public void anmeldungAusfuehren(JFrame frame){
        if(usernameField.getText().isEmpty() && passwordField.getPassword().length==0){
            JOptionPane.showMessageDialog(main,"Username oder Passwort nicht aufgefuellt!");
        }else{
            LoginCheck lc = new LoginCheck();
            if(lc.validateKontoname(usernameField.getText()) && lc.validatePasswort(usernameField.getText(), String.valueOf(passwordField.getPassword()))){
                frame.dispose();
                new MainHR();
            }else{
                JOptionPane.showMessageDialog(main,"Username oder Passwort ist falsch!");
                passwordField.setText("");
                try {lc.c.close();System.out.println("closing in else");} catch (SQLException ex) {ex.printStackTrace();}
            }
        }
    }

    public Login() throws IOException {
    /*
        Login-Panel wird geoeffnet
     */
        JFrame frame = new JFrame();
            frame.add(main);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    /*
        Zentrierung Login-Panel in Abhängigkeit der Monitorauflösung
     */

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            frame.setSize(500,250);                             // Groesse des Login-Panel wird auf 400, 200 gesetzt, kann aber auch unabhaengig von unteren Code-Fragmenten variiert werden
            frame.setResizable(false);                                      // Groesse des Login-Panel ist fix

                int widthScreen = (int) screenSize.getWidth();              // Monitoraufloesung in horizontale Richtung wird in Variable gespeichert
                int heightScreen = (int) screenSize.getHeight();            // Monitoraufloesung in vertikale Richtung wird in Variable gespeichert

                int widthLayout = (int) frame.getSize().getWidth();         // horizontale Groesse Login-Panel wird in Variable gespeichert
                int heigtLayout = (int) frame.getSize().getHeight();        // vertikale Groesse Login-Panel wird in Variable gespeichert

            int widthLayoutPosition = (widthScreen - widthLayout)/2;        // Ermittlung der horizontalen Position des Login-Panel
            int heigtLayoutPosition = (heightScreen - heigtLayout)/2;       // Ermittlung der vertikalen Position des Login-Panel

            frame.setLocation(widthLayoutPosition,heigtLayoutPosition);     // Login-Panel wird in Abhaengigkeit von Monitorauflösung zentriert angezeigt

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

        ImageIcon userIcon      = new ImageIcon("C:\\Users\\Lennard\\IdeaProjects\\PersonalverwaltungAMGIS\\src\\main\\resources\\icons\\user.png");
        ImageIcon passwordIcon  = new ImageIcon("C:\\Users\\Lennard\\IdeaProjects\\PersonalverwaltungAMGIS\\src\\main\\resources\\icons\\password.png");

            usernameImage.setIcon(userIcon);
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

                    anmeldungAusfuehren(frame);
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

                    anmeldungAusfuehren(frame);
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

                    anmeldungAusfuehren(frame);
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
                anmeldungAusfuehren(frame);
            }
        });


    }

}
