package CONTROLLER.AdditionalDesignElements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class IconDesign {

    public void setIcon(JFrame frame, JLabel label, String path){

        Image image;
        ImageIcon icon;

        image = null;
        try{
            image = ImageIO.read(new File(path));
        }catch (IOException ex){
            ex.printStackTrace();
            frame.setUndecorated(true);
        }

        icon = new ImageIcon(image);
        label.setIcon(icon);
    }
}
