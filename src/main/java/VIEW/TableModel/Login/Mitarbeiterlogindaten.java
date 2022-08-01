package VIEW.TableModel.Login;

public class Mitarbeiterlogindaten {
    public int id;
    public String kontoname;
    public String passwort;

    public Mitarbeiterlogindaten(int id, String kontoname, String passwort) {
        this.id = id;
        this.kontoname = kontoname;
        this.passwort = passwort;
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


}
