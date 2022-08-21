package controller.attachments;


import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/** ====================================================================================================================
 * PendingDirectory ist eine Klass, welche ausschliesslich fuer Interaktionen mit dem File-System erstellt wurden.
 * Pending ist dabei ein Ordner, welcher als Zwischenspeicher genutzt wird.
 * =====================================================================================================================
 */

public class PendingDirectory {

    //Wenn beim Erstellen oder Bearbeiten einer Personalakte das Fenster geschlossen/abgebrochen wird, wird der Pending Ordner geleert

    /** ====================================================================================================================
     * 'clearOnClose' dient dazu, wenn die Erstellung oder Bearbeitung einer Personalakte über den Button abbrechen oder
     * durch das schliessen der Form beendet wird, wird mit dieser Methode ebenfalls der Pending-Ordner geleert.
     * Dadurch kann die Dateienorganisation gewaehrleistet werden.
     *
     * @param frame     Anzeigerahmen
     * @param button    Abbrechen-Button
     */
    public void clearOnClose(JFrame frame, JButton button){

        /*
            Pending wird geleert, wenn das Fenster geschlossen wird
         */
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                File dir = new File("src/main/resources/AktenFiles/Pending/");  // Initialisierung des Pending-Ordners in der Variable 'dir'

                try {                                                                   // Exception-Handling, da auch keine Datei im Ordner vorhanden sein können
                    FileUtils.cleanDirectory(dir);                                      //Alle Datei aus dem Pending-Ordner werden geloeescht
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /*
            Pending wird geleert, wenn der Abbrechen-Button betaetigt wird
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                File dir = new File("src/main/resources/AktenFiles/Pending/");  // Initialisierung des Pending-Ordners in der Variable 'dir'

                try {                                                                   // Exception-Handling, da auch keine Datei im Ordner vorhanden sein können
                    FileUtils.cleanDirectory(dir);                                      //Alle Datei aus dem Pending-Ordner werden geloeescht
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
