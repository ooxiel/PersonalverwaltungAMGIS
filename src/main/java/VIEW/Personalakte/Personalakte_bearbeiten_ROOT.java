package VIEW.Personalakte;

import MODEL.Personalakten.PA_bearbeiten;
import CONTROLLER.Attachments.FileDir.CreateChildNodes;
import CONTROLLER.Attachments.FileDir.FileNode;
import CONTROLLER.UserInput.DeleteInput.Delete;
import CONTROLLER.UserInput.CheckInput.DynamicInputProof;
import CONTROLLER.UserInput.CheckInput.StaticInputProof;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Personalakte_bearbeiten_ROOT {

    private JPanel main;
    private JPanel personalInfoPanel;
    private JTextField nameField;
    private JTextField vornameField;
    private JTextField zweitNameField;
    private JTextField emailField;
    private JTextField geburstagField;
    private JTextField telefonField;
    private JComboBox geschlecht;

    private JPanel adressPanel;
    private JTextField plzField;
    private JTextField hausnummerField;
    private JTextField strasseField;
    private JTextField hausnummerZusatzField;
    private JTextField landField;
    private JTextField bundeslandField;
    private JPanel jobInfoPanel;
    private JTextField jobnameField;
    private JTextField standortField;
    private JTextField abteilungField;
    private JTextField abteilungsLeiterField;
    private JTextField beschaeftigungField;
    private JTextField raumField;

    private JButton button1;
    private JButton abbrechenButton;
    private JButton alleEingabenLoeschenButton;
    private JButton aenderungenUebernehmenButton;
    private JLabel logoIconLeft;
    private JLabel logoIconRight;
    private JLabel erstelltDate;
    private JLabel geandertDate;
    private JLabel pidField;
    private JLabel mitarbeiterField;
    private JButton personalakteLoeschenButton;
    private JButton HRMitarbeiterErstellenButton;
    private JButton setAnlagenButton;
    private JTree fileTree;

    public Personalakte_bearbeiten_ROOT(int id, String anrede, String vorname, String zweitname, String nachname, String geburtsdatum, String telefon, String email, String strasse, String strassenNR, String strassenBuchstabe, String land, String bundesland,
                                        String plz, String jobname, String besGrad, String abteilung, String abtLeiter, String raum, String standort, String erstelltDatum, String letzteAenderung) {

        this.mitarbeiterField.setText("Akte von: " + vorname + " " + zweitname + " " + nachname);
        this.pidField.setText(String.valueOf(id));
        this.geschlecht.setSelectedItem(anrede);
        this.vornameField.setText(vorname);
        this.zweitNameField.setText(zweitname);
        this.nameField.setText(nachname);
        this.geburstagField.setText(geburtsdatum);
        this.telefonField.setText(telefon);
        this.emailField.setText(email);
        this.strasseField.setText(strasse);
        this.hausnummerField.setText(strassenNR);
        this.hausnummerZusatzField.setText(strassenBuchstabe);
        this.landField.setText(land);
        this.bundeslandField.setText(bundesland);
        this.plzField.setText(plz);
        this.jobnameField.setText(jobname);
        this.beschaeftigungField.setText(besGrad);
        this.abteilungField.setText(abteilung);
        this.abteilungsLeiterField.setText(abtLeiter);
        this.raumField.setText(raum);
        this.standortField.setText(standort);
        this.erstelltDate.setText(erstelltDatum);
        this.geandertDate.setText(letzteAenderung);

        JFrame frame_1 = new JFrame();

        DynamicInputProof dynamicInput_1 = new DynamicInputProof();
        StaticInputProof staticInput_1 = new StaticInputProof();

        ArrayList<JTextField> optionalInput_1 = new ArrayList<>();
        ArrayList<JTextField> lettersOnly_1 = new ArrayList<>();
        ArrayList<JTextField> numbersOnly_1 = new ArrayList<>();
        ArrayList<JTextField> specialChars_1 = new ArrayList<>();

        show(frame_1);
        disposeButton(frame_1);
        deleteAll(optionalInput_1, lettersOnly_1, numbersOnly_1, specialChars_1);
        deleteP_Akte();


        addOptionalInput(optionalInput_1);
        addLettersOnly(lettersOnly_1);
        addNumbersOnly(numbersOnly_1);
        addSpecialChars(specialChars_1);

        checkInputDynamicStandard(dynamicInput_1, optionalInput_1, lettersOnly_1, numbersOnly_1);
        checkInputDynamicSpecial(dynamicInput_1);

        userInputPruefungStatisch(frame_1, staticInput_1, lettersOnly_1, numbersOnly_1, specialChars_1);

        HRMitarbeiterErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane confirmCreate = new JOptionPane();

                int res = confirmCreate.showConfirmDialog(main, "Wollen Sie einen neuen HR-Mitarbeiter für " + vornameField.getText() + " " + zweitNameField.getText() + " " + nameField.getText() + " erstellen?");

                switch (res) {

                    case 0:
                        //JA
                        PA_bearbeiten pab = new PA_bearbeiten();
                        pab.generateHR(Integer.parseInt(pidField.getText()), vornameField.getText(), nameField.getName(), main);
                        break;
                    case 1:
                        confirmCreate.setVisible(false);
                        break;
                }
            }
        });

        setAnlagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame dirFrame = new JFrame();
                String appdata = System.getenv("APPDATA");
                File appDataDir = new File(appdata);
                JFileChooser fileChooser = new JFileChooser(appdata);

                int select = fileChooser.showOpenDialog(dirFrame);

                if (select == JFileChooser.APPROVE_OPTION) {

                    File fileSelected = fileChooser.getSelectedFile();
                    Path newDIR = Paths.get("src/main/resources/AktenFiles/" + pidField.getText());


                    try {
                        Files.copy(Path.of(fileSelected.getAbsolutePath()), newDIR.resolve(fileSelected.getName()), REPLACE_EXISTING);
                        updateFileTree();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private void updateFileTree() {
        File fileRoot = new File("src/main/resources/AktenFiles/" + pidField.getText());
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        DefaultTreeModel treeModel = new DefaultTreeModel(root);

        fileTree.setModel(treeModel);
        fileTree.setShowsRootHandles(true);

        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent event) {

                DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) fileTree.getSelectionPath().getLastPathComponent();
                FileNode fileNode = (FileNode) dmtn.getUserObject();
                File file = fileNode.getFile();

                fileTree.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);

                        File fileToOpen = new File(String.valueOf(file));

                        if (e.getClickCount() == 2 && fileToOpen.isFile()) {

                            try {
                                if (fileToOpen.exists() && Desktop.isDesktopSupported()) {
                                    Desktop.getDesktop().open(fileToOpen);
                                } else {
                                    JOptionPane.showMessageDialog(main, "Datei kann nicht geöffnet werden.");
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
            }
        });

        CreateChildNodes ccn = new CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();
    }

