package controller.appearance;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * =====================================================================================================================
 * IconDesign ist eine Klasse, welche ausschliesslich dem Design von GUI-Komponenten dient.
 *  ====================================================================================================================
 */
public class IconDesign {

    /** ================================================================================================================
     * 'setIcon' wird genutzt, um Bilddateien auszulesen und einem JLabel zuzuweisen. Dazu muss entsprechend der Frame,
     * das Label und der Pfad mit der Bilddatei uebergeben werden.
     *
     * @param frame     Anzeigerahmen
     * @param label     Darstellung des Image im Fenster
     * @param path      Pfad der Bilddatei
     */

    public void setIcon(JFrame frame, JLabel label, String path){

        // Variablen-Deklarierung

        Image image;
        ImageIcon icon;
        image = null;

        try{                                            // Exceptionhandling, da Pfad nicht erreichbar sein kann
            image = ImageIO.read(new File(path));       // Bilddatei wird mit Hilfe eines IO.readers ausgelesen
        }catch (IOException ex){
            ex.printStackTrace();
            frame.setUndecorated(true);                 // Alle Designelement werden vom Frame entfernt
        }

        icon = new ImageIcon(image);                    // Bild wird als Icon umgewandelt
        label.setIcon(icon);                            // Icon wird dem Label zugewiesen
    }
}
