package controller.attachments.filedir;

import java.io.File;

public class FileNode {
    /*
    source:
    https://stackoverflow.com/questions/60234270/how-to-display-full-file-path-in-jtree
     */
    private File file;

    public FileNode(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        String name = file.getName();
        if (name.equals("")) {
            return file.getAbsolutePath();
        } else {
            return name;
        }
    }
}
