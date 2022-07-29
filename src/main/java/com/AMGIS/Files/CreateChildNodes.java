package com.AMGIS.Files;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class CreateChildNodes implements Runnable {

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
