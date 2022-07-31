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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class AnlagenTree {

    public void show(JTree fileTree, JPanel main, String id){

        File fileRoot = idIsEmpty(id);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        DefaultTreeModel treeModel = new DefaultTreeModel(root);

        fileTree.setModel(treeModel);
        fileTree.setShowsRootHandles(true);
        fileTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent event) {

                DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) fileTree.getSelectionPath().getLastPathComponent();
                FileNode fileNode = (FileNode) dmtn.getUserObject();
                File file = fileNode.getFile();

                openAttachment(fileTree, main, file);
            }
        });

        CreateChildNodes ccn = new CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();
    }

    public void addAttachements(JButton anlage, JTree fileTree, JPanel main, String id){

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
                            show(fileTree, main, id);

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                }
            }
        });
    }

    public void deleteAttachements(JTree fileTree){

        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

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

    private void openAttachment(JTree fileTree, JPanel main, File file){

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
}
