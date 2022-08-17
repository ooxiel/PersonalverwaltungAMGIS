package controller.appearance;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class IconDesign {
    //setIcon bekommt den aktuellen frame, das Label in dem das Bild angezeigt wird und den Path von dem Image übergeben
    public void setIcon(JFrame frame, JLabel label, String path){

        Image image;
        ImageIcon icon;
        image = null;
        try{//Versuchen das Image mit dem Path zu lesen und an die Variable zu übergeben
            image = ImageIO.read(new File(path));
        }catch (IOException ex){
            ex.printStackTrace();
            frame.setUndecorated(true);
        }
        //Image wird dann zu einem ImageIcon umgewandelt
        icon = new ImageIcon(image);
        //Das Label wird mit dem neuen Image aktualisiert
        label.setIcon(icon);
    }
}