//---/ GUI-Funktionen-Implementierung /---//

    /*
     *                  // statische, finale Feld-Ueberpruefungen-Implementierung \\
     */

    private void show(JFrame frame) {

        frame.add(main);
        frame.setSize(1000, 1000);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        Image logo_left = null;
        try {
            logo_left = ImageIO.read(new File("src/main/resources/icons/LogoKlein80x80.png"));
        } catch (IOException ex) {
            frame.setUndecorated(true);
            ex.printStackTrace();
        }
        ImageIcon iconLogo_left = new ImageIcon(logo_left);
        logoIconLeft.setIcon(iconLogo_left);


        Image logo_right = null;
        try {
            logo_right = ImageIO.read(new File("src/main/resources/icons/noLogoKlein80x80.png"));
        } catch (IOException ex) {
            frame.setUndecorated(true);
            ex.printStackTrace();
        }
        ImageIcon iconLogo_right = new ImageIcon(logo_right);
        logoIconRight.setIcon(iconLogo_right);
    }

    private void disposeButton(JFrame frame) {

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    private void deleteAll(ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars) {

        alleEingabenLoeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete delete = new Delete();

                delete.setListNull(optionalInput);
                delete.setListNull(lettersOnly);
                delete.setListNull(numbersOnly);
                delete.setListNull(specialChars);

                delete.setFieldNull(raumField);
                delete.setComboBoxNull(geschlecht);
            }
        });
    }

    private void getAttachements() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appdata = System.getenv("APPDATA");

                File appDataDir = new File(appdata);

                JFileChooser fileChooser = new JFileChooser(appdata);
                fileChooser.showOpenDialog(new JFrame());
            }
        });
    }

    private void deleteP_Akte() {
        personalakteLoeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane confirmDelete = new JOptionPane();
                int res = confirmDelete.showConfirmDialog(main, "Sind Sie sicher, dass diese Personalakte von " + vornameField.getText() + " " + zweitNameField.getText() + " " + nameField.getText() + " endgültig gelöscht werden soll?");

                switch (res) {

                    case 0:
                        // loeschen
                        Connection con = null;
                        try {
                            Class.forName("org.hsqldb.jdbcDriver");
                        } catch (ClassNotFoundException eee) {
                            return;
                        }
                        try {
                            con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis");
                        } catch (SQLException ee) {
                            ee.printStackTrace();
                        }
                        int id = Integer.parseInt(pidField.getText());
                        String sql = "DELETE FROM Mitarbeiterstamm WHERE Person_ID=" + id + ";DELETE FROM adressinfo WHERE Adress_ID=" + id + ";DELETE FROM aktenstamm WHERE Akten_id=" + id + ";DELETE FROM jobinfo WHERE job_id=" + id + ";DELETE FROM aktenkennzeichen WHERE Akten_id=" + id + ";DELETE FROM mitarbeiterlogin  WHERE m_id=" + id + ";DELETE FROM hrroot WHERE hr_id=" + id + ";";
                        try {
                            Statement stmt = con.createStatement();
                            stmt.executeQuery(sql);
                            stmt.close();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 1:
                        // nicht loeschen
                        confirmDelete.setVisible(false);
                        break;
                }
            }
        });
    }

    private void addOptionalInput(ArrayList<JTextField> optionalInput) {

        optionalInput.add(zweitNameField);
        optionalInput.add(hausnummerZusatzField);
        optionalInput.add(abteilungsLeiterField);

    }

    private void addLettersOnly(ArrayList<JTextField> lettersOnly) {

        lettersOnly.add(nameField);
        lettersOnly.add(vornameField);
        lettersOnly.add(strasseField);
        lettersOnly.add(landField);
        lettersOnly.add(bundeslandField);
        lettersOnly.add(jobnameField);
        lettersOnly.add(standortField);

    }

    private void addNumbersOnly(ArrayList<JTextField> numbersOnly) {

        numbersOnly.add(plzField);
        numbersOnly.add(beschaeftigungField);
        numbersOnly.add(hausnummerField);

    }

    private void addSpecialChars(ArrayList<JTextField> specialChars) {

        specialChars.add(emailField);
        specialChars.add(geburstagField);
        specialChars.add(telefonField);
        specialChars.add(abteilungField);

    }

    private void checkInputDynamicStandard(DynamicInputProof dynamicInput, ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly) {

        dynamicInput.onlyLetterField(optionalInput);
        dynamicInput.onlyLetterField(lettersOnly);
        dynamicInput.onlyNumberField(numbersOnly);
    }

    private void checkInputDynamicSpecial(DynamicInputProof dynamicInput) {

        dynamicInput.setAmountofCharacterAllowed(telefonField, 15);
        dynamicInput.setAmountofCharacterAllowed(hausnummerZusatzField, 1);
        dynamicInput.setAmountofCharacterAllowed(plzField, 5);
        dynamicInput.setAmountofCharacterAllowed(beschaeftigungField, 3);

        dynamicInput.dateField(geburstagField);
    }

    private void userInputPruefungStatisch(JFrame frame, StaticInputProof staticInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars) {

        aenderungenUebernehmenButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                staticInput.setMaxInteger(beschaeftigungField, 100);

                if (staticInput.inputNotNull(lettersOnly) ||
                        staticInput.inputNotNull(numbersOnly) ||
                        staticInput.inputNotNull(specialChars) ||
                        staticInput.comboBoxFieldisEmpty(geschlecht)) {

                    JOptionPane.showMessageDialog(main, "Es fehlen notwendige Eingaben!");

                } else {
                    saveMitarbeiter(frame, staticInput);
                }

            }
        });
    }

    private void saveMitarbeiter(JFrame frame, StaticInputProof staticInput) {

        boolean testGeburstag = staticInput.dateValid(geburstagField);
        boolean testTelefon = staticInput.telefonValide(telefonField);
        boolean testMail = staticInput.mailValide(emailField);


        if (testGeburstag && testTelefon && testMail) {

            PA_bearbeiten pab = new PA_bearbeiten();
            pab.speichernPA(Integer.parseInt(pidField.getText()), geschlecht.getSelectedItem().toString(), vornameField.getText(), zweitNameField.getText(), nameField.getText(),
                    geburstagField.getText(), telefonField.getText(), emailField.getText(), strasseField.getText(), hausnummerField.getText(),
                    hausnummerZusatzField.getText(), landField.getText(), bundeslandField.getText(), plzField.getText(), jobnameField.getText(),
                    beschaeftigungField.getText(), abteilungField.getText(), abteilungsLeiterField.getText(), raumField.getText(), standortField.getText(), erstelltDate.getText());
            try {
                pab.con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(main, "Eingabe Erfolgreich!");
            frame.dispose();

        } else {
            JOptionPane.showMessageDialog(main, "Ungueltige Angaben!");
        }
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
        main.setLayout(new GridLayoutManager(14, 5, new Insets(0, 0, 0, 0), -1, -1));
        main.setBackground(new Color(-16446928));
        personalInfoPanel = new JPanel();
        personalInfoPanel.setLayout(new GridLayoutManager(8, 4, new Insets(0, 0, 0, 0), -1, -1));
        personalInfoPanel.setBackground(new Color(-1));
        personalInfoPanel.setToolTipText("");
        main.add(personalInfoPanel, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        personalInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Name*:");
        personalInfoPanel.add(label1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        nameField = new JTextField();
        nameField.setToolTipText("");
        personalInfoPanel.add(nameField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label2 = new JLabel();
        label2.setText("Vorname*: ");
        personalInfoPanel.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        vornameField = new JTextField();
        personalInfoPanel.add(vornameField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label3 = new JLabel();
        label3.setText("Zweitname:");
        personalInfoPanel.add(label3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        zweitNameField = new JTextField();
        zweitNameField.setText("");
        personalInfoPanel.add(zweitNameField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label4 = new JLabel();
        label4.setText("E-Mail*:");
        personalInfoPanel.add(label4, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        emailField = new JTextField();
        personalInfoPanel.add(emailField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label5 = new JLabel();
        label5.setText("Geburstag*:");
        personalInfoPanel.add(label5, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        geburstagField = new JTextField();
        personalInfoPanel.add(geburstagField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label6 = new JLabel();
        label6.setText("Telefon*:");
        personalInfoPanel.add(label6, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        telefonField = new JTextField();
        personalInfoPanel.add(telefonField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        personalInfoPanel.add(panel1, new GridConstraints(1, 2, 7, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setBackground(new Color(-16777216));
        label7.setText("Anrede*:");
        personalInfoPanel.add(label7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        geschlecht = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("");
        defaultComboBoxModel1.addElement("Herr");
        defaultComboBoxModel1.addElement("Frau");
        defaultComboBoxModel1.addElement("Divers");
        geschlecht.setModel(defaultComboBoxModel1);
        personalInfoPanel.add(geschlecht, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 6, false));
        final JLabel label8 = new JLabel();
        label8.setText("Personal-ID:");
        personalInfoPanel.add(label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pidField = new JLabel();
        pidField.setText("");
        personalInfoPanel.add(pidField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        adressPanel = new JPanel();
        adressPanel.setLayout(new GridLayoutManager(4, 7, new Insets(0, 0, 0, 0), -1, -1));
        adressPanel.setBackground(new Color(-1));
        main.add(adressPanel, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        adressPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label9 = new JLabel();
        label9.setText("Hausnummerzusatz:");
        adressPanel.add(label9, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Straße*:");
        adressPanel.add(label10, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JLabel label11 = new JLabel();
        label11.setText("Hausnummer*: ");
        adressPanel.add(label11, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(112, 16), null, 1, false));
        hausnummerField = new JTextField();
        hausnummerField.setText("");
        adressPanel.add(hausnummerField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        strasseField = new JTextField();
        adressPanel.add(strasseField, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label12 = new JLabel();
        label12.setText("Land*:");
        adressPanel.add(label12, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        landField = new JTextField();
        adressPanel.add(landField, new GridConstraints(3, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label13 = new JLabel();
        label13.setText("Postleitzahl*:");
        adressPanel.add(label13, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(112, 16), null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("Bundesland*:");
        adressPanel.add(label14, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        adressPanel.add(panel2, new GridConstraints(0, 5, 4, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        plzField = new JTextField();
        adressPanel.add(plzField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        bundeslandField = new JTextField();
        adressPanel.add(bundeslandField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        hausnummerZusatzField = new JTextField();
        adressPanel.add(hausnummerZusatzField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        jobInfoPanel = new JPanel();
        jobInfoPanel.setLayout(new GridLayoutManager(3, 9, new Insets(0, 0, 0, 0), -1, -1));
        jobInfoPanel.setBackground(new Color(-1));
        main.add(jobInfoPanel, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jobInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label15 = new JLabel();
        label15.setText("Berufsbezeichnung*: ");
        jobInfoPanel.add(label15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        jobnameField = new JTextField();
        jobInfoPanel.add(jobnameField, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        standortField = new JTextField();
        jobInfoPanel.add(standortField, new GridConstraints(2, 3, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        abteilungField = new JTextField();
        abteilungField.setText("");
        jobInfoPanel.add(abteilungField, new GridConstraints(1, 3, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label16 = new JLabel();
        label16.setText("Abteilungsleiter:");
        jobInfoPanel.add(label16, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        abteilungsLeiterField = new JTextField();
        jobInfoPanel.add(abteilungsLeiterField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label17 = new JLabel();
        label17.setText("Raum:");
        jobInfoPanel.add(label17, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JLabel label18 = new JLabel();
        label18.setText("Abteilung*:");
        jobInfoPanel.add(label18, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        raumField = new JTextField();
        jobInfoPanel.add(raumField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label19 = new JLabel();
        label19.setText("Standort*: ");
        jobInfoPanel.add(label19, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        jobInfoPanel.add(panel3, new GridConstraints(0, 7, 3, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setText("Beschäftigungsgrad in %*:");
        jobInfoPanel.add(label20, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        beschaeftigungField = new JTextField();
        beschaeftigungField.setText("");
        jobInfoPanel.add(beschaeftigungField, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        button1 = new JButton();
        button1.setBackground(new Color(-1));
        button1.setForeground(new Color(-16446928));
        button1.setText("Button");
        main.add(button1, new GridConstraints(10, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        abbrechenButton = new JButton();
        abbrechenButton.setBackground(new Color(-1));
        abbrechenButton.setForeground(new Color(-16446928));
        abbrechenButton.setHideActionText(false);
        abbrechenButton.setText("Abbrechen");
        main.add(abbrechenButton, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        alleEingabenLoeschenButton = new JButton();
        alleEingabenLoeschenButton.setBackground(new Color(-1));
        alleEingabenLoeschenButton.setForeground(new Color(-16446928));
        alleEingabenLoeschenButton.setText("Alle Eingaben loeschen");
        main.add(alleEingabenLoeschenButton, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        aenderungenUebernehmenButton = new JButton();
        aenderungenUebernehmenButton.setBackground(new Color(-1));
        aenderungenUebernehmenButton.setForeground(new Color(-16446928));
        aenderungenUebernehmenButton.setText("Aenderungen uebernehmen");
        main.add(aenderungenUebernehmenButton, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-16446928));
        main.add(panel4, new GridConstraints(1, 0, 10, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setBackground(new Color(-16446928));
        main.add(panel5, new GridConstraints(1, 4, 10, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        logoIconRight = new JLabel();
        logoIconRight.setText("");
        panel5.add(logoIconRight, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setBackground(new Color(-1));
        main.add(panel6, new GridConstraints(7, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setText("Letzte Änderung:");
        panel6.add(label21, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        label22.setText("Erstellt:");
        panel6.add(label22, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        erstelltDate = new JLabel();
        erstelltDate.setText("");
        panel6.add(erstelltDate, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
        final Spacer spacer1 = new Spacer();
        panel6.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        geandertDate = new JLabel();
        geandertDate.setText("");
        panel6.add(geandertDate, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
        mitarbeiterField = new JLabel();
        Font mitarbeiterFieldFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 28, mitarbeiterField.getFont());
        if (mitarbeiterFieldFont != null) mitarbeiterField.setFont(mitarbeiterFieldFont);
        mitarbeiterField.setForeground(new Color(-1));
        mitarbeiterField.setText("");
        main.add(mitarbeiterField, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logoIconLeft = new JLabel();
        logoIconLeft.setText("");
        main.add(logoIconLeft, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.setBackground(new Color(-16446928));
        main.add(panel7, new GridConstraints(11, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        HRMitarbeiterErstellenButton = new JButton();
        HRMitarbeiterErstellenButton.setBackground(new Color(-1));
        HRMitarbeiterErstellenButton.setForeground(new Color(-16446928));
        HRMitarbeiterErstellenButton.setText("HR-Mitarbeiter erstellen");
        main.add(HRMitarbeiterErstellenButton, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        personalakteLoeschenButton = new JButton();
        personalakteLoeschenButton.setBackground(new Color(-1));
        personalakteLoeschenButton.setForeground(new Color(-16446928));
        personalakteLoeschenButton.setText("Personalakte löschen");
        main.add(personalakteLoeschenButton, new GridConstraints(12, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel8.setBackground(new Color(-16446928));
        main.add(panel8, new GridConstraints(13, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        main.add(panel9, new GridConstraints(6, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        setAnlagenButton = new JButton();
        setAnlagenButton.setBackground(new Color(-1));
        setAnlagenButton.setText("Anlagen anfügen");
        panel9.add(setAnlagenButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fileTree = new JTree();
        panel9.add(fileTree, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
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
