package GUI.ProofServices;

import org.apache.poi.ss.util.DateFormatConverter;
import org.jfree.chart.imagemap.ToolTipTagFragmentGenerator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.Robot;

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
                    field.setToolTipText(null);
                }else{
                    e.consume();
                    //field.setEditable(false);
                    field.setBorder(new LineBorder(Color.red));
                    field.setToolTipText("Maximale Eintragskapazität erreicht!");
                    ToolTipManager.sharedInstance().setInitialDelay(0);

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
                if((input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE)){
                }else if(field.getText().length() == 2 || field.getText().length() == 5) {
                    field.setText(field.getText() + ".");
                }

                if((input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE)){
                }else if(field.getText().length() == 1 || field.getText().length() == 4){
                    try {
                        Robot robot = new Robot();
                        robot.keyPress(KeyEvent.VK_ENTER);
                    } catch (AWTException ex) {
                        ex.printStackTrace();
                    }
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