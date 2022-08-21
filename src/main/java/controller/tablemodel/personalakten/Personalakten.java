package controller.tablemodel.personalakten;

/** ====================================================================================================================
 *  Klasse wird genutzt, um alle Personalakten in einer JTable anzuzeigen
 * =====================================================================================================================
 */
public class Personalakten {
    //Attribute der Klasse
    public int id;
    String anrede;
    String vorname;
    String zweitname;
    String nachname;
    String geburtsdatum;
    String telefon;
    String email;
    String strasse;
    String strassenNR;
    String strassenBuchstabe;
    String land;
    String bundesland;
    String plz;
    String jobname;
    String besGrad;
    String abteilung;
    String abtLeiter;
    String raum;
    String standort;
    String erstelltDatum;
    String letzteAenderung;

    /** ================================================================================================================
     * Konstruktor fuer die Klasse Personalakte
     *
     * @param id                Personal-ID
     * @param anrede            Anrede
     * @param vorname           Vorname
     * @param zweitname         Zweitname
     * @param nachname          Nachname
     * @param geburtsdatum      Geburstag
     * @param telefon           Telefonnummer
     * @param email             E-Mail-Adresse
     */

    public Personalakten(int id, String anrede, String vorname, String zweitname, String nachname, String geburtsdatum, String telefon, String email) {
        this.id = id;
        this.anrede = anrede;
        this.vorname = vorname;
        this.zweitname = zweitname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.telefon = telefon;
        this.email = email;
        this.strasse = strasse;
        this.strassenNR = strassenNR;
        this.strassenBuchstabe = strassenBuchstabe;
        this.land = land;
        this.bundesland = bundesland;
        this.plz = plz;
        this.jobname = jobname;
        this.besGrad = besGrad;
        this.abteilung = abteilung;
        this.abtLeiter = abtLeiter;
        this.raum = raum;
        this.standort = standort;
        this.erstelltDatum = erstelltDatum;
        this.letzteAenderung = letzteAenderung;
    }

    /*
        Getter- und Setter-Methoden
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getZweitname() {
        return zweitname;
    }

    public void setZweitname(String zweitname) {
        this.zweitname = zweitname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getStrassenNR() {
        return strassenNR;
    }

    public void setStrassenNR(String strassenNR) {
        this.strassenNR = strassenNR;
    }

    public String getStrassenBuchstabe() {
        return strassenBuchstabe;
    }

    public void setStrassenBuchstabe(String strassenBuchstabe) {
        this.strassenBuchstabe = strassenBuchstabe;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getBesGrad() {
        return besGrad;
    }

    public void setBesGrad(String besGrad) {
        this.besGrad = besGrad;
    }

    public String getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(String abteilung) {
        this.abteilung = abteilung;
    }

    public String getAbtLeiter() {
        return abtLeiter;
    }

    public void setAbtLeiter(String abtLeiter) {
        this.abtLeiter = abtLeiter;
    }

    public String getRaum() {
        return raum;
    }

    public void setRaum(String raum) {
        this.raum = raum;
    }

    public String getStandort() {
        return standort;
    }

    public void setStandort(String standort) {
        this.standort = standort;
    }

    public String getErstelltDatum() {
        return erstelltDatum;
    }

    public String getLetzteAenderung() {
        return letzteAenderung;
    }

    public void setLetzteAenderung(String letzteAenderung) {
        this.letzteAenderung = letzteAenderung;
    }
}
