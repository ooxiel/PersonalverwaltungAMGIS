package controller.tablemodel.mitarbeiter;

public class HRMitarbeiter {
    //Attribute der Klasse
    public final int id;
    public String kontoname;
    private String passwort;
    public boolean root;
    //Kontruktor
    public HRMitarbeiter(int id, String kontoname, String passwort, boolean root) {
        this.id = id;
        this.kontoname = kontoname;
        this.passwort = passwort;
        this.root = root;
    }
    //Getter und Setter
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

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }
}
