package CONTROLLER.Appearance;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

public class FilterDesign {

    public void changeBorderLook(JComboBox geschlecht, JTextField nameField, JTextField vornameField, JTextField jobnameField, JTextField abteilungField, JTextField standortFeild){


        Border border = new BevelBorder(0, Color.white, Color.decode("#050a30"));

        geschlecht.setBorder(border);
        nameField.setBorder(border);
        vornameField.setBorder(border);
        jobnameField.setBorder(border);
        abteilungField.setBorder(border);
        standortFeild.setBorder(border);
    }
}
