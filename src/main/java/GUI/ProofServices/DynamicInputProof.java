package GUI.ProofServices;

import org.apache.poi.ss.util.DateFormatConverter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class DynamicInputProof {

    public void setAmountofCharacterAllowed (JTextField field, int amount){

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                char input = e.getKeyChar();

                // Realisierung Datumformat

                if (field.getText().length() < amount || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE) || (input == KeyEvent.VK_ENTER)){
                    field.setEditable(true);
                    field.setBorder(LineBorder.createGrayLineBorder());
                }else{
                    field.setEditable(false);
                    field.setBorder(new LineBorder(Color.red));
                }
            }
        });
    }


    public void dateField(JTextField field){

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                char input = e.getKeyChar();


                // Realisierung Datumformat

                if (!Character.isDigit(input) || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE) || (input == KeyEvent.VK_ENTER)) {
                    e.consume();

                }

                if(field.getText().length() == 2 || field.getText().length() == 5){
                    e.setKeyChar('.');  // 3. und 6. Input wird auf einen "." gesetzt

                }

                // Es können in diesem Textfeld nur Änderungen bis 10 Zeichen vorgenommen werden, ausgenommen davon sind Back-Space, Delete und Enter
                // Falls die 10 Zeichen überschritten werden, wird das Textfeld gesperrt
                // Wenn nicht, Textfeld offen

                if(field.getText().length() >= 10 && ((input != KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE) || (input == KeyEvent.VK_ENTER))){

                    field.setEditable(false);

                }else{
                    field.setEditable(true);
                }
            }
        });
    }

    public void telefonField(JTextField field) {

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                char input = e.getKeyChar();


                // Realisierung Datumformat

                if (Character.isLetter(input) || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE) || (input == KeyEvent.VK_ENTER)) {
                    e.consume();
                }

                if(field.getText().length() == 0){
                    e.setKeyChar('+');
                }

                if(field.getText().length() == 3 || field.getText().length() == 7){
                    e.setKeyChar(' ');  // 3. und 6. Input wird auf einen "." gesetzt
                }


                if (field.getText().length() > 15 && ((input != KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE) || (input == KeyEvent.VK_ENTER))) {

                    field.setEditable(false);

                } else {
                    field.setEditable(true);
                }
            }
        });
    }

    public void onlyLetterField(ArrayList<JTextField> list){

        for (JTextField k : list) {
            k.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);

                    char input = e.getKeyChar();

                    if(!Character.isLetter(input) || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE || (input == KeyEvent.VK_ENTER))){
                        e.consume();
                    }
                }
            });
        }

    }

    public void onlyNumberField(ArrayList<JTextField> list){

        for (JTextField k : list) {
            k.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);

                    char input = e.getKeyChar();

                    if(!Character.isDigit(input) || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE) || (input == KeyEvent.VK_ENTER)) {

                        e.consume();
                    }
                }
            });
        }
    }
}
