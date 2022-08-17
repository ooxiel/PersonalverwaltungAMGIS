package controller.userinput.deleteinput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteUserInput {

    //Gesamte Eingabe wird gelöscht
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
    //Feld leeren
    private void setFieldNull (JTextField field){
        field.setText(null);
    }

    //erstellte ArrayList leeren
    private void setListNull (ArrayList<JTextField> list){

        for (JTextField k : list) {

            k.setText(null);
        }
    }
    //DropDownList auf einen leeren Wert setzen
    private void setComboBoxNull (JComboBox box){
        box.setSelectedIndex(0);
    }
}
