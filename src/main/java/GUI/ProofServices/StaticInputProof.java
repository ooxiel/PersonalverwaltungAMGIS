package GUI.ProofServices;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaticInputProof {

    public void comboBoxFieldEmpty(JComboBox comboBox, JLabel label){

        if(comboBox.getSelectedIndex() == 0){
            label.setVisible(true);
            label.setText("Keine Anrede festgelegt!");
        }else{
            label.setVisible(true);
        }
    }

    public void mailValide(JTextField field){

        String input = field.getText();

        String  regex       = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+[.A-Z]{2,6}$";
        Pattern pattern     = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher     = pattern.matcher(input);

        if(!matcher.find()){
            field.setText("Keine gueltige E-Mail-Adresse");
        }

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.removeFocusListener(this);
            }
        });
    }

    public void telefonValide(JTextField field){

        String input = field.getText();

        String regex    = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        if(!matcher.find()){
            field.setText("Keine gueltige Telefonnummer");
        }

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.removeFocusListener(this);
            }
        });

    }

    public void inputNotNull(ArrayList<JTextField> list){

        for (JTextField k : list) {

            if(k.getText().isEmpty()){
                fieldEmpty(k);
            }
        }
    }

    private void fieldEmpty(JTextField k){

        // True: Fehlermeldung wird geworfen
        k.setText("Bitte nehmen Sie eine Eingabe vor!");        // Fehlermeldung = "Notwendige Angabe fehlt"

        k.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                k.setText(null);                        // Fehlermeldung wird gelöscht, sobald das Feld angeklickt wird
            }

            @Override
            public void focusLost(FocusEvent e) {
                k.removeFocusListener(this);          // Sobald eine neue Eingabe erfolgt ist, kann diese "normal" bearbeteitet werden, ohne, dass das komplette Field gelöscht wird

            }
        });
    }


}
