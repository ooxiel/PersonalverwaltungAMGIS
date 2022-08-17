package CONTROLLER.TableModel.Mitarbeiter;


public class Mitarbeiter{
    //Attribute der Klasse
    public int id;
    public String kontoname;
    private String passwort;

    //Konstruktor
    public Mitarbeiter(int id, String kontoname, String passwort) {
        this.id = id;
        this.kontoname = kontoname;
        this.passwort = passwort;
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
}

