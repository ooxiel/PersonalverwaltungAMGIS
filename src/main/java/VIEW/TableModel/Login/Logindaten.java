package VIEW.TableModel.Login;

public class Logindaten {
    public int id;
    public String kontoname;
    public String passwort;
    public boolean isRoot;

    public Logindaten(int id, String kontoname, String passwort, boolean isRoot) {
        this.id = id;
        this.kontoname = kontoname;
        this.passwort = passwort;
        this.isRoot = isRoot;
    }

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
