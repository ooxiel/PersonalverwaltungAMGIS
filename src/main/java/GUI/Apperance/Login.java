package GUI.Apperance;

import com.AMGIS.Facade.LoginFacade;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


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

    public Login() throws IOException {



    /*
        Login-Panel wird geoeffnet
     */

        JFrame  frame       = new JFrame();
        Frame   centerFrame  = new Frame();



            frame.add(main);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(500,250); // Groesse des Login-Panel wird auf 400, 200 gesetzt, kann aber auch unabhaengig von unteren Code-Fragmenten variiert werden


    /*
        Zentrierung Login-Panel in Abhängigkeit der Monitorauflösung

     */

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            int widthLayout = (int) frame.getSize().getWidth();
            int heightLayout = (int) frame.getSize().getHeight();

            frame.setLocation(centerFrame.center(widthLayout,heightLayout));
            frame.setResizable(false); // Groesse des Login-Panel ist fix

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

        besteatigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            // Variblen username und password werden deklariert und initialisiert

                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

            // Login-Eingaben überprüfen

                LoginFacade fassade = new LoginFacade();                                                        // Einsetzen des Facade-Patters, da Login-Überprüfung in drei Schritten bei jedem Login-Versuch erfolgen muss

                if(!fassade.testLogin(username, password)){
                    JOptionPane.showMessageDialog(main,"Username oder Passwort nicht aufgefuellt!");
                }else{
                    frame.dispose();
                    new MainHR();
                }
            }
        });
    }
}
