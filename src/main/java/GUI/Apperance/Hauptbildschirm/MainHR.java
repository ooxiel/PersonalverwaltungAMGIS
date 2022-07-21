package GUI.Apperance.Hauptbildschirm;

import GUI.Apperance.Login.Login;
import GUI.Apperance.Personalakte.Personalakte_bearbeiten;
import GUI.Apperance.Personalakte.Personalakte_erstellen;
import com.AMGIS.Akteure.Personalakten;
import com.AMGIS.Data_Handling.MainHR_Table;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicListUI;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class MainHR extends JFrame {
    private JPanel main;
    private JTable personalaktenTable;
    private JButton abmeldenButton;
    private JButton neuenHRMitarbeiterErstellenButton;
    private JButton neuePersonalakteErstellenButton;
    private JLabel anredeField;
    private JComboBox geschlecht;
    private JTextField nameField;
    private JTextField zweitNameField;
    private JTextField vornameField;
    private JTextField emailField;
    private JTextField geburstagField;
    private JTextField telefonField;
    private JTextField strasseField;
    private JTextField landField;
    private JTextField hausnummerField;
    private JTextField hausnummerZusatzField;
    private JTextField plzField;
    private JTextField bundeslandField;
    private JTextField jobnameField;
    private JTextField abteilungField;
    private JTextField abteilungsLeiterField;
    private JTextField raumField;
    private JTextField standortField;
    private JTextField beschaeftigungField;
    private JButton sucheStartenButton;
    private javax.swing.JScrollPane JScrollPane;
    private JLabel imgLogo;
    private JTextField idField;
    private JButton neuePersonalakteAnlegenButton;
    private JPanel dashboardPanel;
    private JPanel personalakteErstellen;


    public static void main(String[] args) {
        new MainHR();
    }

    public MainHR() {
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

        personalaktenTable.addComponentListener(new ComponentAdapter() {
        });
    }

    private void show(JFrame frame) {
        frame.add(main);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

            frame.setSize(1000, 700);


        Border border = new BevelBorder(0, Color.white, Color.decode("#050a30"));

        idField.setBorder(border);
        nameField.setBorder(border);
        vornameField.setBorder(border);
        zweitNameField.setBorder(border);

        editPersonalakte();

    }

    private void editPersonalakte() {

        personalaktenTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        personalaktenTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (e.getClickCount() == 2) {
                    JTable selected = (JTable) e.getSource();
                    //int column = 0;
                    int row = selected.getSelectedRow();




                    Personalakte_bearbeiten test = new Personalakte_bearbeiten(Integer.parseInt(selected.getModel().getValueAt(row, 0).toString()), selected.getModel().getValueAt(row, 1).toString(), selected.getModel().getValueAt(row, 2).toString(), selected.getModel().getValueAt(row, 3).toString(), selected.getModel().getValueAt(row, 4).toString(), selected.getModel().getValueAt(row, 5).toString(), selected.getModel().getValueAt(row, 6).toString(), selected.getModel().getValueAt(row, 7).toString(), selected.getModel().getValueAt(row, 8).toString(), selected.getModel().getValueAt(row, 9).toString(), selected.getModel().getValueAt(row, 10).toString(), selected.getModel().getValueAt(row, 11).toString(), selected.getModel().getValueAt(row, 12).toString(), selected.getModel().getValueAt(row, 13).toString(), selected.getModel().getValueAt(row, 14).toString(), selected.getModel().getValueAt(row, 15).toString(), selected.getModel().getValueAt(row, 16).toString(), selected.getModel().getValueAt(row, 17).toString(), selected.getModel().getValueAt(row, 18).toString(), selected.getModel().getValueAt(row, 19).toString(), selected.getModel().getValueAt(row, 20).toString(), selected.getModel().getValueAt(row, 21).toString());
                }
            }
        });


    }

    public void filter() {
        MainHR_Table mHRt = new MainHR_Table();
        //alles leer
        //angepasst auf neues Layout -> aber nur erste if
        if (idField.getText().isEmpty() && geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.defaultTablePersonalakte(personalaktenTable);
        }
        //FUENF
        // id+anrede+vorname+zweitname+name
        else if (!idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && !vornameField.getText().isEmpty() && !zweitNameField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            mHRt.allFilteredTablePersonalakte(personalaktenTable, "id", idField.getText(), "anrede", geschlecht.getSelectedItem().toString(), "vorname", vornameField.getText(), "zweitname", zweitNameField.getText(), "nachname", nameField.getText());
        }
        // id
        else if (!idField.getText().isEmpty() && geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable, "id", idField.getText());
        }
        // anrede
        else if (idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable, "anrede", geschlecht.getSelectedItem().toString());
        }
        // vorname
        else if (idField.getText().isEmpty() && geschlecht.getSelectedItem().toString().isEmpty() && !vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable, "vorname", vornameField.getText());
        }
        // zweitname
        else if (idField.getText().isEmpty() && geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && !zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable, "zweitname", zweitNameField.getText());
        }
        // name
        else if (idField.getText().isEmpty() && geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            mHRt.simpleFilteredTablePersonalakte(personalaktenTable, "nachname", nameField.getText());
        }

        // ZWEI
        // id+anrede
        else if (!idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.doubleFilteredTablePersonalakte(personalaktenTable, "id", idField.getText(), "anrede", geschlecht.getSelectedItem().toString());
        }
        // id+vorname
        else if (!idField.getText().isEmpty() && geschlecht.getSelectedItem().toString().isEmpty() && !vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.doubleFilteredTablePersonalakte(personalaktenTable, "id", idField.getText(), "vorname", vornameField.getText());
        }
        // id+zweitname
        else if (!idField.getText().isEmpty() && geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && !zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.doubleFilteredTablePersonalakte(personalaktenTable, "id", idField.getText(), "zweitname", zweitNameField.getText());
        }
        // id+nachname
        else if (!idField.getText().isEmpty() && geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            mHRt.doubleFilteredTablePersonalakte(personalaktenTable, "id", idField.getText(), "nachname", nameField.getText());
        }

        // anrede+vorname
        else if (idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && !vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.doubleFilteredTablePersonalakte(personalaktenTable, "anrede", geschlecht.getSelectedItem().toString(), "vorname", vornameField.getText());
        }
        // anrede+zweitname
        else if (idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && !zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.doubleFilteredTablePersonalakte(personalaktenTable, "anrede", geschlecht.getSelectedItem().toString(), "zweitname", zweitNameField.getText());
        }
        // anrede+nachname
        else if (idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            mHRt.doubleFilteredTablePersonalakte(personalaktenTable, "anrede", geschlecht.getSelectedItem().toString(), "nachname", nameField.getText());
        }

        // vorname+zweitname
        else if (idField.getText().isEmpty() && geschlecht.getSelectedItem().toString().isEmpty() && !vornameField.getText().isEmpty() && !zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.doubleFilteredTablePersonalakte(personalaktenTable, "vorname", vornameField.getText(), "zweitname", zweitNameField.getText());
        }
        // vorname+nachname
        else if (idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && !vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            mHRt.doubleFilteredTablePersonalakte(personalaktenTable, "vorname", vornameField.getText(), "nachname", nameField.getText());
        }
        // zweitname+nachname
        else if (idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && !zweitNameField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            mHRt.doubleFilteredTablePersonalakte(personalaktenTable, "zweitname", zweitNameField.getText(), "nachname", nameField.getText());
        }

        //DREI
        //


        //VIER
        //id+anrede+vorname+zweitname
        else if (!idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && !vornameField.getText().isEmpty() && !zweitNameField.getText().isEmpty() && nameField.getText().isEmpty()) {
            mHRt.fourFilteredTablePersonalakte(personalaktenTable, "id", idField.getText(), "anrede", geschlecht.getSelectedItem().toString(), "vorname", vornameField.getText(), "zweitname", zweitNameField.getText());
        }
        //id+anrede+vorname+nachname
        else if (!idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && !vornameField.getText().isEmpty() && zweitNameField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            mHRt.fourFilteredTablePersonalakte(personalaktenTable, "id", idField.getText(), "anrede", geschlecht.getSelectedItem().toString(), "vorname", vornameField.getText(), "nachname", nameField.getText());
        }
        //id+anrede+zweitname+nachname
        else if (!idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && vornameField.getText().isEmpty() && !zweitNameField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            mHRt.fourFilteredTablePersonalakte(personalaktenTable, "id", idField.getText(), "anrede", geschlecht.getSelectedItem().toString(), "zweitname", zweitNameField.getText(), "nachname", nameField.getText());
        }
        //id+vorname+zweitname+nachname
        else if (!idField.getText().isEmpty() && geschlecht.getSelectedItem().toString().isEmpty() && !vornameField.getText().isEmpty() && !zweitNameField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            mHRt.fourFilteredTablePersonalakte(personalaktenTable, "id", idField.getText(), "vorname", vornameField.getText(), "zweitname", zweitNameField.getText(), "nachname", nameField.getText());
        }
        //anrede+vorname+zweitname+nachname
        else if (idField.getText().isEmpty() && !geschlecht.getSelectedItem().toString().isEmpty() && !vornameField.getText().isEmpty() && !zweitNameField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            mHRt.fourFilteredTablePersonalakte(personalaktenTable, "anrede", geschlecht.getSelectedItem().toString(), "vorname", vornameField.getText(), "zweitname", zweitNameField.getText(), "nachname", nameField.getText());
        }
    }


    private void disposeButton(JFrame frame) {
        abmeldenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                try {
                    new Login();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        main = new JPanel();
        main.setLayout(new GridLayoutManager(15, 5, new Insets(0, 0, 0, 0), -1, -1));
        main.setBackground(new Color(-16446928));
        main.setMaximumSize(new Dimension(600, 600));
        JScrollPane = new JScrollPane();
        JScrollPane.setBackground(new Color(-1));
        JScrollPane.setEnabled(true);
        Font JScrollPaneFont = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.PLAIN, 16, JScrollPane.getFont());
        if (JScrollPaneFont != null) JScrollPane.setFont(JScrollPaneFont);
        main.add(JScrollPane, new GridConstraints(10, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        JScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        personalaktenTable = new JTable();
        personalaktenTable.setBackground(new Color(-1));
        Font personalaktenTableFont = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.PLAIN, 16, personalaktenTable.getFont());
        if (personalaktenTableFont != null) personalaktenTable.setFont(personalaktenTableFont);
        personalaktenTable.setForeground(new Color(-16777216));
        personalaktenTable.setGridColor(new Color(-16777216));
        personalaktenTable.setName("personalaktenTable");
        JScrollPane.setViewportView(personalaktenTable);
        neuePersonalakteErstellenButton = new JButton();
        neuePersonalakteErstellenButton.setBackground(new Color(-1));
        neuePersonalakteErstellenButton.setForeground(new Color(-16446928));
        neuePersonalakteErstellenButton.setText("neue Personalakte erstellen");
        main.add(neuePersonalakteErstellenButton, new GridConstraints(14, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        main.add(panel1, new GridConstraints(11, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        abmeldenButton = new JButton();
        abmeldenButton.setBackground(new Color(-1));
        abmeldenButton.setForeground(new Color(-16446928));
        abmeldenButton.setText("Abmelden");
        main.add(abmeldenButton, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(382, 30), null, 0, false));
        sucheStartenButton = new JButton();
        sucheStartenButton.setBackground(new Color(-1));
        sucheStartenButton.setForeground(new Color(-16446928));
        sucheStartenButton.setText("Suche starten");
        main.add(sucheStartenButton, new GridConstraints(14, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1));
        main.add(panel2, new GridConstraints(7, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-1));
        main.add(panel3, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-1));
        main.add(panel4, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setBackground(new Color(-1));
        main.add(panel5, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setBackground(new Color(-1));
        main.add(panel6, new GridConstraints(4, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.setBackground(new Color(-1));
        main.add(panel7, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel8.setBackground(new Color(-1));
        main.add(panel8, new GridConstraints(8, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel9.setBackground(new Color(-1));
        main.add(panel9, new GridConstraints(9, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel10.setBackground(new Color(-1));
        main.add(panel10, new GridConstraints(12, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel11.setBackground(new Color(-1));
        main.add(panel11, new GridConstraints(13, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel12.setBackground(new Color(-16446928));
        main.add(panel12, new GridConstraints(0, 0, 15, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel13.setBackground(new Color(-16446928));
        main.add(panel13, new GridConstraints(0, 4, 15, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 36, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-1));
        label1.setText("Übersicht digitale Personalakten");
        main.add(label1, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel14.setBackground(new Color(-1));
        Font panel14Font = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD, 16, panel14.getFont());
        if (panel14Font != null) panel14.setFont(panel14Font);
        main.add(panel14, new GridConstraints(6, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel14.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel15.setBackground(new Color(-1));
        panel14.add(panel15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        idField = new JTextField();
        idField.setToolTipText("");
        panel15.add(idField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 16, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Personal-ID");
        panel15.add(label2, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel16.setBackground(new Color(-1));
        panel14.add(panel16, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        geschlecht = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("");
        defaultComboBoxModel1.addElement("Herr");
        defaultComboBoxModel1.addElement("Frau");
        defaultComboBoxModel1.addElement("Divers");
        geschlecht.setModel(defaultComboBoxModel1);
        panel16.add(geschlecht, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        anredeField = new JLabel();
        Font anredeFieldFont = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 16, anredeField.getFont());
        if (anredeFieldFont != null) anredeField.setFont(anredeFieldFont);
        anredeField.setText("Anrede");
        panel16.add(anredeField, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel17.setBackground(new Color(-1));
        panel14.add(panel17, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Name");
        panel17.add(label3, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        nameField.setToolTipText("");
        panel17.add(nameField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JPanel panel18 = new JPanel();
        panel18.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel18.setBackground(new Color(-1));
        panel14.add(panel18, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 16, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Vorname");
        panel18.add(label4, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        vornameField = new JTextField();
        panel18.add(vornameField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JPanel panel19 = new JPanel();
        panel19.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel19.setBackground(new Color(-1));
        panel14.add(panel19, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 16, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("Zweitname");
        panel19.add(label5, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        zweitNameField = new JTextField();
        zweitNameField.setText("");
        panel19.add(zweitNameField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JPanel panel20 = new JPanel();
        panel20.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel20.setBackground(new Color(-1));
        panel14.add(panel20, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return main;
    }

}

