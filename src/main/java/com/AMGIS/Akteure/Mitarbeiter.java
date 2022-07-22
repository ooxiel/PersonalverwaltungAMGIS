package com.AMGIS.Akteure;

import java.io.File;
import java.io.Serializable;



public class Mitarbeiter{

    public final int id;
    public String kontoname;
    private String passwort;
    public boolean isHRmitarbeiter;

    public Mitarbeiter(int id, String kontoname, String passwort, boolean isHRmitarbeiter) {
        this.id = id;
        this.kontoname = kontoname;
        this.passwort = passwort;
        this.isHRmitarbeiter = isHRmitarbeiter;
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

    public boolean isHRmitarbeiter() {
        return isHRmitarbeiter;
    }

    public void setHRmitarbeiter(boolean HRmitarbeiter) {
        isHRmitarbeiter = HRmitarbeiter;
    }
}
