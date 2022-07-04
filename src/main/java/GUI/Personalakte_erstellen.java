package GUI;

import javax.swing.*;

public class Personalakte_erstellen extends JFrame{

    // Main

        private JPanel main;
            private JButton abbrechenButton;
            private JButton personalakteErstellenButton;

    // Panel

        private JPanel personalInfoPanel;
            private JComboBox comboBox1;

            private JTextField textField1;
            private JTextField textField2;
            private JTextField textField3;
            private JTextField textField4;
            private JTextField textField5;

        private JPanel adressPanel;
            private JTextField textField6;
            private JTextField textField7;
            private JTextField textField8;
            private JTextField textField9;
            private JTextField textField10;

        private JPanel jobInfoPanel;
            private JTextField textField11;
            private JTextField textField12;
            private JTextField textField13;
            private JTextField textField14;
            private JTextField textField15;
            private JTextField textField16;
            private JTextField textField17;



    public static void main(String[] args) {
        new Personalakte_erstellen();
    }

    public Personalakte_erstellen(){

        JFrame frame = new JFrame();

        frame.add(main);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800,600);

        frame.setVisible(true);

    }
}
