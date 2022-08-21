package controller.userinput.checkinput;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Robot;

/** ====================================================================================================================
 *  Klasse wird genutzt, um Eingabefehler bereits waehrend der Eingabe abzufangen bzw. zu regulieren.
 * ====================================================================================================================
 */
public class DynamicInputProof {

    /** ================================================================================================================
     * Mit der 'setAmountofCharacterAllowed-Methode kann fuer Felder die Anzahl an Zeichen auf eine bestimmte Anzahl
     * begrenzt werden
     *
     * @param field     Feld, dass begrenzt werden soll
     * @param amount    Anzahl der zulaessigen Zeichen
     */
    public void setAmountofCharacterAllowed (JTextField field, int amount){

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                char input = e.getKeyChar();    // Input-Variable initialisiert das eingegebene Zeichen

                /*
                    Feld ist solang editierbar, solange festgelegte Anzahl an Zeichen nicht ueberschritten oder das
                    Zeichen eine Loeschung von Zeichen oder ein Enter ist.

                    Wenn nicht wird das Feld gesperrt und der User erhaelt eine Meldung ueber den Tool-Tip des
                    betreffenden Feldes
                 */
                if (field.getText().length() < amount || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE) || (input == KeyEvent.VK_ENTER)){

                    field.setEditable(true);
                    field.setBorder(LineBorder.createGrayLineBorder());
                    field.setToolTipText(null);

                }else{

                    e.consume();
                    field.setEditable(false);
                    field.setToolTipText("Maximale Eingabeskapazität erreicht!");
                    ToolTipManager.sharedInstance().setInitialDelay(0);
                }
            }
        });
    }

    /** ================================================================================================================
     * Mit dem 'dateField' kann ein Feld direkt im europaischen Datumsformat geschrieben werden. Punkte werden dabei
     * werden dabei automatisch gesetzt, sodass immer die Form dd/mm/yyyy eingehalten wird.
     *
     * @param field     Feld, welches ein Datum-Format haben soll
     */

    public void dateField(JTextField field){

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char input = e.getKeyChar();        // Input-Variable initialisiert das eingegebene Zeichen


                /*
                    Datumsfeld laesst nur Eingaben von Zahlen zu. If-Bedingung prueft auf Zahlen- oder Buchstaben-Eingaben.
                    Bei Eingabe von Buchstaben erhält der User eine Information ueber den Tool-Tip-Text
                 */
                if (!Character.isDigit(input)) {
                    e.consume();
                    field.setToolTipText("Keine Eingabe von Buchstaben oder Sonderzeichen moeglich! Bitte nur Zahlen eingeben.");
                    ToolTipManager.sharedInstance().setInitialDelay(0);
                }else{
                    field.setToolTipText(null);
                }

                /*
                    Mit den beiden folgenden If-Bedingungen werden automatisiert ueber den Robot Punkte gesetzt.
                 */

                if((input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE)){
                    field.setBorder(LineBorder.createGrayLineBorder());
                }else if(field.getText().length() == 2 || field.getText().length() == 5) {
                    field.setText(field.getText() + ".");
                }

                if((input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE)){
                    field.setEditable(true);
                }else if(field.getText().length() == 1 || field.getText().length() == 4){
                    try {
                        Robot robot = new Robot();
                        robot.keyPress(KeyEvent.VK_ENTER);
                    } catch (AWTException ex) {
                        ex.printStackTrace();
                    }
                }

                /*
                    Es koennen in diesem Textfeld nur Aenderungen bis 10 Zeichen vorgenommen werden, ausgenommen davon
                    sind Back-Space, Delete und Enter.
                    Falls die 10 Zeichen überschritten werden, wird das Textfeld gesperrt. Wenn nicht bleibt das
                    Textfeld editierbar
                 */

                setAmountofCharacterAllowed(field, 10);
            }

        });


    }

    /** ================================================================================================================
     * Felder in denen ausschliesslich Buchstaben vorkommen duerfen werden der ArrayList 'list' zugeordnet. Die Methode
     * ueberprueft ob diese Bedingung waehrend der Eingabe eingehalten wird.
     *
     * @param list  ArrayList, die alle Felder enthaelt, die nur Buchstaben enthalten darf
     */
    public void onlyLetterField(ArrayList<JTextField> list){

        for (JTextField k : list) {
             k.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);

                    char input = e.getKeyChar();        // Input-Variable initialisiert das eingegebene Zeichen

                    /*
                        If-Bedingung ueberpruft, ob die Eingabe ein Buchstabe oder eine Zahl ist.
                        Ist die Eingabe eine Zahl erhaelt der User eine Information ueber den Tool-Tip-Text
                     */

                    if(!Character.isLetter(input) || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE || (input == KeyEvent.VK_ENTER))){
                        e.consume();        //Event wird beendet -> Zahlen etc. werden nicht in das Feld eingetragen
                        k.setToolTipText("Keine Eingabe von Zahlen oder Sonderzeichen moeglich! Bitte nur Buchstaben eingeben.");
                        ToolTipManager.sharedInstance().setInitialDelay(0);
                    }else{
                        k.setToolTipText(null);
                    }

                }
            });
        }

    }
    /** ================================================================================================================
     * Felder in denen ausschliesslich Zahlen vorkommen duerfen werden der ArrayList 'list' zugeordnet. Die Methode
     * ueberprueft ob diese Bedingung waehrend der Eingabe eingehalten wird.
     *
     * @param list  ArrayList, die alle Felder enthaelt, die nur Zahlen enthalten darf
     */
    public void onlyNumberField(ArrayList<JTextField> list){

        for (JTextField k : list) {
            k.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);

                    char input = e.getKeyChar();        // Input-Variable initialisiert das eingegebene Zeichen

                    /*
                        If-Bedingung ueberpruft, ob die Eingabe ein Buchstabe oder eine Zahl ist.
                        Ist die Eingabe ein Buchstabe, erhaelt der User eine Information ueber den Tool-Tip-Text
                     */

                    if(!Character.isDigit(input) || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE) || (input == KeyEvent.VK_ENTER)) {
                        e.consume();        //Event wird beendet -> Zahlen etc. werden nicht in das Feld eingetragen
                        k.setToolTipText("Keine Eingabe von Buchstaben oder Sonderzeichen moeglich! Bitte nur Zahlen eingeben.");
                        ToolTipManager.sharedInstance().setInitialDelay(0);
                    }else{
                        k.setToolTipText(null);
                    }
                }
            });
        }
    }
}