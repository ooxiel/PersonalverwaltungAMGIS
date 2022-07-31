package VIEW.TableModel.Login;

public class Logindaten {
    public int id;
    public String kontoname;
    public String passwort;
    public boolean hrmitarbeiter;

    public Logindaten(int id, String kontoname, String passwort, boolean hrmitarbeiter) {
        this.id = id;
        this.kontoname = kontoname;
        this.passwort = passwort;
        this.hrmitarbeiter = hrmitarbeiter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isHrmitarbeiter() {
        return hrmitarbeiter;
    }

    public void setHrmitarbeiter(boolean hrmitarbeiter) {
        this.hrmitarbeiter = hrmitarbeiter;
    }
}
