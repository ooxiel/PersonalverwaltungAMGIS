package controller.appearance;

import view.login.LoginView;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * =====================================================================================================================
 *  DefaultFraming ist eine Klasse,welche dazu genutzt wird, damit GUI-Komponeten bestimmte Basisfunktionen
 *  wiederverwendbar nutzen koennen.
 *  ====================================================================================================================
 */
public class DefaultFraming {

    /** ================================================================================================================
     * Ein Frame muss ueber mehrere Methodenaufrufe implementiert werden. Die Methode 'show' bundelt diese notwendigen
     * Implementierungen in einer Methode.
     *
     * @param frame     Anzeigerahmen
     * @param main      Anzeigeinhalte
     * @param width     Breite des Fensters
     * @param height    Hoehe des Fensters
     * @param exitType  Schliessverhalten des Fensters
     */
    public void show(JFrame frame, JPanel main, int width, int height, String exitType){

        frame.add(main);                    // Das JPanel main, enthaelt alle Inhalte, und wird dem Frame hinzugefuegt
        frame.setSize(width, height);       // Groesse des Frames wird gesetzt
        frame.setVisible(true);             // Frame wird auf "Sichtbar" gesetzt
        frame.setLocationRelativeTo(null);  // Frame wird in der Bildschirm-Mitte platziert
        disposeType(frame, exitType);       // Methodenausruf des Schliessverhaltens des Frames
    }

    /** ================================================================================================================
     * Im Programm werden zwei verschiedene Schlieessverhalten (DisposeTypes) verwendet:
     *      - Exit    = Das Programm wird komplett geschlossen
     *      - Dispose = Es wird nur das einzelne Fenster geschlossen, das Programm wird aber weiter ausgefuehrt
     *
     * @param frame     Anzeigerahmen
     * @param exitType  Schliessverhalten des Fensters
     */
    private void disposeType(JFrame frame, String exitType){
        if (exitType.equals("EXIT")){
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        if(exitType.equals("DISPOSE")){
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
    }

    /** ================================================================================================================
     * Mit Hilfe der Methode defaultLogout kann jeder View (wenn notwendig) eine Menuleiste mit einem "Logout"-Button
     * anzeigen lassen.
     * Beim Betaetigen wird der LoginView ausgerufen und der aktuelle Bildschirm geschlossen.
     *
     * @param frame Anzeigerahmen
     */
    public void defaultLogout (JFrame frame){

        JMenuBar menu = new JMenuBar();                     // Generierung der Menuleiste
        JMenu logout = new JMenu("Logout");              // Der Menuleiste wird das Menu mit dem Namen "Logout" hinzugefuegt

        logout.setForeground(Color.white);                 // Schriftfarbe von "Logout"-Menu wird aus weiss gesetzt
        menu.setBackground(Color.decode("#050a30"));   // Menu-Hintergrundfarbe wird auf Hexcode-Farbe gesetzt

        menu.add(logout);                                   // Menu Logout wird der Menuleiste hinzugefuegt

        /*
        "Lougout"-Menu bekommt einen Listener zugewiesen, welcher beim Betaetigen das aktuelle Fenster schliesst
        und den LoginView oeffnet
         */
        logout.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                new LoginView();
                frame.dispose();
            }
        /*
        MenuListener ist ein Interface somit muessen die folgenden Methoden ebenfalls implementiert werden
         */
            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });

        frame.setJMenuBar(menu);                                // Menuleiste wird dem Frame hinzugefuegt
    }

    /** ================================================================================================================
     * defaultDispose wird fuer die implementierten "Abbrechen"-Button genutzt
     * Wenn der Button betaetigt wird, schliesst sich der entsprechende Frame.
     * Das Programm wird aber nicht beendet
     *
     * @param frame     Anzeigerahmen
     * @param button    Abbrechen-Button
     */
    public void defaultDispose (JFrame frame, JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

}
