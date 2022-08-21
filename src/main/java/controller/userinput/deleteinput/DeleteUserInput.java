package controller.userinput.deleteinput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** ====================================================================================================================
 * Klasse enthaelt Funktion, um per Knopfdruck alle Eingaben auf einmal zu loeschen
 * =====================================================================================================================
 */

public class DeleteUserInput {

    /** ================================================================================================================
     *  Mit dieser Methoden koennen ueber einen Button-Klick alle Eingaben beim Erstellen oder Bearbeiten einer
     *  Personalakte geloescht werden.
     *
     * @param button            Loeschen-Button
     * @param optionalInput     Arraylist, welche optionale Felder-Eingaben enthaelt
     * @param lettersOnly       Arraylist, welche Felder enthaelt, die nur Buchstaben besitzen duerfen
     * @param numbersOnly       Arraylist, welche Felder enthaelt, die nur Zaheln besitzen duerfen
     * @param specialChars      Arraylist, welche Felder enthaelt, welche Sonderzeichen besitzen
     * @param raumField         Feld, welches den Bueroraum enthaelt
     * @param geschlecht        JComboBox, welches das Geschlecht enthält
     */
    public void all(JButton button, ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly,
                    ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                    JTextField raumField, JComboBox geschlecht){

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

    /** ================================================================================================================
     *  Methode leert einzelne Felder
     *
     * @param field     Feld, welches geleert werden soll
     */
    private void setFieldNull (JTextField field){
        field.setText(null);
    }

    /** ================================================================================================================
     * Methode leert vollstaendige Listen an Feldern
     *
     * @param list  ArrayList mit Feldern, die geleert werden sollen
     */
    private void setListNull (ArrayList<JTextField> list){

        for (JTextField k : list) {

            k.setText(null);
        }
    }

    /** ================================================================================================================
     * Methode leert eine ComboBox. Methode setzt vorraus, dass der Default-Wert (null) auf der ersten Position liegt.
     *
     * @param box   JComboBox, welche geleert werden soll
     */

    private void setComboBoxNull (JComboBox box){
        box.setSelectedIndex(0);
    }
}
