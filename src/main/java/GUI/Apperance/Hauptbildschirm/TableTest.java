package GUI.Apperance.Hauptbildschirm;

import com.AMGIS.TableModels.LogindatenTableModel;
import com.AMGIS.Akteure.Logindaten;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TableTest {
    private JPanel main;

    public static void main(String[] args) {
        new TableTest();
    }
    public TableTest(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);














        List<Logindaten> logindaten = new ArrayList<>();
        Logindaten logindaten1 = new Logindaten(1,"aaaaaa","dddddddd",true);
        Logindaten logindaten2 = new Logindaten(2,"bbbbbbbb","eeeeeeeeee",false);
        Logindaten logindaten3 = new Logindaten(3,"ccccccc","ffffffff",true);


        logindaten.add(logindaten1);logindaten.add(logindaten2);logindaten.add(logindaten3);
        String[] Header = {"ID", "KONTONAME", "PASSWORT","HR Mitarbeiter"};
        LogindatenTableModel ldtm = new LogindatenTableModel(logindaten);
        JTable t = new JTable(ldtm);
        JScrollPane jsp = new JScrollPane();
        jsp.add(t);
        frame.add(new JScrollPane(t));

        //frame.add(t.getTableHeader(), BorderLayout.NORTH);
        //frame.add(t);
        //frame.pack();


    }
}
