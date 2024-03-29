package controller.tablemodel.login.hr;

/** ====================================================================================================================
 * Klasse wird genutzt, um Logindaten in einer JTable anzuzeigen
 * =====================================================================================================================
 */

public class Logindaten {
    //Attribute der Klasse
    public int id;
    public String kontoname;
    public String passwort;
    public boolean isRoot;

    /** ================================================================================================================
     * Konstruktor der Logindaten-Klasse
     *
     * @param id            Personal-ID
     * @param kontoname     Username
     * @param passwort      Passwort
     * @param isRoot        Root-Berechtigungen
     */
    public Logindaten(int id, String kontoname, String passwort, boolean isRoot) {
        this.id = id;
        this.kontoname = kontoname;
        this.passwort = passwort;
        this.isRoot = isRoot;
    }

    /*
        Getter- und Setter-Methoden
     */
    public int getId() {
        return id;
    }

    public String getKontoname() {
        return kontoname;
    }

    public String getPasswort() {
        return passwort;
    }

    public boolean isHrmitarbeiter() {
        return isRoot;
    }
}
