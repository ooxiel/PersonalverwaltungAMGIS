package controller.functions;

/** ====================================================================================================================
 *  Klasse wird genutzt, um alle Informationen im MitarbeiterView anzuzeigen
 * =====================================================================================================================
 */
public class MitarbeiterController {

    //Attribute der Klasse
    public int id;
    public String kontoname;
    private String passwort;

    /** ================================================================================================================
     * Konstruktor der Klasse MitarbeiterController
     *
     * @param id            Personal-ID
     * @param kontoname     Username
     * @param passwort      Passwort
     */
    public MitarbeiterController(int id, String kontoname, String passwort) {
        this.id = id;
        this.kontoname = kontoname;
        this.passwort = passwort;
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

    public void setKontoname(String kontoname) {
        this.kontoname = kontoname;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}

