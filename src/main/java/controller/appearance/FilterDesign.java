package controller.appearance;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

/**
 * =====================================================================================================================
 * FilterDesign ist eine Klasse die ausschliesslich aus Design-Aspekten genutzt wurde
 *
 * =====================================================================================================================
 */
public class FilterDesign {

    /** ================================================================================================================
     * Jedes JTextField hat einen Standard LookAndFeel. Mit der Methode 'changeBorderLook' werden diese personalisiert.
     *
     * @param geschlecht        ComboBox fuer die Auswahl des Geschlechts
     * @param nameField         Filterfeld fuer den Namen
     * @param vornameField      Filterfeld fuer den Vornamen
     * @param jobnameField      Filterfeld fuer die Berufsbezeichnung
     * @param abteilungField    Filterfeld fuer Abteilung
     * @param standortFeild     Filterfeld fuer den Standort
     */
    public void changeBorderLook(JComboBox geschlecht, JTextField nameField, JTextField vornameField, JTextField jobnameField, JTextField abteilungField, JTextField standortFeild){

        Border border = new BevelBorder(0, Color.white, Color.decode("#050a30"));       // Generierung eines personalisierten Feldbegrenzung-Stils

        // Anwendung der Feldbegrenzungen auf die angegebenen Felder

        geschlecht.setBorder(border);
        nameField.setBorder(border);
        vornameField.setBorder(border);
        jobnameField.setBorder(border);
        abteilungField.setBorder(border);
        standortFeild.setBorder(border);
    }
}
