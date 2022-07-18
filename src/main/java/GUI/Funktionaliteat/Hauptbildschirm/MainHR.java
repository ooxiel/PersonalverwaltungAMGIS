package GUI.Funktionaliteat.Hauptbildschirm;

import GUI.Funktionaliteat.Zusaetze.HR_erstellen;
import GUI.Funktionaliteat.Zusaetze.Login;
import GUI.Funktionaliteat.Personalakte.Personalakte_erstellen;
import GUI.LogindatenTableModel;
import GUI.MitarbeiterTableModel;
import com.AMGIS.Akteure.Logindaten;
import com.AMGIS.Akteure.Mitarbeiter;
import com.AMGIS.Data_Handling.MainHR_Table;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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





        Connection con=null;
        try {Class.forName("org.hsqldb.jdbcDriver");}catch(ClassNotFoundException e) {return;}
        try {con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis"); //url,user,pw
        }catch(SQLException e){e.printStackTrace();}

        try{
            String sql= "SELECT  ID, Kontoname, Passwort, HRMitarbeiter FROM Accounts";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);

            List<Logindaten> logindaten = new ArrayList<>();

            while (rs.next()){
                int id = rs.getInt(1);
                String kontoname = String.valueOf(rs.getString(2));
                String passwort = String.valueOf(rs.getString(3));
                boolean hrmitarbeiter = rs.getBoolean(4);
                Logindaten ld = new Logindaten(id,kontoname,passwort,hrmitarbeiter);
                logindaten.add(ld);
            }
            LogindatenTableModel ldtm = new LogindatenTableModel(logindaten);
            personalaktenTable.setModel(ldtm);
            //frame.add(new JScrollPane(personalaktenTable));
        }catch (Exception e){return;}


        neuePersonalakteErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Personalakte_erstellen();
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

