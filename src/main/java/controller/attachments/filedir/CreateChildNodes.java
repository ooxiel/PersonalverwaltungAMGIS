package controller.attachments.filedir;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class CreateChildNodes implements Runnable {
    /*
    source:
    https://stackoverflow.com/questions/60234270/how-to-display-full-file-path-in-jtree
    */
    private DefaultMutableTreeNode root;
    private File fileRoot;

    public CreateChildNodes(File fileRoot, DefaultMutableTreeNode root) {
        this.fileRoot = fileRoot;
        this.root = root;
    }

    public void run() {
        createChildren(fileRoot, root);
    }

    private void createChildren(File fileRoot, DefaultMutableTreeNode node) {
        File[] files = fileRoot.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file));

            node.add(childNode);
            if (file.isDirectory()) {
                createChildren(file, childNode);
            }
        }
    }
}