package GUI.Apperance;

import com.AMGIS.Akteure.Mitarbeiter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class MainHR {
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
    private JButton neuePersonalakteAnlegenButton;
    private JPanel dashboardPanel;
    private JPanel personalakteErstellen;



    public static void main(String[] args) {
        new MainHR();
    }

    public MainHR(){
        JFrame          frame       = new JFrame();

        frame.add(main);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1350,1080);
        frame.setLocationRelativeTo(null);


        TableModel dataModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return 3;
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return new Integer(rowIndex*columnIndex);
            }
        };

        JTable dataTable = new JTable(dataModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);



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
}

