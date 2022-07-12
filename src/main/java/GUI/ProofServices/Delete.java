package GUI.ProofServices;

import javax.swing.*;
import java.util.ArrayList;

public class Delete {

    public void setListNull (ArrayList<JTextField> list){

        for (JTextField k : list) {

            k.setText(null);
        }
    }
    public void setComboBoxNull (JComboBox box){
        box.setSelectedIndex(0);
    }
}
