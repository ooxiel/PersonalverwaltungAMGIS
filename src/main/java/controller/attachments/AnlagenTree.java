package controller.attachments;

import controller.attachments.filedir.CreateChildNodes;
import controller.attachments.filedir.FileNode;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/** ====================================================================================================================
 * AnlagenTree ist eine Klasse, welche allgemein genutzt wird, um Anhaenge in Personalakten anzuzeigen, zu oeffnen und
 * zu loeschen.
 * AnlagenTree ist so konzipiert, dass ein originaler Systemdateipfad mit eines JTrees gespiegelt wird.
 * =====================================================================================================================
 */
public class AnlagenTree {

    /** ================================================================================================================
     *  Die 'show Methode wird allgemein genutzt um einen aus dem System gespiegelten JTree anzuzeigen.
     *
     * @param fileTree      Darstellung des File-Systems im Programm
     * @param main          Anzeigeinhalte
     * @param id            Personal-ID
     * @param caller        Abfrage des Methodenausrufers, um verschiedene Funktion freizuschalten
     */
    public void show(JTree fileTree, JPanel main, String id, String caller){

        File fileRoot = idIsEmpty(id);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        DefaultTreeModel treeModel = new DefaultTreeModel(root);

        fileTree.setModel(treeModel);
        fileTree.putClientProperty("JTree.lineStyle","Angled");
        fileTree.setShowsRootHandles(true);
        fileTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent event) {

                try{
                    DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) fileTree.getSelectionModel().getSelectionPath().getLastPathComponent();
                    FileNode fileNode = (FileNode) dmtn.getUserObject();
                    File file = fileNode.getFile();

                    OpenAndDeleteAttachements(fileTree,main, file, id, caller);

                }catch (NullPointerException ex){
                    ex.getMessage();
                }
            }
        });

        CreateChildNodes ccn = new CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();
    }

    /** ================================================================================================================
     *  Mit der 'addAttachements'-Methode wird ein Dialoagfeld geoeffnet, welches den Dateiexplorer des ausfuehrenden
     *  Geraetes anzeigt. Aus diesem koennen entsprechenden Datei an eine Personalakte angehangen werden.
     *
     *  Die Dateien werden dabei aus dem System in den "Pending"-Ordner kopiert. Sobald die Aenderungen uebernommen wurden,
     *  werden die Dateien anschliessend aus diesem Ordner, beim Abbruch wieder geloescht oder bei Erfolg in einen
     *  Personalakten-verknuepften Ordner verschoben.
     *
     * @param anlage        Button, um Anlangen hinzuzufuegen
     * @param fileTree      Darstellung des File-Systems im Programm
     * @param main          angezeigte Inhalte
     * @param id            Personal-ID
     * @param caller        Abfrage des Methodenausrufers, um verschiedene Funktion freizuschalten
     */

    public void addAttachements(JButton anlage, JTree fileTree, JPanel main, String id, String caller){

        anlage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame dirFrame = new JFrame();                                    // Es wird ein neuer Frame erstellt, um den Dateiexplorer darzustellen
                String documents = System.getenv("DOCUMENTS");               // systemabhaengige Variable, welche den Ausgangspunkt des Dateiexplorers darstellt
                JFileChooser fileChooser = new JFileChooser(documents);            // systemabhaengige Variable wird mit Java integierten FileChooser verknuepft

                int select = fileChooser.showOpenDialog(dirFrame);                  // Pop-Up-Fenster mit Dateiexplorer wird geoeffnet

                /*
                    select wurde initialisiert, um festzustellen, ob eine Datei ausgewaehlt wurde oder ensprechend nicht

                 */

                if (select == JFileChooser.APPROVE_OPTION) {

                    File fileSelected = fileChooser.getSelectedFile();              // fileSelected enthaelt den Dateiexplorer ausgewaehlten File
                    Path newDIR = idIsEmpty(id).toPath();                           // Ueberpruefung, bereits ein Ordner fuer entsprechende Personal-ID besteht

                        /*
                            File wird zum Pfad newDIR kopiert, falls dort eine identische Datei bereits existieren sollte, wird diese ersetzt
                            Anschliessend wird diese ueber die Methode 'show' wieder im JTree angezeigt
                         */

                        try {
                            Files.copy(Path.of(fileSelected.getAbsolutePath()), newDIR.resolve(fileSelected.getName()), REPLACE_EXISTING);
                            show(fileTree, main, id, caller);

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                }
            }
        });
    }

    /** ================================================================================================================
     * 'OpenAndDeleteAttachments' wird genutzt, um ueber einen einfach Rechtsklick ein Menu zu oeffnen, ueber welches die
     *  angezeigten Dateien entweder geoeffnet oder auch geloescht werden koennen.
     *
     *  Der Funktionsumfang ist dabei abhaengig von der Rolle. Diese wird durch den caller bestimmt:
     *
     *  - Mitarbeiter   -> open
     *  - HR            -> open, delete
     *  - Root          -> open, delete
     *
     * @param fileTree      Darstellung des File-Systems im Programm
     * @param main          angezeigte Inhalte
     * @param file          Datei, welche geoeffnet oder geloeschet werden soll
     * @param id            Personal-ID
     * @param caller        Abfrage des Methodenausrufers, um verschiedene Funktion freizuschalten
     */
    private void OpenAndDeleteAttachements(JTree fileTree, JPanel main, File file, String id, String caller){

        JPopupMenu pop = new JPopupMenu();                  // Menu, welches ueber einen Rechtsklick aufgerufen werden kann
            JMenuItem open = new JMenuItem("Open");     // Menu-Gegenstand "Open" wird generiert

            /*
                Dem MenuItem "Open" wird ein Aktionslistener hinzugefuegt. Als Aktion ist implementiert, dass die
                ausgewaehlte Datei geoeffnet werden kann, wenn diese
                a) vorhanden ist
                b) ein File ist
                c) und eine entsprechend Software zum oeffnen des jeweiligen Dateityps vorhanden ist

                Anderenfalls kann die Datei nicht geoeffnet werden und der User erhaelt eine Pop-Up-Meldung.
             */
            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                        try {
                            if (file.exists() && file.isFile() && Desktop.isDesktopSupported()) {
                                Desktop.getDesktop().open(file);
                            } else {
                                JOptionPane.showMessageDialog(main, "Datei kann nicht geoeffnet werden.");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
            });
            pop.add(open);

            if(caller.equals("HR")){
                JMenuItem delete = new JMenuItem("Delete");     // Fuer HR und Root-User wird neben der "Open"-Option ebenfalls die Funktion Loeschen hinzugefuegts

                /*
                    Jede hinzugefuegte Datei wird neben der klassischen Speicherung in einem Ordner ebenfalls in der
                    Datenbank gespeichert. In der Datenbank wird jeweils der Dateipfad und die Personal-ID gespeichert.
                    Wenn eine Datei geloescht wird im Programm, wird der Pfad ebenfalls aus der Datenbank geloescht.

                    Anschliessend wird der JTree ueber 'show' wieder aktualisiert.
                 */

                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if(file.exists() && file.isFile()){
                            try {Class.forName("org.hsqldb.jdbcDriver");}catch(ClassNotFoundException ee) {return;}
                            try {
                                Connection con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis");
                                System.out.println("src/main/resources/AktenFiles/"+id+"/"+file.getName());
                                String sql = "DELETE FROM Aktenkennzeichen WHERE Dateipfad='src\\main\\resources\\AktenFiles\\"+id+"\\"+file.getName()+"'";
                                Statement stmt = con.createStatement();
                                stmt.executeQuery(sql);
                                stmt.close();
                            }catch(SQLException eee){eee.printStackTrace();}

                            file.delete();                      // File wird aus dem Systemordner geloescht
                            show(fileTree, main, id, caller);   // JTree wird aktualisiert
                        }
                    }
                });
                pop.add(delete);
            }

                /*
                     MouseListener ist ein Java-eigenes Interfaces, weshalb alle unten stehenden Methoden implementiert
                     werden mussten
                 */
                fileTree.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    /*
                        Im JTree wird bei Auswahl eines Elements ueber einen Rechtsklick das Menu geoeffnet
                     */

                    @Override
                    public void mousePressed(MouseEvent e) {

                        if(SwingUtilities.isRightMouseButton(e)){
                            pop.show(fileTree, e.getX(), e.getY());
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

            }

    /** ================================================================================================================
     *  Mit der idIsEmpty-Methode wird ueberprueft, ob fuer eine Personalakte bereits eine ID festgelegt wurde.
     *  Hierfuer wird Personal-ID uebergeben.
     *
     *      id != null -> Es wird ein neuer Ordner erstellt mit der Personal-ID als Namen
     *      id == null -> Datei wird zunaechst im 'Pending' Ordner abgelegt
     *
     * @param id    Personal-ID
     * @return      Pfad fuer den Ordner, in welchem die Dateien abgelegt werden sollen
     */

    private File idIsEmpty(String id){

        if(id != null){
            return new File("src/main/resources/AktenFiles/"+id);
        }else{
            return new File("src/main/resources/AktenFiles/Pending");
        }
    }
}

