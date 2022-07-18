package GUI.Funktionaliteat.Hauptbildschirm;

import GUI.Funktionaliteat.Zusaetze.HR_erstellen;
import GUI.Funktionaliteat.Zusaetze.Login;
import GUI.Funktionaliteat.Personalakte.Personalakte_erstellen;
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

public class MainHR{
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

        MainHR_Table t = new MainHR_Table();

        JTable dataTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(personalaktenTable);
        personalaktenTable.setVisible(true);




        Connection con=null;
        try {Class.forName("org.hsqldb.jdbcDriver");}catch(ClassNotFoundException e) {return;}
        try {con = DriverManager.getConnection("jdbc:hsqldb:file:"+new File("src\\main\\resources\\Datenbank\\AMGISDatenbank")+"; shutdown=true", "amgis", "amgis"); //url,user,pw
        }catch(SQLException e){e.printStackTrace();}

        try{
            String sql= "SELECT  ID, Kontoname,Passwort FROM Accounts";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
                String id = String.valueOf(rs.getInt(1));
                String kontoname = String.valueOf(rs.getString(2));
                String passwort = String.valueOf(3);
                String tbData[] = {id,kontoname,passwort};
                DefaultTableModel tblModel = (DefaultTableModel) personalaktenTable.getModel();
                tblModel.addRow(tbData);
            }

            //personalaktenTable.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            return;
        }
        try {con.close();} catch (SQLException ex) {ex.printStackTrace();}





        /*t.defaultTable(dataTable);
        try {t.con.close();} catch (SQLException ex) {ex.printStackTrace();}*/

        /*try {


            //TableModel definieren
            String[] tableColumnsName = {"ID","Vorname","Zweitname","Nachname","Geburtstag","2","3","3","3","3","3","3","3","3","3","3","3","3","19","20"};
            DefaultTableModel aModel = (DefaultTableModel) personalaktenTable.getModel();
            aModel.setColumnIdentifiers(tableColumnsName);
            //query

            //
            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            int colNo = rsmd.getColumnCount();
            while(rs.next()){
                Object[] objects = new Object[colNo];
                for(int i=0;i<colNo;i++){
                    objects[i]=rs.getObject(i+1);
                    System.out.println("working");
                }
                aModel.addRow(objects);
            }
            personalaktenTable.setModel(aModel);
            personalaktenTable.updateUI();

            stmt.close();
            rs.close();
            try {con.close();} catch (SQLException ex) {ex.printStackTrace();}
        } catch (SQLException e) {
            e.printStackTrace();
        }*/








        neuePersonalakteErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Personalakte_erstellen();
            }
        });
        neuenHRMitarbeiterErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HR_erstellen();
            }
        });
    }

    private void show(JFrame frame) {
        frame.add(main);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1350,1080);
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

