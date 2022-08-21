package controller.tablemodel.login.mitarbeiter;

/** ====================================================================================================================
 * Klasse wird genutzt, um Mitarbeiterlogindaten in einer JTable anzuzeigen
 * =====================================================================================================================
 */
public class Mitarbeiterlogindaten {
    /*
        Attribute der Klasse
     */
    public int id;
    public String kontoname;
    public String passwort;

    /** ================================================================================================================
     * Konstruktor der Mitarbeiterlogindaten-Klasse
     *
     * @param id            Personal-ID
     * @param kontoname     Username
     * @param passwort      Passwort
     */
    public Mitarbeiterlogindaten(int id, String kontoname, String passwort) {
        this.id = id;
        this.kontoname = kontoname;
        this.passwort = passwort;
    }
    /*
        Getter-Methoden
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


}
