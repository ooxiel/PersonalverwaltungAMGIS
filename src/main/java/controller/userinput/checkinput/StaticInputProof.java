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

/** ====================================================================================================================
 *  Klasse wird genutzt, um Eingabefehler nachdem der 'Personalakte-erstellen'- oder 'Aenderungen uebernehmen'-Button
 *  betaetigt wurde.
 * ====================================================================================================================
 */
public class StaticInputProof {

    /** ================================================================================================================
     * Methode ueberpruft, eine ComboBox leer oder gefüllt ist
     *
     * @param comboBox      ComboBox, welche ueberpruft werden soll
     * @return              true -> ComboBox ist leer; false -> ComboBox ist nicht leer
     */
    public boolean comboBoxFieldisEmpty(JComboBox comboBox){

        if(comboBox.getSelectedIndex() == 0){

            comboBox.setToolTipText("Bitte nehmen Sie eine Eingabe vor!");      // User-Information das ComboBox leer ist
            comboBox.setBorder(new LineBorder(Color.red));                      // rote Markierung um ComboBox
            ToolTipManager.sharedInstance().setInitialDelay(0);                 // Tool-Tip-Text wird ohne Verzoegerung angezeigt

            /*
                Wenn ComboBox auswählt wird, wird Farbe auf den Default-Wert gesetzt.
             */
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

    /** ================================================================================================================
     * DIe Methode ueberpruft ob es sich bei einem eingegebenen Datum um ein reales Datum handelt. Real bedeutet:
     * - Tag <= 31
     * - Monat <= 12
     * - Jahr <= aktuelles Jahr
     *
     * @param field     Feld, bei welchem das Datumformat ueberprueft werden soll
     * @return          true -> Datum ist gueltig; false -> Datum ist ungueltig
     */
    public boolean dateValid (JTextField field) {
        String input = field.getText();                                             // User-Eingabe
            char[] inputs = input.toCharArray();                                    // Eingabe in Array vom Typ char
            char[] outputs = new char[8];                                           // Eingabe ohne Datumspunkte

            /*
                Punkte werden aus dem Array gefiltert
             */
            int counter = 0;
                for (char k : inputs) {
                    if (k != '.') {
                        outputs[counter] = k;
                        counter++;
                    }
                }

            /*
                Array wird in Tag, Monat und Jahr aufgeteilt
             */

            String outputDay = String.copyValueOf(outputs,0,2);
            String outputMonth = String.copyValueOf(outputs,2,2);
            String outputYear = String.copyValueOf(outputs,4,4);

            int day;
            int month;
            int year;

            /*
                Strings werden in Integer fuer weitere Pruefung umgewandelt
             */

            try {
                day     = Integer.parseInt(outputDay);
                month   = Integer.parseInt(outputMonth);
                year    = Integer.parseInt(outputYear);
            } catch (NumberFormatException e) {
                addAndRemoveFocusListener(field);
                setBorderColorAndToolTip(field,"Datum ungültig");
                return false;
            }

            GregorianCalendar gregorianCalendar = new GregorianCalendar();      // Generierung eines GregorianCalender-Objekts
                gregorianCalendar.setLenient(false);                            // Lenient, Datum Eingabe wie Tag = 942 eine Exception provozieren

            /*
                Datum wird auf Gueltigkeit geprueft
             */

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

    /** ================================================================================================================
     * Mit dieser Methode wird eine eingegebene E-Mail-Adresse validiert. Eine E-Mail-Adresse gilt als validiert, wenn:
     * - mind. ein "@" zwischen zwei Wortgruppen vorhanden ist
     *
     * @param field     Feld, welches eine Mail enthaelt und ueberprueft werden soll
     * @return          true -> Mail-Adresse bestaetigt; false -> Mail-Adresse nicht bestaetigt
     */
    public boolean mailValide(JTextField field){

        String input = field.getText();     // User-Eingabe

        /*
            Regular Expression fuer die E-Mail
         */

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
    //Telefonnummer validieren
    public boolean telefonValide(JTextField field){
        String input = field.getText();     // User-Eingabe

        /*
            Regular Expression fuer die Telefonnummer
         */

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

    /** ================================================================================================================
     * Methode setzt Eingabe einer Methode auf einen maximalen Wert, wenn dieser ueberschritten wird.
     * - Bsp.: Beschaeftigungsgrad kann maximal 100% sein
     * - Beschaeftigungsgrad > 100% -> automatisch auf 100% gesetzt
     *
     * @param field
     * @param max
     */
    public void setMaxInteger (JTextField field, int max){

        int input;
        try{
            input = Integer.parseInt(field.getText());      // User-Eingabe wird ins Integer-Format formatiert
        }catch (NumberFormatException e){
            input = 0;
        }

        /*
            Wird gegebenes Maximum ueberschritten, wird Feldwert auf dieses gesetzt
         */

        if (input > max) {
            field.setText(Integer.toString(max));
        }
    }

    /** ================================================================================================================
     * Methode ueberpruft, ob in die zu pruefenden Felder Eingaben vorgenommen wurden.
     *
     * @param list  ArrayList, welche die Felder mit den Inputs enthaelt die geprueft werden sollen
     * @return      true -> Felder sind nicht leer; false -> Felder sind leer
     */
    public boolean inputNotNull(ArrayList<JTextField> list){
        ArrayList<Integer> notNullConfirm = new ArrayList<>();

        int counter = 0;

            /*
                Iteration durch die ArrayList mit einem for-each-loop

                Ist das Feld leer, wird der ArrayList 'notNullConfirm' ein Element hinzugefuegt.
             */

            for (JTextField k : list) {

                if(k.getText().isEmpty()){
                    fieldEmpty(k);

                    notNullConfirm.add(counter,0);
                    counter++;
                }
            }

            /*
                Ist die ArrayList 'notNullConfirm' vollstaendig leer, gibt es keine leeren Felder. Anderenfalls doch.
             */

            if(notNullConfirm.isEmpty()){
                return false;
            }
        return true;
    }

    /** ================================================================================================================
     *  Methode wird genutzt um leere Felder mit einer Tool-Tip-Information für den User zu versehen.
     *  Sobald das Feld ausgewaehlt wird, verschwindet der Tool-Tip-Text wieder
     *
     * @param k     Feld, welches einen Tool-Tip-Text zugewiesen werden soll
     */
    private void fieldEmpty(JTextField k){
        setBorderColorAndToolTip(k,"Bitte nehmen Sie eine Eingabe vor!");
        addAndRemoveFocusListener(k);
    }

    /**
     * Wenn es einen Fehler / falsche Eingabe in einem Feld gibt, wird das Feld rot. Die Methode wird dazu genutzt, dass
     * wenn das Feld ausgewaehlt wird, um Aenderungen vorzunehmen. Den eingegeben Text automatisch loescht. Sobald das
     * Feld geaendert wurde, wird ebenfalls der Aktionslistener entfernt, damit nicht bei jedem auswaehlen des Feldes
     * die Eingaben wieder geloescht werden.
     *
     * @param field     Feld, welches mit einem Tool-Tip-Text versehen werden soll
     */
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

    /** ================================================================================================================
     * Bei falschen / fehlerhaften Eingaben werden die entsprechenden Felder rot markiert. Die Methode setzt die Farbe
     * wieder auf den Default zurueck.
     *
     * @param field     Feld, bei welchem die Randfarbe auf den Default gesetzt werden soll
     */
    private void resetBorderColorAndToolTip(JTextField field){
        if(KeyEvent.VK_DELETE == 127 || KeyEvent.KEY_TYPED == 400){
            field.setBorder(LineBorder.createGrayLineBorder());
            field.setToolTipText(null);
        }
    }

    /** ================================================================================================================
     * Methode wird genutzt die Randfarbe eines Textfeldes auf rot zu setzten und zeitgleich die Tool-Tip User-Information
     * zu platzieren.
     *
     * @param field     Feld, welches rote Raender und einen Tool-Tip-Text erhalten soll
     * @param text      Tool-Tip-Text
     */

    private void setBorderColorAndToolTip (JTextField field, String text){
        //Border von Feld mit falscher/fehlender Angabe wird Rot gesetzt
        //ToolTip wird angezeigt wenn mit der Maus darueber geschwebt wird
        field.setToolTipText(text);
        field.setBorder(new LineBorder(Color.red));
        ToolTipManager.sharedInstance().setInitialDelay(0);
    }
}