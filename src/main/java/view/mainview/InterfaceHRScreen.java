package view.mainview;

import controller.functions.PersonalakteController;
import view.personalakte.PersonalakteCreateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** ====================================================================================================================
 *  Interface wird genutzt, dass HRView und RootView create, edit und search gleichnamig implementieren
 * ====================================================================================================================
 */
public interface InterfaceHRScreen {
    //Interface fuer die HRView und RootView

    /** ================================================================================================================
     * Methode ruft Bildschirm auf, um eine neue Personalakte zu erstellen
     *
     * @param button    Button, welcher eine Personalakte endgueltig erstellt
     */
    default void createPersonalakte(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PersonalakteCreateView();
            }
        });
    };

    /** ================================================================================================================
     * Methode ruft je nach Caller einen Personalakte-Bearbeiten Bildschirm auf
     *
     * @param table     Uebersicht aller Personalakten
     * @param caller    Wer Methode aufruft -> Root oder HR
     */
    default void editPersonalakte(JTable table, String caller){
        new PersonalakteController().edit(table, caller);
    };

    /** ================================================================================================================
     *  Implementierung fuer den Suchfilter
     */
    void searchPersonalakte();
}
