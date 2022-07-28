package GUI.Apperance.Hauptbildschirm.HR;

import GUI.Apperance.Login.Login;
import GUI.Apperance.Personalakte.Personalakte_bearbeiten;
import GUI.Apperance.Personalakte.Personalakte_erstellen;
import com.AMGIS.Data_Handling.MainHR_Table;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import java.util.Locale;

public class MainHR extends JFrame {
    private JPanel main;
    private JTable personalaktenTable;
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
    private JTable table1;
    private JButton neuenHRUserErstellenButton;
    private JButton neuePersonalakteAnlegenButton;
    private JPanel dashboardPanel;
    private JPanel personalakteErstellen;


    public static void main(String[] args) {
        new MainHR();
    }

    public MainHR(/*HR_Mitarbeiter hrMitarbeiter*/) {
        JFrame frame = new JFrame();
        show(frame);
        disposeButton(frame);
        MainHR_Table mHRt = new MainHR_Table();
        mHRt.filterTable(personalaktenTable, geschlecht.getSelectedItem().toString(), vornameField.getText(), nameField.getText(), jobnameField.getText(), abteilungField.getText(), standortField.getText());
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
        frame.setSize(1000, 700);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Border border = new BevelBorder(0, Color.white, Color.decode("#050a30"));

        geschlecht.setBorder(border);
        nameField.setBorder(border);
        vornameField.setBorder(border);
        jobnameField.setBorder(border);
        abteilungField.setBorder(border);
        standortField.setBorder(border);


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
                    int row = selected.getSelectedRow();
                    int id_toEdit = Integer.parseInt(String.valueOf(selected.getModel().getValueAt(row, 0)));
                    Connection con = null;
                    try {
                        Class.forName("org.hsqldb.jdbcDriver");
                    } catch (ClassNotFoundException ee) {
                        return;
                    }
                    try {
                        con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis");
                        String sql = "SELECT ms.person_id, ms.anrede, ms.vorname, ms.zweitname,ms.nachname, ms.geburtstag, ms.telefon, ms.email, ai.strasse, ai.strassen_nummer, ai.strassen_buchstabe, ai.land, ai.bundesland, ai.plz, ji.jobname, ji.beschaeftigungsgrad, ji.abteilung, ji.abteilungsleiter, ji.raum, ji.standort, ms.erstellt_datum, ms.aenderung_datum FROM mitarbeiterstamm ms, adressinfo ai, jobinfo ji WHERE ms.person_id=" + id_toEdit + " AND ms.person_id=ai.Adress_ID AND person_id=ji.job_ID";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            int id = rs.getInt(1);
                            String anrede = String.valueOf(rs.getString(2));
                            String vorname = String.valueOf(rs.getString(3));
                            String zweitname = String.valueOf(rs.getString(4));
                            String nachname = String.valueOf(rs.getString(5));
                            String geburtsdatum = String.valueOf(rs.getString(6));
                            String telefon = String.valueOf(rs.getString(7));
                            String email = String.valueOf(rs.getString(8));
                            String strasse = String.valueOf(rs.getString(9));
                            String strassenNR = String.valueOf(rs.getString(10));
                            String strassenBuchstabe = String.valueOf(rs.getString(11));
                            String land = String.valueOf(rs.getString(12));
                            String bundesland = String.valueOf(rs.getString(13));
                            String plz = String.valueOf(rs.getString(14));
                            String jobname = String.valueOf(rs.getString(15));
                            String besGrad = String.valueOf(rs.getString(16));
                            String abteilung = String.valueOf(rs.getString(17));
                            String abtLeiter = String.valueOf(rs.getString(18));
                            String raum = String.valueOf(rs.getString(19));
                            String standort = String.valueOf(rs.getString(20));
                            String erstelltDatum = String.valueOf(rs.getString(21));
                            String letzteAenderung = String.valueOf(rs.getString(22));
                            new Personalakte_bearbeiten(id, anrede, vorname, zweitname, nachname, geburtsdatum, telefon, email, strasse, strassenNR, strassenBuchstabe, land, bundesland, plz, jobname, besGrad, abteilung, abtLeiter, raum, standort, erstelltDatum, letzteAenderung);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void filter() {
        MainHR_Table mHRt = new MainHR_Table();
        mHRt.filterTable(personalaktenTable, geschlecht.getSelectedItem().toString(), vornameField.getText(), nameField.getText(), jobnameField.getText(), abteilungField.getText(), standortField.getText());
    }


    private void disposeButton(JFrame frame) {
        JMenuBar menu = new JMenuBar();
        JMenu logout = new JMenu("Logout");

        logout.setForeground(Color.white);
        menu.setBackground(Color.decode("#050a30"));
        menu.add(logout);
        logout.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                try {
                    new Login();
                    frame.dispose();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
        frame.setJMenuBar(menu);
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
        main.setLayout(new GridLayoutManager(17, 4, new Insets(0, 0, 0, 0), -1, -1));
        main.setBackground(new Color(-16446928));
        main.setMaximumSize(new Dimension(600, 600));
        JScrollPane = new JScrollPane();
        JScrollPane.setBackground(new Color(-1));
        JScrollPane.setEnabled(true);
        Font JScrollPaneFont = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.PLAIN, 16, JScrollPane.getFont());
        if (JScrollPaneFont != null) JScrollPane.setFont(JScrollPaneFont);
        main.add(JScrollPane, new GridConstraints(11, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        JScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        personalaktenTable = new JTable();
        personalaktenTable.setBackground(new Color(-1));
        Font personalaktenTableFont = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.PLAIN, 16, personalaktenTable.getFont());
        if (personalaktenTableFont != null) personalaktenTable.setFont(personalaktenTableFont);
        personalaktenTable.setForeground(new Color(-16777216));
        personalaktenTable.setGridColor(new Color(-16777216));
        personalaktenTable.setName("personalaktenTable");
        JScrollPane.setViewportView(personalaktenTable);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        main.add(panel1, new GridConstraints(15, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1));
        main.add(panel2, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-1));
        main.add(panel3, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-1));
        main.add(panel4, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setBackground(new Color(-1));
        main.add(panel5, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setBackground(new Color(-1));
        main.add(panel6, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.setBackground(new Color(-1));
        main.add(panel7, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel8.setBackground(new Color(-1));
        main.add(panel8, new GridConstraints(9, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel9.setBackground(new Color(-1));
        main.add(panel9, new GridConstraints(10, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel10.setBackground(new Color(-1));
        main.add(panel10, new GridConstraints(16, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel11.setBackground(new Color(-16446928));
        main.add(panel11, new GridConstraints(0, 0, 17, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel12.setBackground(new Color(-16446928));
        main.add(panel12, new GridConstraints(0, 3, 17, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 36, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-1));
        label1.setText("Übersicht digitale Personalakten");
        main.add(label1, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridLayoutManager(2, 7, new Insets(0, 0, 0, 0), -1, -1));
        panel13.setBackground(new Color(-1));
        Font panel13Font = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD, 16, panel13.getFont());
        if (panel13Font != null) panel13.setFont(panel13Font);
        main.add(panel13, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel13.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel14.setBackground(new Color(-1));
        panel13.add(panel14, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        geschlecht = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("");
        defaultComboBoxModel1.addElement("Herr");
        defaultComboBoxModel1.addElement("Frau");
        defaultComboBoxModel1.addElement("Divers");
        geschlecht.setModel(defaultComboBoxModel1);
        panel14.add(geschlecht, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        anredeField = new JLabel();
        Font anredeFieldFont = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 16, anredeField.getFont());
        if (anredeFieldFont != null) anredeField.setFont(anredeFieldFont);
        anredeField.setText("Anrede");
        panel14.add(anredeField, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel15.setBackground(new Color(-1));
        panel13.add(panel15, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 16, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Abteilung");
        panel15.add(label2, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        abteilungField = new JTextField();
        abteilungField.setText("");
        panel15.add(abteilungField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel16.setBackground(new Color(-1));
        panel13.add(panel16, new GridConstraints(1, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel17.setBackground(new Color(-1));
        panel13.add(panel17, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jobnameField = new JTextField();
        jobnameField.setToolTipText("");
        panel17.add(jobnameField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Jobname");
        panel17.add(label3, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel18 = new JPanel();
        panel18.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel18.setBackground(new Color(-1));
        panel13.add(panel18, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 16, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Name");
        panel18.add(label4, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        nameField.setToolTipText("");
        panel18.add(nameField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JPanel panel19 = new JPanel();
        panel19.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel19.setBackground(new Color(-1));
        panel13.add(panel19, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 16, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("Vorname");
        panel19.add(label5, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        vornameField = new JTextField();
        panel19.add(vornameField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JPanel panel20 = new JPanel();
        panel20.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel20.setBackground(new Color(-1));
        panel13.add(panel20, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        standortField = new JTextField();
        standortField.setToolTipText("");
        panel20.add(standortField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 16, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setText("Standort");
        panel20.add(label6, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sucheStartenButton = new JButton();
        sucheStartenButton.setBackground(new Color(-1));
        sucheStartenButton.setForeground(new Color(-16446928));
        sucheStartenButton.setText("Suche starten");
        main.add(sucheStartenButton, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        neuePersonalakteErstellenButton = new JButton();
        neuePersonalakteErstellenButton.setBackground(new Color(-1));
        neuePersonalakteErstellenButton.setForeground(new Color(-16446928));
        neuePersonalakteErstellenButton.setText("neue Personalakte erstellen");
        main.add(neuePersonalakteErstellenButton, new GridConstraints(14, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel21 = new JPanel();
        panel21.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel21.setBackground(new Color(-1));
        main.add(panel21, new GridConstraints(13, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel22 = new JPanel();
        panel22.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel22.setBackground(new Color(-1));
        main.add(panel22, new GridConstraints(12, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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

