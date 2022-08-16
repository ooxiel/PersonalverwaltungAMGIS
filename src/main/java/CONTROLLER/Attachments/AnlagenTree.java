package CONTROLLER.Attachments;

import CONTROLLER.Attachments.FileDir.CreateChildNodes;
import CONTROLLER.Attachments.FileDir.FileNode;

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

public class AnlagenTree {

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

    public void addAttachements(JButton anlage, JTree fileTree, JPanel main, String id, String caller){

        anlage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame dirFrame = new JFrame();
                String appdata = System.getenv("DOCUMENTS");
                JFileChooser fileChooser = new JFileChooser(appdata);

                int select = fileChooser.showOpenDialog(dirFrame);

                if (select == JFileChooser.APPROVE_OPTION) {

                    File fileSelected = fileChooser.getSelectedFile();
                    Path newDIR = idIsEmpty(id).toPath();

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

    private void OpenAndDeleteAttachements(JTree fileTree, JPanel main, File file, String id, String caller){

        JPopupMenu pop = new JPopupMenu();
            JMenuItem open = new JMenuItem("Open");
            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                        try {
                            if (file.exists() && file.isFile() && Desktop.isDesktopSupported()) {
                                Desktop.getDesktop().open(file);
                            } else {
                                JOptionPane.showMessageDialog(main, "Datei kann nicht geöffnet werden.");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
            });
            pop.add(open);

            if(caller.equals("HR")){
                JMenuItem delete = new JMenuItem("Delete");
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
                            file.delete();
                            show(fileTree, main, id, caller);
                        }
                    }
                });
                pop.add(delete);
            }

                fileTree.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

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

    private File idIsEmpty(String id){

        if(id != null){
            return new File("src/main/resources/AktenFiles/"+id);
        }else{
            return new File("src/main/resources/AktenFiles/Pending");
        }
    }
}

