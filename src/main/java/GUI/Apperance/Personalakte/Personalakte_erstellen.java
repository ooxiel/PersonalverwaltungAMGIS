package GUI.Apperance.Personalakte;


import com.AMGIS.Files.Anlagen;
import com.AMGIS.Files.CreateChildNodes;
import com.AMGIS.Files.FileNode;
import com.AMGIS.Services.InputCheck.Delete;
import com.AMGIS.Services.InputCheck.DynamicInputProof;
import com.AMGIS.Services.InputCheck.StaticInputProof;
import com.AMGIS.Data_Handling.PA_erstellen;
import com.AMGIS.Files.AnlagenTableModel;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Personalakte_erstellen extends JFrame {

    // Main

    private JPanel main;
    private JButton abbrechenButton;
    private JButton personalakteErstellenButton;
    private JButton alleEingabenLoeschenButton;

    // Panels

    private JPanel personalInfoPanel;

    private JComboBox geschlecht;
    private JTextField zweitNameField;
    private JTextField vornameField;
    private JTextField geburstagField;
    private JTextField telefonField;
    private JTextField emailField;
    private JTextField nameField;

    private JPanel adressPanel;
    private JTextField strasseField;
    private JTextField hausnummerField;
    private JTextField hausnummerZusatzField;
    private JTextField landField;
    private JTextField bundeslandField;
    private JTextField plzField;

    private JPanel jobInfoPanel;
    private JTextField jobnameField;
    private JTextField beschaeftigungField;
    private JTextField abteilungField;
    private JTextField abteilungsLeiterField;
    private JTextField raumField;
    private JTextField standortField;

    private JButton button1;
    private JLabel logoIconLeft;
    private JLabel logoIconRight;
    private JButton setAnlagenButton;
    private JTable anlagenTable;
    private JTree fileTree;

    public Personalakte_erstellen() {

        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();

        DynamicInputProof dynamicInput = new DynamicInputProof();
        StaticInputProof staticInput = new StaticInputProof();

        ArrayList<JTextField> optionalInput = new ArrayList<>();
        ArrayList<JTextField> lettersOnly = new ArrayList<>();
        ArrayList<JTextField> numbersOnly = new ArrayList<>();
        ArrayList<JTextField> specialChars = new ArrayList<>();

        show(frame);
        disposeButton(frame);
        deleteAll(optionalInput, lettersOnly, numbersOnly, specialChars);

        getAttachements();

        addOptionalInput(optionalInput);
        addLettersOnly(lettersOnly);
        addNumbersOnly(numbersOnly);
        addSpecialChars(specialChars);

        checkInputDynamicStandard(dynamicInput, optionalInput, lettersOnly, numbersOnly);
        checkInputDynamicSpecial(dynamicInput);

        userInputPruefungStatisch(frame, staticInput, lettersOnly, numbersOnly, specialChars);
    }



    /*
     *                      // GUI-Funktionen-Implementierung \\
     */

    private void show(JFrame frame) {

        frame.add(main);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                File dir = new File("src/main/resources/AktenFiles/Pending/");
                try {
                    FileUtils.cleanDirectory(dir);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // EIGNET SICH MÖGLICHERWEISE BESSER BEIM BEARBEITEN
        // https://www.youtube.com/watch?v=rhRcxwreeVc&t=4s

        File fileRoot = new File("src/main/resources/AktenFiles/Pending");
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

                System.out.println(file);
            }
        });

        CreateChildNodes ccn = new CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();

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
                    Path newDIR = Paths.get("src/main/resources/AktenFiles/Pending/");


                    try {
                        Files.copy(Path.of(fileSelected.getAbsolutePath()), newDIR.resolve(fileSelected.getName()));
                        updateFileTree();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


        Image logo_left = null;
        try {
            logo_left = ImageIO.read(new File("src/main/resources/icons/LogoKlein80x80.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
            frame.setUndecorated(true);
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

    private void updateFileTree() {
        File fileRoot = new File("src/main/resources/AktenFiles/Pending");
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


    private void disposeButton(JFrame frame) {

        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                File dir = new File("src/main/resources/AktenFiles/Pending/");
                try {
                    FileUtils.cleanDirectory(dir);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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

    }

    /*
     *                  // dynamische Feld-Ueberpruefungen-Implementierung \\
     */

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

    /*
     *                  // statische, finale Feld-Ueberpruefungen-Implementierung \\
     */

    private void userInputPruefungStatisch(JFrame frame, StaticInputProof staticInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars) {

        personalakteErstellenButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                staticInput.setMaxInteger(beschaeftigungField, 100);

                if (staticInput.inputNotNull(lettersOnly) &&
                        staticInput.inputNotNull(numbersOnly) &&
                        staticInput.inputNotNull(specialChars) &&
                        staticInput.comboBoxFieldisEmpty(geschlecht)) {

                    JOptionPane.showMessageDialog(main, "Es fehlen notwendige Eingaben!");

                } else {
                    createMitarbeiter(frame, staticInput);
                }

            }
        });
    }

    private void createMitarbeiter(JFrame frame, StaticInputProof staticInput) {

        boolean testGeburstag = staticInput.dateValid(geburstagField);
        boolean testTelefon = staticInput.telefonValide(telefonField);
        boolean testMail = staticInput.mailValide(emailField);

        if (testGeburstag && testTelefon && testMail) {

            PA_erstellen pae = new PA_erstellen();

            pae.einfuegenPA(geschlecht.getSelectedItem().toString(), vornameField.getText(), zweitNameField.getText(), nameField.getText(),
                    geburstagField.getText(), telefonField.getText(), emailField.getText(), strasseField.getText(), hausnummerField.getText(),
                    hausnummerZusatzField.getText(), landField.getText(), bundeslandField.getText(), plzField.getText(), jobnameField.getText(),
                    beschaeftigungField.getText(), abteilungField.getText(), abteilungsLeiterField.getText(), raumField.getText(), standortField.getText());
            //Feld in Personalakte erstellen erstellen und verbinden ;)


            //FELD MUSS NOCH EINGEFÜGT WERDEN
            //erstelltDate.setText(String.valueOf(LocalDateTime.now()));

            try {
                pae.con.close();
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
        main.setLayout(new GridLayoutManager(11, 5, new Insets(0, 0, 0, 0), -1, -1));
        main.setBackground(new Color(-16446928));
        personalInfoPanel = new JPanel();
        personalInfoPanel.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        personalInfoPanel.setBackground(new Color(-1));
        personalInfoPanel.setToolTipText("");
        main.add(personalInfoPanel, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        personalInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Name*:");
        personalInfoPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        nameField = new JTextField();
        nameField.setToolTipText("");
        personalInfoPanel.add(nameField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label2 = new JLabel();
        label2.setText("Vorname*: ");
        personalInfoPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        vornameField = new JTextField();
        personalInfoPanel.add(vornameField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label3 = new JLabel();
        label3.setText("Zweitname:");
        personalInfoPanel.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        zweitNameField = new JTextField();
        zweitNameField.setText("");
        personalInfoPanel.add(zweitNameField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label4 = new JLabel();
        label4.setText("E-Mail*:");
        personalInfoPanel.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        emailField = new JTextField();
        personalInfoPanel.add(emailField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label5 = new JLabel();
        label5.setText("Geburstag*:");
        personalInfoPanel.add(label5, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        geburstagField = new JTextField();
        personalInfoPanel.add(geburstagField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JLabel label6 = new JLabel();
        label6.setText("Telefon*:");
        personalInfoPanel.add(label6, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        telefonField = new JTextField();
        personalInfoPanel.add(telefonField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 6, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        personalInfoPanel.add(panel1, new GridConstraints(0, 2, 7, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setBackground(new Color(-16777216));
        label7.setText("Anrede*:");
        personalInfoPanel.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        geschlecht = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("");
        defaultComboBoxModel1.addElement("Herr");
        defaultComboBoxModel1.addElement("Frau");
        defaultComboBoxModel1.addElement("Divers");
        geschlecht.setModel(defaultComboBoxModel1);
        personalInfoPanel.add(geschlecht, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 6, false));
        adressPanel = new JPanel();
        adressPanel.setLayout(new GridLayoutManager(4, 6, new Insets(0, 0, 0, 0), -1, -1));
        adressPanel.setBackground(new Color(-1));
        main.add(adressPanel, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        adressPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label8 = new JLabel();
        label8.setText("Hausnummerzusatz:");
        adressPanel.add(label8, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Straße*:");
        adressPanel.add(label9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JLabel label10 = new JLabel();
        label10.setText("Hausnummer*: ");
        adressPanel.add(label10, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(112, 16), null, 1, false));
        hausnummerField = new JTextField();
        hausnummerField.setText("");
        adressPanel.add(hausnummerField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        strasseField = new JTextField();
        adressPanel.add(strasseField, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        hausnummerZusatzField = new JTextField();
        adressPanel.add(hausnummerZusatzField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label11 = new JLabel();
        label11.setText("Land*:");
        adressPanel.add(label11, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        landField = new JTextField();
        adressPanel.add(landField, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label12 = new JLabel();
        label12.setText("Postleitzahl*:");
        adressPanel.add(label12, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(112, 16), null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("Bundesland*:");
        adressPanel.add(label13, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        adressPanel.add(panel2, new GridConstraints(0, 4, 4, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        plzField = new JTextField();
        adressPanel.add(plzField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        bundeslandField = new JTextField();
        adressPanel.add(bundeslandField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        jobInfoPanel = new JPanel();
        jobInfoPanel.setLayout(new GridLayoutManager(3, 9, new Insets(0, 0, 0, 0), -1, -1));
        jobInfoPanel.setBackground(new Color(-1));
        main.add(jobInfoPanel, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jobInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label14 = new JLabel();
        label14.setText("Berufsbezeichnung*: ");
        jobInfoPanel.add(label14, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        jobnameField = new JTextField();
        jobInfoPanel.add(jobnameField, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        standortField = new JTextField();
        jobInfoPanel.add(standortField, new GridConstraints(2, 3, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        abteilungField = new JTextField();
        abteilungField.setText("");
        jobInfoPanel.add(abteilungField, new GridConstraints(1, 3, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label15 = new JLabel();
        label15.setText("Abteilungsleiter:");
        jobInfoPanel.add(label15, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        abteilungsLeiterField = new JTextField();
        jobInfoPanel.add(abteilungsLeiterField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label16 = new JLabel();
        label16.setText("Raum:");
        jobInfoPanel.add(label16, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JLabel label17 = new JLabel();
        label17.setText("Abteilung*:");
        jobInfoPanel.add(label17, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        raumField = new JTextField();
        jobInfoPanel.add(raumField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        final JLabel label18 = new JLabel();
        label18.setText("Standort*: ");
        jobInfoPanel.add(label18, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        jobInfoPanel.add(panel3, new GridConstraints(0, 7, 3, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        label19.setText("Beschäftigungsgrad in %*:");
        jobInfoPanel.add(label19, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        beschaeftigungField = new JTextField();
        beschaeftigungField.setText("");
        jobInfoPanel.add(beschaeftigungField, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        abbrechenButton = new JButton();
        abbrechenButton.setBackground(new Color(-1));
        abbrechenButton.setText("Abbrechen");
        main.add(abbrechenButton, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        alleEingabenLoeschenButton = new JButton();
        alleEingabenLoeschenButton.setBackground(new Color(-1));
        alleEingabenLoeschenButton.setText("Alle Eingaben loeschen");
        main.add(alleEingabenLoeschenButton, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        personalakteErstellenButton = new JButton();
        personalakteErstellenButton.setBackground(new Color(-1));
        personalakteErstellenButton.setText("Personalakte erstellen");
        main.add(personalakteErstellenButton, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        main.add(panel6, new GridConstraints(8, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setText("Letzte Änderung:");
        panel6.add(label20, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setText("Erstellt:");
        panel6.add(label21, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        label22.setText("");
        panel6.add(label22, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
        final Spacer spacer1 = new Spacer();
        panel6.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label23 = new JLabel();
        label23.setText("");
        panel6.add(label23, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
        final JLabel label24 = new JLabel();
        Font label24Font = this.$$$getFont$$$("Arial Black", Font.ITALIC, 28, label24.getFont());
        if (label24Font != null) label24.setFont(label24Font);
        label24.setForeground(new Color(-1));
        label24.setText("Personalakte erstellen");
        main.add(label24, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logoIconLeft = new JLabel();
        logoIconLeft.setText("");
        main.add(logoIconLeft, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        main.add(panel7, new GridConstraints(6, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        setAnlagenButton = new JButton();
        setAnlagenButton.setBackground(new Color(-1));
        setAnlagenButton.setText("Anlagen anfügen");
        panel7.add(setAnlagenButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fileTree = new JTree();
        panel7.add(fileTree, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
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
