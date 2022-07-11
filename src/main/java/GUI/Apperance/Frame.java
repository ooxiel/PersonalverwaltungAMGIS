package GUI.Apperance;

import java.awt.*;

public class Frame {

    /**
     * Zentrierung der Forms
     *
     * @param widthLayout
     * @param heightLayout
     * @return
     */

    public Point center(int widthLayout, int heightLayout){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int widthScreen = (int) screenSize.getWidth();              // Monitoraufloesung in horizontale Richtung wird in Variable gespeichert
        int heightScreen = (int) screenSize.getHeight();            // Monitoraufloesung in vertikale Richtung wird in Variable gespeichert

        int widthLayoutPosition = (widthScreen - widthLayout)/2;        // Ermittlung der horizontalen Position des Login-Panel
        int heigtLayoutPosition = (heightScreen - heightLayout)/2;       // Ermittlung der vertikalen Position des Login-Panel

        return new Point(widthLayoutPosition, heigtLayoutPosition);     // Login-Panel wird in Abhaengigkeit von Monitorauflösung zentriert angezeigt
    }
}
