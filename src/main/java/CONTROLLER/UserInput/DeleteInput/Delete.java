package CONTROLLER.UserInput.DeleteInput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Delete {

    public void all(JButton button, ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars, JTextField raumField, JComboBox geschlecht){

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setListNull(optionalInput);
                setListNull(lettersOnly);
                setListNull(numbersOnly);
                setListNull(specialChars);

                setFieldNull(raumField);
                setComboBoxNull(geschlecht);
            }
        });
    }
    private void setFieldNull (JTextField field){
        field.setText(null);
    }

    private void setListNull (ArrayList<JTextField> list){

        for (JTextField k : list) {

            k.setText(null);
        }
    }

    private void setComboBoxNull (JComboBox box){
        box.setSelectedIndex(0);
    }
}
