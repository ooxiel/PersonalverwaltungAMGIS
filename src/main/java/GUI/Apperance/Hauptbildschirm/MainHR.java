package GUI.Apperance.Hauptbildschirm;

import GUI.Apperance.Login.Login;
import GUI.Apperance.Personalakte.Personalakte_erstellen;
import com.AMGIS.Data_Handling.MainHR_Table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainHR extends JFrame{
    private JPanel main;
    private JTable personalaktenTable;
    private JButton abmeldenButton;
    private JButton neuenHRMitarbeiterErstellenButton;
    private JButton neuePersonalakteErstellenButton;
    private JPanel personalInfoPanel;
    private JLabel anredeField;
    private JComboBox geschlecht;
    private JTextField nameField;
    private JTextField zweitNameField;
    private JTextField vornameField;
    private JTextField emailField;
    private JTextField geburstagField;
    private JTextField telefonField;
    private JPanel adressPanel;
    private JTextField strasseField;
    private JTextField landField;
    private JTextField hausnummerField;
    private JTextField hausnummerZusatzField;
    private JTextField plzField;
    private JTextField bundeslandField;
    private JPanel jobInfoPanel;
    private JTextField jobnameField;
    private JTextField positionField;
    private JTextField abteilungField;
    private JTextField abteilungsLeiterField;
    private JTextField raumField;
    private JTextField standortField;
    private JTextField beschaeftigungField;
    private JButton sucheStartenButton;
    private javax.swing.JScrollPane JScrollPane;
    private JButton neuePersonalakteAnlegenButton;
    private JPanel dashboardPanel;
    private JPanel personalakteErstellen;



    public static void main(String[] args) {
        new MainHR();
    }

    public MainHR(){
        JFrame frame = new JFrame();
        show(frame);
        disposeButton(frame);

        neuePersonalakteErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Personalakte_erstellen();
            }
        });

        sucheStartenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filter();
            }
        });

    }

    private void show(JFrame frame) {
        frame.add(main);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setLocationRelativeTo(null);
    }

    public void filter() {
        MainHR_Table mHRt = new MainHR_Table();
        //alles leer
        if(geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.defaultTablePersonalakte(personalaktenTable);

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == false && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"anrede",geschlecht.getSelectedItem().toString());

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == false && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == false && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == false && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == false && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == false && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == false && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == false && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == false && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == false && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == false && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == false && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == false && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == false && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == false && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == false && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == false && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == false && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == false && standortField.getText().isEmpty() == true) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");

        }else if (geschlecht.getSelectedItem().toString().isEmpty() == true && vornameField.getText().isEmpty() == true && zweitNameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && nameField.getText().isEmpty() == true && geburstagField.getText().isEmpty() == true && telefonField.getText().isEmpty() == true && emailField.getText().isEmpty() == true && strasseField.getText().isEmpty() == true && hausnummerField.getText().isEmpty() == true && hausnummerZusatzField.getText().isEmpty() == true && landField.getText().isEmpty() == true && bundeslandField.getText().isEmpty() == true && plzField.getText().isEmpty() == true && jobnameField.getText().isEmpty() == true && beschaeftigungField.getText().isEmpty() == true && abteilungField.getText().isEmpty() == true && abteilungsLeiterField.getText().isEmpty() == true && raumField.getText().isEmpty() == true && standortField.getText().isEmpty() == false) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable,"","");
        }
    }


            private void disposeButton(JFrame frame) {
        abmeldenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                try{
                    new Login();
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        });
    }
}

