package controller.userinput.checkinput;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StaticInputProof {

    public boolean comboBoxFieldisEmpty(JComboBox comboBox){

        if(comboBox.getSelectedIndex() == 0){

            comboBox.setToolTipText("Bitte nehmen Sie eine Eingabe vor!");
            comboBox.setBorder(new LineBorder(Color.red));
            ToolTipManager.sharedInstance().setInitialDelay(0);

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

    public boolean dateValid (JTextField field) {

        String input = field.getText();

            char[] inputs = input.toCharArray();
            char[] outputs = new char[8];
            int counter = 0;

                for (char k : inputs) {

                    if (k != '.') {
                        outputs[counter] = k;
                        counter++;
                    }
                }

            String outputDay = String.copyValueOf(outputs,0,2);
            String outputMonth = String.copyValueOf(outputs,2,2);
            String outputYear = String.copyValueOf(outputs,4,4);

            int day;
            int month;
            int year;

            try {

                day     = Integer.parseInt(outputDay);
                month   = Integer.parseInt(outputMonth);
                year    = Integer.parseInt(outputYear);

            } catch (NumberFormatException e) {

                addAndRemoveFocusListener(field);
                setBorderColorAndToolTip(field,"Datum ungültig");

                return false;
            }

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setLenient(false);

            if(LocalDate.now().getYear() < year){

                addAndRemoveFocusListener(field);
                setBorderColorAndToolTip(field,"Datum ungültig");

                return false;

            }else{
                gregorianCalendar.set(year,month-1,day);

                try{
                    gregorianCalendar.getTime();

                }catch(IllegalArgumentException e){

                    addAndRemoveFocusListener(field);
                    setBorderColorAndToolTip(field,"Datum ungültig");

                    return false;
                }

            return true;
            }
    }

    public boolean mailValide(JTextField field){

        String input = field.getText();

        String  regex       = "[A-Z0-9._%+-]+@[A-Z0-9.-]+[.A-Z]{2,6}";
        Pattern pattern     = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher     = pattern.matcher(input);

        if(!matcher.find()){

            addAndRemoveFocusListener(field);
            setBorderColorAndToolTip(field,"Keine gueltige E-Mail-Adresse!");

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

            setBorderColorAndToolTip(field,"Keine gueltige Telefonnummer!");
            addAndRemoveFocusListener(field);

            return false;
        }

        return true;
    }

    public void setMaxInteger (JTextField field, int max){

        int input;
        try{
            input = Integer.parseInt(field.getText());
        }catch (NumberFormatException e){
            input = 0;
        }

        if (input > max) {
            field.setText(Integer.toString(max));
        }
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

        setBorderColorAndToolTip(k,"Bitte nehmen Sie eine Eingabe vor!");
        addAndRemoveFocusListener(k);
    }

    private void addAndRemoveFocusListener(JTextField field){

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setText(null);
                resetBorderColorAndToolTip(field);
                field.setEditable(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.removeFocusListener(this);
            }
        });
    }

    private void resetBorderColorAndToolTip(JTextField field){

        if(KeyEvent.VK_DELETE == 127 || KeyEvent.KEY_TYPED == 400){

            field.setBorder(LineBorder.createGrayLineBorder());
            field.setToolTipText(null);
        }

    }
    private void setBorderColorAndToolTip (JTextField field, String text){

        field.setToolTipText(text);
        field.setBorder(new LineBorder(Color.red));
        ToolTipManager.sharedInstance().setInitialDelay(0);
    }
}
