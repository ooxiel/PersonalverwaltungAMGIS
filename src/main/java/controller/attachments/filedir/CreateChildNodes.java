package controller.attachments.filedir;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

/** ====================================================================================================================
 * Die Klasse CreateChildNodes wurde vollstaendig aus dem folgenden Internetbeitrag uebernommen
 *
 *  source: https://stackoverflow.com/questions/60234270/how-to-display-full-file-path-in-jtree
 *  ====================================================================================================================
 */
public class CreateChildNodes implements Runnable {

    private DefaultMutableTreeNode root;        // Varibalendeklaration eines DefaultMutableTreeNode
    private File fileRoot;                      // Variablendeklaration eines fileRoot vom Typ File

    /** ================================================================================================================
     * Kontruktor von CreateChildNodes
     *
     * @param fileRoot
     * @param root
     */
    public CreateChildNodes(File fileRoot, DefaultMutableTreeNode root) {
        this.fileRoot = fileRoot;
        this.root = root;
    }

    /** ================================================================================================================
     *  Implementierte Methode aus dem Runnable Interface
     */
    public void run() {
        createChildren(fileRoot, root);
    }

    /** ================================================================================================================
     * Methode erstellt Darstellung von Childs in einem Ordner
     *
     * @param fileRoot  Dateiursprung
     * @param node      Dateiknoten
     */

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
