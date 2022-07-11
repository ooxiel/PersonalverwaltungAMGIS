package GUI.ProofServices;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class txtFieldProof {

    public boolean inputNotNull(ArrayList<JTextField> list){

        for (JTextField k : list) {

            if(k.getText().isEmpty()){
                fieldEmpty(k);
            }
        }
        return true;
    }

    private void fieldEmpty(JTextField k){

            // True: Fehlermeldung wird geworfen
            k.setText("Fehlt ist leer oder unzulässigen Zeichen!");        // Fehlermeldung = "Notwendige Angabe fehlt"

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


    /*
        Hier alle zu überprüfenden Felder Eingeben
     */

    public boolean dateField(JTextField textField){



        return true;
    }

    public boolean comboBoxField(JComboBox comboBox){

    /*
        // Ueberpruefung, ob eine Eingabe vorgenommen wurde

        if (geschlecht.getSelectedItem().toString().isEmpty()) {
            hintAnrede.setText("Notwendige Angabe fehlt!!!");

            geschlecht.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    hintAnrede.setVisible(false);
                }

                @Override
                public void focusLost(FocusEvent e) {
                    geschlecht.removeFocusListener(this);
                }
            });
        }*/

        return true;
    }
}
