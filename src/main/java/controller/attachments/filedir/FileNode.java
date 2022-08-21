package controller.attachments.filedir;

import java.io.File;

/** ====================================================================================================================
 * Die Klasse FileNode wurde vollstaendig aus dem folgenden Internetbeitrag uebernommen
 *
 *  source: https://stackoverflow.com/questions/60234270/how-to-display-full-file-path-in-jtree
 *
 *  ====================================================================================================================
 */
public class FileNode {

    private File file;              // Variablendeklaration

    /** ================================================================================================================
     * Kontruktor von FileNode
     *
     * @param file Datei
     */
    public FileNode(File file) {
        this.file = file;
    }

    /** ================================================================================================================
     * Getter-Methode
     *
     * @return File
     */
    public File getFile() {
        return file;
    }

    /** ================================================================================================================
     * Mit der toString Methode wird entweder der Dateiname oder der Dateipfad einer Datei wiedergeben
     *
     * @return String
     */
    @Override
    public String toString() {
        String name = file.getName();           // Filename wird in Datentyp String umgewandelt

        /*
            Wenn der Name des Files leer ist        -> wird der vollständige Dateipfad wiedergegeben
            Wenn der Name des Files nicht leer ist  -> wird nur der Dateiname wiedergeben
         */

        if (name.equals("")) {
            return file.getAbsolutePath();
        } else {
            return name;
        }
    }
}
