package GUI.ProofServices;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaticInputProof {

    public boolean comboBoxFieldisEmpty(JComboBox comboBox, JLabel label){

        if(comboBox.getSelectedIndex() == 0){

            comboBox.setBorder(new LineBorder(Color.red));
            comboBox.setToolTipText("Falsche Eingabe");

            comboBox.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    comboBox.setSelectedIndex(0);

                    if(MouseEvent.MOUSE_CLICKED == 500){
                        comboBox.setBorder(LineBorder.createGrayLineBorder());
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    comboBox.removeFocusListener(this);
                }
            });
            return true;
        }

        return false;
    }
    public boolean dateValide(JTextField field){


        String input = field.getText();

        String  regex       = "([0-9]+(\\.[0-9]+)+)";
        Pattern pattern     = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher     = pattern.matcher(input);

        if(!matcher.find()){
            field.setToolTipText("Datum ungültig");
            field.setBorder(new LineBorder(Color.red));

            addAndRemoveFocusListener(field);

            return false;
        }


        return true;
    }

    public boolean mailValide(JTextField field){

        String input = field.getText();

        String  regex       = "[A-Z0-9._%+-]+@[A-Z0-9.-]+[.A-Z]{2,6}";
        Pattern pattern     = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher     = pattern.matcher(input);

        if(!matcher.find()){
            field.setToolTipText("Keine gueltige E-Mail-Adresse");
            field.setBorder(new LineBorder(Color.red));

            addAndRemoveFocusListener(field);

            return false;
        }
        return true;
    }

    public boolean telefonValide(JTextField field){

        String input = field.getText();

        String regex    = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        if(!matcher.find()){
            field.setToolTipText("Keine gueltige Telefonnummer");
            field.setBorder(new LineBorder(Color.red));

            addAndRemoveFocusListener(field);

            return false;
        }

        return true;
    }

    public boolean inputNotNull(ArrayList<JTextField> list){

        ArrayList<Integer> notNullConfirm = new ArrayList<>();
        int counter = 0;

            for (JTextField k : list) {

                if(k.getText().isEmpty()){
                    fieldEmpty(k);

                    notNullConfirm.add(counter,0);
                    counter++;
                }
            }

            if(notNullConfirm.isEmpty()){
                return false;
            }
        return true;
    }

    private void fieldEmpty(JTextField k){

        // True: Fehlermeldung wird geworfen
        k.setToolTipText("Bitte nehmen Sie eine Eingabe vor!");        // Fehlermeldung = "Notwendige Angabe fehlt"
        k.setBorder(new LineBorder(Color.red));

        addAndRemoveFocusListener(k);
    }

    private void addAndRemoveFocusListener(JTextField field){

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setText(null);
                resetBorderColor(field);
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.removeFocusListener(this);
            }
        });
    }

    private void resetBorderColor(JTextField field){

        if(KeyEvent.VK_DELETE == 127 || KeyEvent.KEY_TYPED == 400){

            field.setBorder(LineBorder.createGrayLineBorder());
            field.setToolTipText(null);
        }

    }
}
