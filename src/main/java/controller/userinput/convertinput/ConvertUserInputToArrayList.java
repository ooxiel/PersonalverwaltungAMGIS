package controller.userinput.convertinput;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/** ====================================================================================================================
 *  Klasse wird genutzt, um effizient ArrayList vom Typ JTextField zu generieren
 * ====================================================================================================================
 */

public class ConvertUserInputToArrayList {

    /** ================================================================================================================
     * Erstellt ArrayList vom Typ JTextField mit variabler Anzahl an Parametern
     *
     * @param args  Variable Anzahl an JTextfields die uebergeben werden koennen
     * @return      Arraylist mit allen Elementen, die uebergeben wurden
     */
    public ArrayList<JTextField> itemstoAdd(JTextField... args){

        return new ArrayList<>(Arrays.asList(args));
    }
}
