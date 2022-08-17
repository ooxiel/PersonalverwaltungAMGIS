package controller.TableModel.Login;

public class Mitarbeiterlogindaten {
    //Attribute der Klasse
    public int id;
    public String kontoname;
    public String passwort;
    //Konstruktor für die Klasse
    public Mitarbeiterlogindaten(int id, String kontoname, String passwort) {
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

    public String getPasswort() {
        return passwort;
    }


}
