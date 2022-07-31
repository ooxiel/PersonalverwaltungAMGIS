package VIEW.TableModel.Mitarbeiter;


public class Mitarbeiter{

    public int id;
    public String kontoname;
    private String passwort;


    public Mitarbeiter(int id, String kontoname, String passwort) {
        this.id = id;
        this.kontoname = kontoname;
        this.passwort = passwort;
    }

    public Mitarbeiter(String searchIDwithKN_m, String text, String passwort) {
    }

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

