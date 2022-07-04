package GUI;

import javax.swing.*;

public class Login {

    // Main

        private JPanel main;
            private JPasswordField passwordField;
            private JTextField usernameField;
            private JButton bestätigenButton;
            private JButton abbrechenButton;

    public static void main(String[] args) {
        new Login();
    }

    public Login(){

        JFrame frame = new JFrame();

        frame.add(main);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400,200);
    }
}
