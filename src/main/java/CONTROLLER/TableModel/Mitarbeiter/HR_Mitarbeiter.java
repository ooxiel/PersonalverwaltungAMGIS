package CONTROLLER.TableModel.Mitarbeiter;

public class HR_Mitarbeiter {
    public final int id;
    public String kontoname;
    private String passwort;
    public boolean root;




    public HR_Mitarbeiter(int id, String kontoname, String passwort, boolean root) {
        this.id = id;
        this.kontoname = kontoname;
        this.passwort = passwort;
        this.root = root;
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

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

}