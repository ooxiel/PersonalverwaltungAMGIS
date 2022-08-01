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

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class AnlagenTree {

    public void show(JTree fileTree, JPanel main, String id){

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

                    OpenAndDeleteAttachements(fileTree,main, file, id);

                }catch (NullPointerException ex){
                    ex.getMessage();
                }
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

    private void OpenAndDeleteAttachements(JTree fileTree, JPanel main, File file, String id){

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

            JMenuItem delete = new JMenuItem("Delete");
            delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            if(file.exists() && file.isFile()){
                file.delete();

                show(fileTree, main, id);
            }
            }
            });

            pop.add(open);
            pop.add(delete);

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


    public void clearAttachements(JTree fileTree, String id, JPanel main){
        
    }

    private File idIsEmpty(String id){

        if(id != null){
            return new File("src/main/resources/AktenFiles/"+id);
        }else{
            return new File("src/main/resources/AktenFiles/Pending");
        }
    }
}

