package GUI;


import com.AMGIS.Proof;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Personalakte_erstellen extends JFrame {

    // Main

    private JPanel main;
    private JButton abbrechenButton;
    private JButton personalakteErstellenButton;
    private JButton eingabenLoeschenButton;

    // Panel

    private JPanel personalInfoPanel;
    private JComboBox geschlecht;
    private JLabel anredeField;

    private JTextField zweitNameField;
    private JTextField vornameField;
    private JTextField geburstagField;
    private JTextField telefonField;
    private JTextField emailField;
    private JTextField nameField;

    private JPanel adressPanel;
    private JTextField strasseField;
    private JTextField hausnummerField;
    private JTextField landField;
    private JTextField bundeslandField;
    private JTextField plzField;

    private JPanel jobInfoPanel;
    private JTextField jobnameField;
    private JTextField beschaeftigungField;
    private JTextField positionField;
    private JTextField abteilungField;
    private JTextField abteilungsLeiterField;
    private JTextField raumField;
    private JTextField regionField;
    private JLabel requiredInputLabel;
    private JLabel hintAnrede;
    private JButton button1;
    private JTextArea textArea1;
    private JLabel labeltest;


    public static void main(String[] args) {
        new Personalakte_erstellen();
    }

    public Personalakte_erstellen() {

        JFrame frame = new JFrame();

        frame.add(main);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.setVisible(true);


    // Form wird geschlossen

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


    // Ueberprüfung der Angaben sowie Erstellung eines neuen Mitarbeiter-Objektes

        personalakteErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Proof proof = new Proof();

                proof.jTextFieldisNull(geschlecht, hintAnrede, nameField, vornameField, geburstagField, emailField, telefonField,
                        strasseField, hausnummerField, landField, bundeslandField, plzField, jobnameField, beschaeftigungField,
                        positionField, abteilungField, abteilungsLeiterField, raumField, regionField);


                MaskFormatter phoneNumber = new MaskFormatter();

                phoneNumber.setValidCharacters("0123456789/+");


            };





        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appdata = System.getenv("APPDATA");

                File appDataDir = new File(appdata);

                JFileChooser fileChooser = new JFileChooser(appdata);
                fileChooser.showOpenDialog(new JFrame());
            }
        });
    };
}

