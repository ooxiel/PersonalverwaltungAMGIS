package view.mainview;

import controller.appearance.DefaultFraming;

import javax.swing.*;

/** ====================================================================================================================
 * Interface wird genutzt, dass jeder mainview eine Methode show und einen logout implementieren
 * =====================================================================================================================
 */
public interface InterfaceScreen {

    /** ================================================================================================================
     *  Implementierung, dass jede implementierende Klasse eine show-Methode besitzen
     *
     * @param frame     Anzeigefenster
     * @param main      anzuzeigende Inhalte
     * @param id        Personal-ID
     */
    void show(JFrame frame, JPanel main, String id);

    /** ================================================================================================================
     * Methode ruft eine default Logout-Methode auf
     *
     * @param frame     Anzeigefenster
     */
    default void logout(JFrame frame){
        new DefaultFraming().defaultLogout(frame);
    }
}
